package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.request.EnrolarRequest;
import com.credibanco.assessment.card.model.Tarjeta;


public interface TarjetaService {

  Tarjeta nuevaTarjeta(Tarjeta request);

  Boolean enrolarTarjeta(EnrolarRequest request);


}
