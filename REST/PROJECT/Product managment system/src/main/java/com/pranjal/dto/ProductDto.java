package com.pranjal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ProductDto {
  @NotBlank
    private String name;
  @NotEmpty
  @Size(min = 2, max = 100, message = "description min & max 2-100")
  private String description;
    private Double price;

    private Integer quantity;
}
