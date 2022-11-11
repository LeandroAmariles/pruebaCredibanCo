package com.credibanco.assessment.card.impl;

import com.credibanco.assessment.card.api.client.exception.ConflictException;
import com.credibanco.assessment.card.api.client.exception.CreateException;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.repository.TarjetaRepository;
import com.credibanco.assessment.card.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class TarjetaServiceImpl implements TarjetaService {

  private final static String TIPO_DEBITO = "debito";

  private final static String TIPO_CREDITO = "credito";

  @Autowired private TarjetaRepository tarjetaRepository;


  @Override
  @Transactional
  public Tarjeta nuevaTarjeta(Tarjeta request) {
    try {
      if (validarExistencia(request.getPan()) && corroborarTipo(request.getTipo()) ) {
        request.setNumeroValidacion(String.valueOf((int) (Math.random() * 100) + 1));
        tarjetaRepository.save(request);
      }
    }catch (ConflictException e){
      throw new CreateException("Error creando la nueva tarjeta, "+ e.getConflictMessage());
    }
    return request ;
  }

  private Boolean validarExistencia(String pan) throws ConflictException {
    if(tarjetaRepository.findByPan(pan)!=null){
      throw new ConflictException("Ya hay un prodcto con el numero de tarjeta "+pan);
    }
    return true;
  }

  private Boolean corroborarTipo(String tipo) throws ConflictException {
    if(tipo.equalsIgnoreCase(TIPO_DEBITO)||tipo.equalsIgnoreCase(TIPO_CREDITO)){
      return true;
    }
    throw new ConflictException("Tipo invalido");
  }
}
