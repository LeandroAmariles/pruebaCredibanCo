package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EliminarTarjetaRequest {

  @JsonProperty("pan")
  @NotBlank
  private String pan;

  @JsonProperty("numero_validacion")
  @NotBlank
  private String numeroValidacion;
}
