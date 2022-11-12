package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.api.client.TransaccionApi;
import com.credibanco.assessment.card.dto.request.AnularTransaccionRequest;
import com.credibanco.assessment.card.dto.request.CrearTransaccionRequest;
import com.credibanco.assessment.card.dto.response.AnularTransaccionResponse;
import com.credibanco.assessment.card.dto.response.CrearTransaccionResponse;
import com.credibanco.assessment.card.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController implements TransaccionApi {

  private final TransaccionService transaccionService;

  @Override
  @PostMapping("/crearTransaccion")
  public ResponseEntity<CrearTransaccionResponse> nuevaTransaccion(@Valid @RequestBody CrearTransaccionRequest request) {
    CrearTransaccionResponse response = new CrearTransaccionResponse();
    if(transaccionService.crearTransaccion(request)!=null){
      response.setMensaje("Compra Exitosa");
      response.setCodigoRespuesta("00");
      response.setEstadoTransaccion("Aprovada");
      response.setNumeroReferencia(request.getNumeroReferencia());
    }
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/anularTransaccion")
  public ResponseEntity<AnularTransaccionResponse> anularTransaccion(@Valid @RequestBody AnularTransaccionRequest request) {
    AnularTransaccionResponse response = new AnularTransaccionResponse();
    if(transaccionService.anularTransaccion(request)){
      response.setMensaje("Compra Anulada");
      response.setCodigoRespuesta("00");
      response.setNumeroReferencia(request.getNumeroRef());
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
