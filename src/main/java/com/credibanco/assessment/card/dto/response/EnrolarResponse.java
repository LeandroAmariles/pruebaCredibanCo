package com.credibanco.assessment.card.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrolarResponse {

  private String codigoRespuesta;

  private String mensaje;

  private String pan;
}
