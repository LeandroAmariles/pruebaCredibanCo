package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrolarRequest {

  @JsonProperty("pan")
  @NotBlank
  private String pan;

  @JsonProperty("numero_validacion")
  @NotBlank
  private String numeroValidacion;
}
