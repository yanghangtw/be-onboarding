/* (C)2023 */
package com.example.demo.service;

import com.example.demo.repo.HelloRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloService {

  private final HelloRepo helloRepo;

  public Optional<String> getMessages(String person) {
    var messages = helloRepo.findByPersonOrderByMessageAsc(person);
    return messages.isEmpty() ? Optional.empty() : Optional.of("TODO");
  }
}
