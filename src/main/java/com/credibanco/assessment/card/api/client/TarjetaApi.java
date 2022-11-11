package com.credibanco.assessment.card.api.client;

import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.request.EliminarTarjetaRequest;
import com.credibanco.assessment.card.dto.request.EnrolarRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import com.credibanco.assessment.card.dto.response.EliminarTarjetaResponse;
import com.credibanco.assessment.card.dto.response.EnrolarResponse;
import com.credibanco.assessment.card.dto.response.TarjetaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TarjetaApi {

ResponseEntity<CrearTarjetaResponse> crearTarjeta(@RequestBody CrearTarjetaRequest request);

ResponseEntity<EnrolarResponse> enrolarTarjeta(@RequestBody EnrolarRequest request);

ResponseEntity<TarjetaResponse> obtenerDatosTarjeta(@PathVariable String pan);

ResponseEntity<EliminarTarjetaResponse> eliminarTarjeta(@RequestBody EliminarTarjetaRequest request);
}
