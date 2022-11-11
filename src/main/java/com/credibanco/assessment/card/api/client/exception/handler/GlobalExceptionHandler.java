package com.credibanco.assessment.card.api.client.exception.handler;


import com.credibanco.assessment.card.api.client.exception.*;
import com.credibanco.assessment.card.api.client.exception.error.ErrorCode;
import com.credibanco.assessment.card.api.client.exception.error.ErrorDetails;
import com.credibanco.assessment.card.dto.response.EliminarTarjetaResponse;
import com.credibanco.assessment.card.dto.response.EnrolarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex){
    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.RESOURCE_NOT_FOUND)
        .detail(String.format("No se encontro la tarjeta con numero %s.",ex.getResourceId())).build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  @ExceptionHandler(CreateException.class)
  private ResponseEntity<ErrorDetails> handleCreateException(CreateException ex){
    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.BAD_REQUEST)
        .detail(ex.getConflictMessage())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(ConflictException.class)
  private ResponseEntity<ErrorDetails> handleConflict(ConflictException ex) {

    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.RESOURCE_ALREADY_EXISTS)
        .detail(ex.getConflictMessage())
        .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(ConflictEnroledException.class)
  private ResponseEntity<ErrorDetails> handleConflictEnroled (ConflictEnroledException ex) {
    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.RESOURCE_NOT_FOUND)
        .detail(ex.getConflictMessage())
        .object(EnrolarResponse.builder().codigoRespuesta("02")
            .mensaje("Numero de validacion invalido").build())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
   }

  @ExceptionHandler(ConflictEnroledException2.class)
  private ResponseEntity<ErrorDetails> handleConflictEnroled2 (ConflictEnroledException2 ex) {
    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.RESOURCE_NOT_FOUND)
        .detail(ex.getConflictMessage())
        .object(EnrolarResponse.builder().codigoRespuesta("01")
            .mensaje("Tarjeta no existe").build())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(ConflictDeleteException.class)
  private ResponseEntity<ErrorDetails> handleConflictDelete (ConflictDeleteException ex) {
    ErrorDetails error = ErrorDetails.builder()
        .code(ErrorCode.INVALID_CREDENTIALS)
        .detail(ex.getConflictMessage())
        .object(EliminarTarjetaResponse.builder()
            .codigoRespuesta("01")
            .mensaje("No se ha eliminado la tarjeta")
            .build())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }





}
