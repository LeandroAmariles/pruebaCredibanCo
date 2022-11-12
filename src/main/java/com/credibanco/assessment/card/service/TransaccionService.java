package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.request.AnularTransaccionRequest;
import com.credibanco.assessment.card.dto.request.CrearTransaccionRequest;
import com.credibanco.assessment.card.model.Transaccion;

public interface TransaccionService {

  Transaccion crearTransaccion(CrearTransaccionRequest request);

  Boolean anularTransaccion(AnularTransaccionRequest request);
}
