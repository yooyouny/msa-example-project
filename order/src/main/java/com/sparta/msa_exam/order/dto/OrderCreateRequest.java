package com.sparta.msa_exam.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderCreateRequest(
    @NotBlank String name,
    @NotNull @Valid List<OrderProductRequest> productIds
) {}

