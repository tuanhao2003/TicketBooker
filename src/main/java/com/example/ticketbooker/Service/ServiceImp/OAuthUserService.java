package com.example.ticketbooker.Service.ServiceImp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class OAuthUserService {
    public String getUserInfo(String accessToken) {
        String url = "https://people.googleapis.com/v1/people/me?personFields=names,emailAddresses,photos,genders,birthdays,addresses,phoneNumbers";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody(); // Trả về thông tin người dùng
    }
}
