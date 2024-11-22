package com.example.ticketbooker.Util;

import java.net.URLEncoder;
import java.util.*;

public class VNPayUtils {

    public static String createQueryUrl(Map<String, String> vnp_Params, String hashSecret) throws Exception {
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder query = new StringBuilder();
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build query string
                query.append(URLEncoder.encode(fieldName, "UTF-8"))
                        .append('=')
                        .append(URLEncoder.encode(fieldValue, "UTF-8"))
                        .append('&');
                // Build data string for HMAC
                hashData.append(fieldName).append('=').append(fieldValue).append('&');
            }
        }

        // Remove last '&'
        query.deleteCharAt(query.length() - 1);
        hashData.deleteCharAt(hashData.length() - 1);

        String secureHash = hmacSHA512(hashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return query.toString();
    }

    public static String validatePayment(Map<String, String> params, String hashSecret) {
        String secureHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = params.get(fieldName);
            if ((value != null) && (value.length() > 0)) {
                hashData.append(fieldName).append('=').append(value).append('&');
            }
        }
        hashData.deleteCharAt(hashData.length() - 1);

        String calculatedHash = hmacSHA512(hashSecret, hashData.toString());
        return calculatedHash.equals(secureHash) ? "Payment Success" : "Invalid Signature";
    }

    private static String hmacSHA512(String key, String data) {
        try {
            javax.crypto.Mac hmacSHA512 = javax.crypto.Mac.getInstance("HmacSHA512");
            javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "HmacSHA512");
            hmacSHA512.init(secretKey);
            byte[] hash = hmacSHA512.doFinal(data.getBytes());
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("HmacSHA512 error", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
