/* (C)2023 */
package com.example.demo.web;

import com.example.demo.service.HelloService;
import com.example.demo.web.dto.GetMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/hello")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloResource {

  private final HelloService helloService;

  @GetMapping
  public ResponseEntity<GetMessageResponse> getMessage(
      @RequestParam(name = "person") String person) {
    var message = helloService.getMessages(person);
    return message
        .map(s -> ResponseEntity.ok(GetMessageResponse.of(person, s)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
