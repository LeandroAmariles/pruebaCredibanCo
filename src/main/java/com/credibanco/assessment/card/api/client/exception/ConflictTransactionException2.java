package com.credibanco.assessment.card.api.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConflictTransactionException2 extends RuntimeException{

  private final String conflictMessage;
}
