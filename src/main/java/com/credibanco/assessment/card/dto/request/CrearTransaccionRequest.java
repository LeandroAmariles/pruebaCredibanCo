package com.credibanco.assessment.card.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearTransaccionRequest {

  @NotNull
  private String pan;

  @JsonProperty("referencia")
  @Size(min = 6, max = 6,message = "Debe ser de 6 digitos")
  @NotBlank(message = "Numero de referencia requerido")
  private String numeroReferencia;

  private Double totalCompra;

  private String direccion;
}
