package com.credibanco.assessment.card.impl;

import com.credibanco.assessment.card.api.client.exception.*;
import com.credibanco.assessment.card.dto.request.EliminarTarjetaRequest;
import com.credibanco.assessment.card.dto.request.EnrolarRequest;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.repository.TarjetaRepository;
import com.credibanco.assessment.card.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TarjetaServiceImpl implements TarjetaService {

  private final static String TIPO_DEBITO = "debito";

  private final static String TIPO_CREDITO = "credito";

  @Autowired private TarjetaRepository tarjetaRepository;


  @Override
  @Transactional
  public Tarjeta nuevaTarjeta(Tarjeta request) {
    try {
      if (sePuedeCrear(request.getPan()) && corroborarTipo(request.getTipo()) ) {
        request.setNumeroValidacion(String.valueOf((int) (Math.random() * 100) + 1));
        tarjetaRepository.save(request);
      }
    }catch (ConflictException e){
      throw new CreateException("Error creando la nueva tarjeta, "+ e.getConflictMessage());
    }
    return request ;
  }

  @Override
  @Transactional
  public Boolean enrolarTarjeta(EnrolarRequest request) {
    try{
      if(exist(request.getPan())){
        Tarjeta tarjeta = tarjetaRepository.findByPan(request.getPan()).get();
            String validation = tarjeta.getNumeroValidacion();
        if(request.getNumeroValidacion().equalsIgnoreCase(validation)){
          tarjeta.setEstado("Enrolada");
          tarjetaRepository.save(tarjeta);
        }
        else {
          throw new ConflictEnroledException("Numero de validacion invalido");
        }
      }
      else{
        throw new ConflictEnroledException2("La tarjeta no existe");
      }
    }catch(ConflictEnroledException e ){
        throw new ConflictEnroledException("Error enrolando la tarjeta," + e.getConflictMessage());
    }
    catch (ConflictEnroledException2 e){
      throw new ConflictEnroledException2("Error enrolando la tarjeta, "+ e.getConflictMessage());
    }
    return true;
  }

  @Override
  @Transactional(readOnly = true)
  public Tarjeta datosTarjeta(String pan) {
    return tarjetaRepository.findByPan(pan).orElseThrow(() -> new ConflictEnroledException2("No existe la tarjeta"));
  }

  @Override
  public Boolean borrarTarjeta(EliminarTarjetaRequest request) {
      if(validarDatosTarjeta(request.getPan(), request.getNumeroValidacion())){
        tarjetaRepository.deleteById(tarjetaRepository.findByPan(request.getPan()).get().getId());
        return true;
      }
      else {
        throw new ConflictDeleteException("Error de validacion al intentar borrar");
      }

  }

  private Boolean sePuedeCrear(String pan) throws ConflictException {
    if(tarjetaRepository.existsByPan(pan)){
      throw new ConflictException("Ya hay un prodcto con el numero de tarjeta "+pan);
    }
    return true;
  }

  private Boolean exist(String pan) throws ConflictException {
    if(!tarjetaRepository.existsByPan(pan)){
      throw  new ConflictEnroledException2("No exsite una tarjeta con el numero, "+pan);
    }
    return true;
  }

  private Boolean corroborarTipo(String tipo) throws ConflictException {
    if(tipo.equalsIgnoreCase(TIPO_DEBITO)||tipo.equalsIgnoreCase(TIPO_CREDITO)){
      return true;
    }
    throw new ConflictException("Tipo invalido");
  }

  private Boolean validarDatosTarjeta(String pan, String numeroValidacion ) throws ConflictDeleteException{
    Tarjeta tj = tarjetaRepository.findByPan(pan).orElseThrow(() -> new ConflictDeleteException("No existe la tarjeta"));
    try {
      if (tj.getPan().equalsIgnoreCase(pan)) {
        if (tj.getNumeroValidacion().equalsIgnoreCase(numeroValidacion)) {
          return true;
        }else {
          throw new ConflictDeleteException("Numero de Validacion incorrecto");
        }
      }
      else {
        throw new ConflictDeleteException("No existe la tarje con numero,"+ pan);
      }
    }catch (ConflictDeleteException e){
    throw new ConflictDeleteException("Error al eliminar la tarjeta,"+ e.getConflictMessage());
    }

  }


}
