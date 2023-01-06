/* (C)2023 */
package com.example.demo.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.service.HelloService;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HelloResource.class)
class HelloResourceTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private HelloService helloService;

  @AfterEach
  void tearDown() {
    reset(helloService);
  }

  @Test
  void shouldReturn200__givenFoundMessageForThatPerson() throws Exception {
    when(helloService.getMessages(anyString())).thenReturn(Optional.of("Hello World"));

    mockMvc
        .perform(get("/v1/hello?person=Smith"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.name", is("Smith")))
        .andExpect(jsonPath("$.message", is("Hello World")));

    verify(helloService).getMessages("Smith");
  }

  @Test
  void shouldReturn404__givenNoMessageFoundForThatPerson() throws Exception {
    when(helloService.getMessages(anyString())).thenReturn(Optional.empty());

    mockMvc.perform(get("/v1/hello?person=Smith")).andExpect(status().is(404));

    verify(helloService).getMessages("Smith");
  }
}
