package com.onerivet.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOnDto {
    private Integer addOnId;
    private String addOn;
   private BigDecimal price;
}
