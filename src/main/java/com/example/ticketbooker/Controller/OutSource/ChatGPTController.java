package com.example.ticketbooker.Controller.OutSource;

import com.example.ticketbooker.Service.OutSource.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @Autowired
    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    /**
     * API endpoint để gửi câu hỏi tới ChatGPT
     *
     * @param userMessage thông điệp từ người dùng
     * @return câu trả lời từ ChatGPT
     */
    @PostMapping("/ask")
    public ResponseEntity<String> askChatGPT(@RequestBody String userMessage) {
        try {
            String response = chatGPTService.askChatGPT(userMessage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/askDetail")
    public ResponseEntity<String> askDetailChatGPT(@RequestBody String userMessage) {
        try {
            String response = chatGPTService.askDetailChatGPT(userMessage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
