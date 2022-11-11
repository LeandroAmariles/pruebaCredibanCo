package com.credibanco.assessment.card.api.client;

import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TarjetaApi {

ResponseEntity<CrearTarjetaResponse> crearTarjeta(@RequestBody CrearTarjetaRequest request);
}
