/* (C)2023 */
package com.example.demo.web.dto;

import lombok.Data;

@Data(staticConstructor = "of")
public class GetMessageResponse {
  private final String name;
  private final String message;
}
