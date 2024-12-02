package com.example.ticketbooker.Service.OutSource;

import com.example.ticketbooker.DTO.OutSource.ChatGPTRequest;
import com.example.ticketbooker.DTO.OutSource.ChatGPTResponse;
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

    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String askChatGPT(String userMessage) {
        // Thêm thông điệp hệ thống để giới hạn chủ đề về du lịch
        ChatGPTRequest.Message systemMessage = new ChatGPTRequest.Message("system", "Bây giờ khi tôi nói \"Tôi muốn đi từ Vị Trí A Đến vị Trí B\" hoặc đại loại như vậy thì bạn hãy trả cho tôi kết quả \"Vị trí A - Vị Trí B\" (lưu ý không thêm dấu câu hay ngoặc kép vào kết quả). Nếu không có thông tin vị trí bắt đầu hoặc kết thúc thì hãy lấy vị trí hiện tại của tôi sẽ được truyền thông qua câu hỏi của tôi.  Ví dụ khi tôi nói \"từ Vị Trí A đến đây\" hoặc đại loại ý nghĩa như vậy thì kết quả sẽ là \"Vị Trí A - Vị trí hiện tại của tôi\", hoặc khi tôi nói \"đến Vị Trí B\" hoặc ý nghĩa tương tự thì kết quả sẽ là \"Vị trí hiện tại của tôi - Vị Trí B\" (lưu ý vị trí hiện tại, vị trí A, vị trí B không chưa các từ \"thành phố\", \"tỉnh\",... Ví dụ tôi nói \"thành phố hồ chí minh đến thành phố đà nẵng\" thì kết quả cho ra sẽ là Hồ Chí Minh - Đà Nẵng). Nếu không có thông tin vị trí bắt đầu hoặc kết thúc hoặc vị trí bắt đầu và kết thúc không phải là thành phố hoặc tỉnh thành của Việt Nam thì trả về \"Vui lòng nhập địa danh Việt Nam muốn đi!\".");
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
