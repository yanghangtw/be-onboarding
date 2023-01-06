/* (C)2023 */
package com.example.demo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.repo.HelloRepo;
import com.example.demo.repo.po.MessagePo;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class BeOnboardingApplicationTest {

    @Autowired
    HelloRepo helloRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        helloRepo.deleteAll();
    }

    @Test
    void shouldReturnMessageOrdered__givenOriginalMessageNotInOrder() throws Exception {
        helloRepo.save(
            MessagePo
                .builder()
                .id(UUID.randomUUID().toString())
                .person("Smith")
                .message("B")
                .build());
        helloRepo.save(
            MessagePo
                .builder()
                .id(UUID.randomUUID().toString())
                .person("Smith")
                .message("A")
                .build());

        mockMvc
            .perform(get("/v1/hello?person=Smith"))
            .andExpect(status().isOk())
            .andExpect(content().json(
                "{\"name\":\"Smith\",\"message\":\"A B\"}",
                false
            ));
    }
}
