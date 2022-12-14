package com.credibanco.assessment.card.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearTarjetaResponse {

  private String codigoRespuesta;

  private String mensaje;

  private String numeroValidacion;

  private String pan;

}
