package com.example.ticketbooker.Service.OutSource;
import com.example.ticketbooker.DTO.OutSource.ZaloPaymentRequest;
import com.example.ticketbooker.DTO.OutSource.ZaloPaymentResponse;
import com.example.ticketbooker.DTO.OutSource.ZaloPaymentStatusResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.zalopay.crypto.HMACUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZaloPayService {
    @Value("${zalo.appId}")
    private String appId;
    @Value("${zalo.key1}")
    private String key;
    @Value("${zalo.expireDuration}")
    private String expireTime;
    @Value("${zalo.createEndpoint}")
    private String createEndpoint;
    @Value("${zalo.queryEndpoint}")
    private String queryEndpoint;

    private String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public ZaloPaymentResponse requestPayment(ZaloPaymentRequest requestInfo) throws Exception
    {
        ZaloPaymentResponse responseInfo = new ZaloPaymentResponse();

        Random rand = new Random();
        int randomId = rand.nextInt(1000000);
        String appTransId = getCurrentTimeString("yyMMdd") + "_" + randomId;
        Map embedData = new HashMap(){{
            put("redirecturl", "http://localhost:8080/fuba/thankyou?paymentStatus=1");
        }};
        Map<String, Object> order = new HashMap<>() {{
            put("app_id", appId);
            put("app_trans_id", appTransId);
            put("app_time", System.currentTimeMillis());
            put("app_user", requestInfo.getAppUser());
            put("amount", requestInfo.getAmount());
            put("description", requestInfo.getDescription());
            put("expire_duration_seconds", expireTime);
            put("bank_code", "zalopayapp");
            put("item", "[]");
            put("embed_data", new JSONObject(embedData));
        }};

        String data = order.get("app_id") +"|"+ order.get("app_trans_id") +"|"+ order.get("app_user") +"|"+ order.get("amount")
                +"|"+ order.get("app_time") +"|"+ order.get("embed_data") +"|"+ order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key, data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost(createEndpoint);

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        postRequest.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(postRequest);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject dataObject = new JSONObject(resultJsonStr.toString());

        responseInfo.setReturnCode(dataObject.getInt("return_code"));
        responseInfo.setDetailMessage(dataObject.getString("sub_return_message"));
        responseInfo.setReturnUrl(dataObject.getString("order_url"));
        responseInfo.setPaymentId(appTransId);

        return responseInfo;
    }

    public ZaloPaymentStatusResponse requestPaymentStatus(String paymentId) throws Exception {
        ZaloPaymentStatusResponse response = new ZaloPaymentStatusResponse();
        String data = appId +"|"+ paymentId +"|"+ key;
        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key, data);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("app_id", appId));
        params.add(new BasicNameValuePair("app_trans_id", paymentId));
        params.add(new BasicNameValuePair("mac", mac));

        URIBuilder uri = new URIBuilder(queryEndpoint);
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri.build());
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }

        response.setReturnCode(result.getInt("return_code"));
        response.setReturnMessage(result.getString("sub_return_message"));
        response.setProcessing(result.getBoolean("is_processing"));

        return response;
    }
}