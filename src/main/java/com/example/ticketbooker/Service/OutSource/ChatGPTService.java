package com.example.ticketbooker.Service.OutSource;

import com.example.ticketbooker.DTO.OutSource.ChatGPTRequest;
import com.example.ticketbooker.DTO.OutSource.ChatGPTResponse;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ChatGPTService {

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;  // Đọc mô hình từ application.properties

    // Các biến @Value như trước
    private final RestTemplate restTemplate;
    private final RouteService routeService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ChatGPTService(RestTemplate restTemplate, RouteService routeService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.routeService = routeService;
        this.objectMapper = objectMapper;
    }

    public String askChatGPT(String userMessage) {
        try {
            // Lấy danh sách tuyến đường
            List<Routes> routes = routeService.getAllRoutes();

            // Chuyển đổi danh sách thành JSON
            String routesJson = objectMapper.writeValueAsString(routes);

            // Tạo thông điệp với danh sách tuyến đường
            ChatGPTRequest.Message systemMessage = new ChatGPTRequest.Message("system", "Tôi sẽ truyền danh sách các tuyến đường bao gồm điểm đi và điểm đến, nếu câu người dùng nói có ý nghĩa muốn đi từ địa điểm A đến địa điểm B nếu địa điểm A và B khớp với departureLocation và arrivalLocation thì trả về kết quả \"departureLocation - arrivalLocation\" (Không bao gồm ngoặc kép). Ngoài ra tôi sẽ truyền vào \"Currentlocation\". Nếu người dùng nói những câu có ý nghĩa như \"tôi muốn đến địa điểm B\" mà không có vị trí departureLocation cụ thể mà \"Currentlocation\" khớp với departureLocation thì trả về kết quả \"departureLocation(khớp với Currentlocation) - arrivalLocation\". Nếu người dùng nói những câu có ý nghĩa như \"Từ địa điểm A về đây\" mà không có vị trí arrivalLocation cụ thể mà \"Currentlocation\" khớp với arrivalLocation thì trả về kết quả \"departureLocation - arrivalLocation (khớp với Currentlocation)\". Nếu câu hỏi không liên quan hoặc không có kết quả hợp lý thì trả về \"Vui lòng nhập tuyến đường\".\n" +
                    "Đây là danh sách các tuyến đường: " + routesJson);
            ChatGPTRequest.Message userMessageObj = new ChatGPTRequest.Message("user", userMessage);

            // Tạo yêu cầu
            ChatGPTRequest request = new ChatGPTRequest(model, List.of(systemMessage, userMessageObj));

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            HttpEntity<ChatGPTRequest> requestEntity = new HttpEntity<>(request, headers);
            ResponseEntity<ChatGPTResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGPTResponse.class);

            return response.getBody().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi lấy dữ liệu.";
        }
    }

    public String askDetailChatGPT(String userMessage) {
        // Thêm thông điệp hệ thống để giới hạn chủ đề về du lịch
        ChatGPTRequest.Message systemMessage = new ChatGPTRequest.Message("system", "Bây giờ bạn là một trợ lý thông minh thân thiện của trang web đặt vé xe FuBa\n" +
                "Khi người dùng hỏi \"tra cứu vé ở đâu\" (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện và gửi họ đường link http://localhost:8080/fuba/ticket-lookup.\n" +
                "Khi người dùng hỏi \"cho tôi thông tin liên hệ\" (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện và gửi họ đường link http://localhost:8080/fuba/contact-us.\n" +
                "Khi người dùng hỏi \"giới thiệu về trang web\" hoặc \"giới thiệu về Fuba\" (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện và gửi họ đường link http://localhost:8080/fuba/about-us.\n" +
                "Khi người dùng hỏi \"Xem thông tin chi tiết của tôi ở đâu\" (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện và gửi họ đường link http://localhost:8080/fuba/info.\n" +
                "Khi người dùng hỏi \"Xem lịch sử đặt vé ở đâu\" (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện và gửi họ đường link http://localhost:8080/fuba/history-booking.\n" +
                "Khi người dùng hỏi về một địa danh của Việt Nam (không cần phải chính xác từng chữ chỉ cần câu hỏi mang ý nghĩa tương tự) thì bạn sẽ trả lời thân thiện, giới thiệu chi tiết về các địa danh đó. Nếu không phải là địa danh Việt Nam thì trả lời trang web chúng tôi không hỗ trợ du lịch ngoài Việt Nam.");
        ChatGPTRequest.Message userMessageObj = new ChatGPTRequest.Message("user", userMessage);

        // Tạo yêu cầu với cả thông điệp hệ thống và thông điệp từ người dùng
        ChatGPTRequest request = new ChatGPTRequest(model, List.of(systemMessage, userMessageObj));

        // Cấu hình Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Gửi yêu cầu
        HttpEntity<ChatGPTRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<ChatGPTResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGPTResponse.class);

        // Lấy nội dung phản hồi
        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
