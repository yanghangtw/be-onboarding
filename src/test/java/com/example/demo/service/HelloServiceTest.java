/* (C)2023 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.repo.HelloRepo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {

  @Mock private HelloRepo helloRepo;

  @InjectMocks private HelloService helloService;

  @Test
  void shouldReturnCombinedMessage__givenMoreThanOneMessageFound() {
    // TODO
  }

  @Test
  void shouldReturnEmpty__givenNoMessageFound() {
    when(helloRepo.findByPersonOrderByMessageAsc(anyString())).thenReturn(List.of());

    var message = helloService.getMessages("Smith");

    assertTrue(message.isEmpty());
    verify(helloRepo).findByPersonOrderByMessageAsc("Smith");
  }
}
