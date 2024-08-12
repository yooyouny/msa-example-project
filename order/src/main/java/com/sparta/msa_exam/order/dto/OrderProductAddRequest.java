package com.sparta.msa_exam.order.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderProductAddRequest(
  @NotNull List<Long> productIds
) {}
