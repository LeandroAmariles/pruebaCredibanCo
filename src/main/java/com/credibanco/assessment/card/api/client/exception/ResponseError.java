package com.credibanco.assessment.card.api.client.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseError {

  private String codigoRespuesta;

  private String mensaje;
}
