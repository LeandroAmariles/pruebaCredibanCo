package com.credibanco.assessment.card.api.client.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConflictTransactionTimeException extends RuntimeException{
  private final String conflictMessage;
}
