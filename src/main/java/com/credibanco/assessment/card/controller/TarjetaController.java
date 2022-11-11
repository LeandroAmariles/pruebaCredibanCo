package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.api.client.TarjetaApi;
import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.request.EliminarTarjetaRequest;
import com.credibanco.assessment.card.dto.request.EnrolarRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import com.credibanco.assessment.card.dto.response.EliminarTarjetaResponse;
import com.credibanco.assessment.card.dto.response.EnrolarResponse;
import com.credibanco.assessment.card.dto.response.TarjetaResponse;
import com.credibanco.assessment.card.mapper.TarjetaMapper;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.service.TarjetaService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    if (tarjetaService.nuevaTarjeta(nT) != null) {
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
  @Override
  @PatchMapping("/enrolarTarjeta")
  public ResponseEntity<EnrolarResponse> enrolarTarjeta(@RequestBody EnrolarRequest request) {
    EnrolarResponse response = new EnrolarResponse();
    if (tarjetaService.enrolarTarjeta(request)) {
      response.setCodigoRespuesta("00");
      response.setMensaje("Exito");
      response.setPan(enmascararPan(request.getPan()));
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @Override
  @GetMapping("/tarjeta/{pan}")
  public ResponseEntity<TarjetaResponse> obtenerDatosTarjeta(@PathVariable String pan) {

    TarjetaResponse response = tarjetaMapper.entityToResponse2(
        tarjetaService.datosTarjeta(pan)
    );
    response.setPan(enmascararPan(pan));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @Override
  @DeleteMapping("/tarjeta")
  public ResponseEntity<EliminarTarjetaResponse> eliminarTarjeta(@RequestBody EliminarTarjetaRequest request){
    EliminarTarjetaResponse response = new EliminarTarjetaResponse();
    if(tarjetaService.borrarTarjeta(request)){
      response.setCodigoRespuesta("00");
      response.setMensaje("Se ha eliminado la tarjeta");
    }
    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
  }

  private String enmascararPan(String pan) {
    if (pan == null) {
      return null;
    }
    int s = pan.length() - 4;
    int m = pan.length() - 10;

    return pan.substring(0, 6) + Strings.repeat("*", m) + pan.substring(s);
  }
}
