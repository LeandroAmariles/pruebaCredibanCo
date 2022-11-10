package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CrearTarjetaRequest {

  @JsonProperty("pan")
  @NotNull
  private String pan;

  @NotNull
  @JsonProperty("titular")
  private String titular;

  @NotNull
  @JsonProperty("cedula")
  @Size(min=10, max = 15)
  private String cedula;

  @NotNull
  @JsonProperty("tipo")
  private String tipo;

  @NotNull
  @JsonProperty("telefono")
  @Size(min = 10, max = 10)
  private String telefono;



}
