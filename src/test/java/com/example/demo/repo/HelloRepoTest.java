/* (C)2023 */
package com.example.demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.repo.po.MessagePo;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HelloRepoTest {

    @Autowired
    HelloRepo helloRepo;

    @BeforeEach
    void setUp() {
        helloRepo.deleteAll();
    }

    @Test
    void shouldReturnMessageOrdered__givenOriginalMessageNotInOrder() {
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

        var messages = helloRepo.findByPersonOrderByMessageAsc("Smith");

        assertEquals(2, messages.size());
        assertEquals(List.of("A", "B"), messages);
    }
}
