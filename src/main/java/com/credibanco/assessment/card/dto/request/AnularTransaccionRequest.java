package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnularTransaccionRequest {

  @JsonProperty("pan")
  @NotBlank
  private String pan;

  @JsonProperty("numero_referencia")
  @NotBlank
  private String numeroRef;

  @JsonProperty("total_compra")
  @NotNull
  private Double totalCompra;
}
