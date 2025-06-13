package com.HEI.Hazavao.endpoint.rest.controller;

import com.HEI.Hazavao.OpenAPIService.OpenAPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/hazavao")
public class HazavaoController {

  private final OpenAPIService openAPIService;

  @GetMapping
  public ResponseEntity<String> getDefinition(@RequestParam(name = "teny") String teny) {
    if (teny == null || teny.trim().isEmpty()) {
      return ResponseEntity.badRequest().body("Teny (mot) tsy maintsy omena.");
    }
    String definition = openAPIService.getDefinitionInMalagasy(teny);
    return ResponseEntity.ok(definition);
  }
}
