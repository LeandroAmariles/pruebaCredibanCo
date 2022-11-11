package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EnrolarRequest {

  @JsonProperty("pan")
  @NotBlank
  private String pan;

  @JsonProperty("numero_validacion")
  @NotBlank
  private String numeroValidacion;
}
