package com.HEI.Hazavao.endpoint.rest.controller;

import com.HEI.Hazavao.OpenAPIService.OpenAPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
@RestController
@RequestMapping("/hazavao")
public class HazavaoController {

  private final OpenAPIService openAiService;

  @GetMapping
  public ResponseEntity<String> getDefinition(@RequestParam String teny) {
    String prompt = "Hazavao amin'ny teny malagasy ny hevitra sy famaritana ny teny hoe: " + teny;
    String definition = openAiService.getDefinitionInMalagasy(prompt);
    return ResponseEntity.ok(definition);
  }
}
