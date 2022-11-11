package com.credibanco.assessment.card.mapper;

import com.credibanco.assessment.card.dto.request.CrearTarjetaRequest;
import com.credibanco.assessment.card.dto.response.CrearTarjetaResponse;
import com.credibanco.assessment.card.model.Tarjeta;
import org.mapstruct.Mapper;

@Mapper
public interface TarjetaMapper {

  CrearTarjetaResponse entityToResponse(Tarjeta tarjeta);

  Tarjeta requestToEntity(CrearTarjetaRequest request);
}
