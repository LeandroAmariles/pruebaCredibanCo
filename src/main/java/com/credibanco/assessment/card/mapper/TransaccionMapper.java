package com.credibanco.assessment.card.mapper;

import com.credibanco.assessment.card.dto.request.CrearTransaccionRequest;
import com.credibanco.assessment.card.model.Transaccion;
import org.mapstruct.Mapper;

@Mapper
public interface TransaccionMapper {

  Transaccion requestToEntity(CrearTransaccionRequest request);
}
