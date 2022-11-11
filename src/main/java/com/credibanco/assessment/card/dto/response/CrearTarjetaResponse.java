package com.credibanco.assessment.card.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearTarjetaResponse {

  private String codigoRespuesta;

  private String mensaje;

  private String numeroValidacion;

  private String pan;

}
