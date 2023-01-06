/* (C)2023 */
package com.example.demo.repo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "message")
public class MessagePo {
  @Id
  @Column(length = 47)
  private String id;

  @Column private String person;
  @Column private String message;
}
