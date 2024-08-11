package com.sparta.msa_exam.order.dto;

import jakarta.validation.constraints.NotNull;

public record OrderProductRequest(
  @NotNull Long productId
) {}
