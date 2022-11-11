package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.api.client.TarjetaApi;
import com.credibanco.assessment.card.api.client.exception.CreateException;
import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import com.credibanco.assessment.card.mapper.TarjetaMapper;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.service.TarjetaService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tarjeta/")
@RequiredArgsConstructor
public class TarjetaController implements TarjetaApi {

  private final TarjetaMapper tarjetaMapper;
  private final TarjetaService tarjetaService;

  @Override
  @PostMapping("/crearTarjeta")
  public ResponseEntity<CrearTarjetaResponse> crearTarjeta(@RequestBody CrearTarjetaRequest request) {
    Tarjeta nT = tarjetaMapper.requestToEntity(request);
    CrearTarjetaResponse response = new CrearTarjetaResponse();
    if(tarjetaService.nuevaTarjeta(nT)!=null){
      response.setMensaje("Exito");
      response.setCodigoRespuesta("00");
      response.setPan(enmascararPan(nT.getPan()));
      response.setNumeroValidacion(nT.getNumeroValidacion());
    } else {
      response.setMensaje("Fallido");
      response.setCodigoRespuesta("01");
    }
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  private String enmascararPan(String pan){
    if(pan == null) {
      return null;
    }
    int s = pan.length()-4;
    int m = pan.length()-10;

    return pan.substring(0,6)+ Strings.repeat("*",m) + pan.substring(s);
  }
}
