package com.credibanco.assessment.card.api.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

  private final Object resourceId;
}
