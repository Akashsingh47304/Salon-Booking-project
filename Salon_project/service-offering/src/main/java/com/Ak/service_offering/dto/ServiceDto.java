package com.Ak.service_offering.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private Long id;
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
    private Long salonId;
    private Long categoryId;
    private String image;
}
