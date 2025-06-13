package com.HEI.Hazavao.OpenAPIService;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class OpenAPIService {

  private final String apiKey;

  public OpenAPIService() {
    Dotenv dotenv = Dotenv.load();
    this.apiKey = dotenv.get("API_KEY");
  }

  private final RestTemplate restTemplate = new RestTemplate();
  private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

  public String getDefinitionInMalagasy(String word) {
    String prompt = "Hazavao amin'ny teny malagasy ny hevitra sy famaritana ny teny hoe : " + word;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(apiKey);

    Map<String, Object> body =
        Map.of(
            "model",
            "gpt-3.5-turbo",
            "messages",
            List.of(Map.of("role", "user", "content", prompt)),
            "temperature",
            0.7);

    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, entity, Map.class);

    List<Map<String, Object>> choices =
        (List<Map<String, Object>>) response.getBody().get("choices");
    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

    return message.get("content").toString();
  }
}
