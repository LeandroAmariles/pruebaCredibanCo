package com.credibanco.assessment.card.impl;

import com.credibanco.assessment.card.api.client.exception.ConflictException;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.repository.TarjetaRepository;
import com.credibanco.assessment.card.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class TarjetaServiceImpl implements TarjetaService {

  @Autowired private TarjetaRepository tarjetaRepository;


  @Override
  @Transactional
  public Tarjeta nuevaTarjeta(Tarjeta request) {
    if(sePuedeCrear(request.getPan())) {
      request.setNumeroValidacion((int) (Math.random() * 100) + 1);
      tarjetaRepository.save(request);
    }
    //Hacer que retorne el response con el mensaje y
    // todo desde aqui
    return request;
  }

  private Boolean sePuedeCrear(String pan){
    if(tarjetaRepository.findByPan(pan)!=null){
      throw new ConflictException("Ya hay un prodcto con el numero de tarjeta"+pan);
    }
    return true;
  }
}
