package com.credibanco.assessment.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EliminarTarjetaResponse {

  private String codigoRespuesta;

  private String mensaje;

}
