package com.sparta.msa_exam.product;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.msa_exam.product.controller.ProductRestController;
import com.sparta.msa_exam.product.dto.ProductCreateRequest;
import com.sparta.msa_exam.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@WebMvcTest(ProductRestController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private ProductService productService;

  @Test
  @DisplayName("상품 추가 시 이름이 공란이라 예외가 발생한다")
  void 이름이_공란일_때_상품추가_요청시_예외가_발생한다() throws Exception {
    ProductCreateRequest requestDto = new ProductCreateRequest("", 123445);

    mockMvc
        .perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)))
        .andExpect(
            result ->
                assertTrue(
                    result.getResolvedException() instanceof MethodArgumentNotValidException))
        .andDo(print());
  }
  @Test
  @DisplayName("상품 추가 시 가격이 음수값이라 예외가 발생한다")
  void 가격이_음수일_때_상품추가_요청시_예외가_발생한다() throws Exception {
    ProductCreateRequest requestDto = new ProductCreateRequest("망고빙수", -1);

    mockMvc
        .perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDto)))
        .andExpect(
            result ->
                assertTrue(
                    result.getResolvedException() instanceof MethodArgumentNotValidException))
        .andDo(print());
  }
}
