package com.credibanco.assessment.card.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearTarjetaResponse {

  private Long codigoRespuesta;

  private String mensaje;

  private String numeroValidacion;

  private String pan;

}
