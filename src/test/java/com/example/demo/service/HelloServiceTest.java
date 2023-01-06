/* (C)2023 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.example.demo.repo.HelloRepo;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {

  @Mock private HelloRepo helloRepo;

  @InjectMocks private HelloService helloService;

  @AfterEach
  void tearDown() {
    reset(helloRepo);
  }

  @Test
  void shouldReturnCombinedMessage__givenMoreThanOneMessageFound() {
    when(helloRepo.findByPersonOrderByMessageAsc(anyString()))
        .thenReturn(List.of("Hello", "World"));

    var message = helloService.getMessages("Smith");

    assertFalse(message.isEmpty());
    assertEquals(message.get(), "Hello World");
    verify(helloRepo).findByPersonOrderByMessageAsc("Smith");
  }

  @Test
  void shouldReturnEmpty__givenNoMessageFound() {
    when(helloRepo.findByPersonOrderByMessageAsc(anyString())).thenReturn(List.of());

    var message = helloService.getMessages("Smith");

    assertTrue(message.isEmpty());
    verify(helloRepo).findByPersonOrderByMessageAsc("Smith");
  }
}
