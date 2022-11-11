package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.api.client.TarjetaApi;
import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import com.credibanco.assessment.card.mapper.TarjetaMapper;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TarjetaController implements TarjetaApi {

  private final TarjetaMapper tarjetaMapper;
  private final TarjetaService tarjetaService;

  @Override
  public ResponseEntity<CrearTarjetaResponse> crearTarjeta(CrearTarjetaRequest request) {
    Tarjeta nT = tarjetaMapper.requestToEntity(request);
    return null;
  }
}
