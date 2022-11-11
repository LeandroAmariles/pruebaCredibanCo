package com.credibanco.assessment.card.api.client.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  INVALID_FIELD_VALUE("El campo no es valido"),
  RESOURCE_NOT_FOUND("El recurso solicitado no fue encontrado"),
  BAD_REQUEST("El server no puede retornar la respuesta "),

  INVALID_CREDENTIALS("Credenciales invalidas"),
  RESOURCE_ALREADY_EXISTS("Este recurso ya existe"),
  ILLEGAL_ARGUMENT("Ha pasado un argumento inapropiado o ilegal");

  private final String defaultMessage;
}
