package com.credibanco.assessment.card.api.client;

import com.credibanco.assessment.card.dto.request.AnularTransaccionRequest;
import com.credibanco.assessment.card.dto.request.CrearTransaccionRequest;
import com.credibanco.assessment.card.dto.response.AnularTransaccionResponse;
import com.credibanco.assessment.card.dto.response.CrearTransaccionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransaccionApi {

  ResponseEntity<CrearTransaccionResponse> nuevaTransaccion(@RequestBody CrearTransaccionRequest request);

  ResponseEntity<AnularTransaccionResponse> anularTransaccion(@RequestBody AnularTransaccionRequest request);
}
