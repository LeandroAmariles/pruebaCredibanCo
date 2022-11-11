package com.credibanco.assessment.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaResponse {

  private String pan;

  private String titular;

  private String telefono;

  private String estado;


}

