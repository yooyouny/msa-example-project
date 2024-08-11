package com.sparta.msa_exam.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(@NotBlank String name, @Min(value = 0) int supplyPrice) {}
