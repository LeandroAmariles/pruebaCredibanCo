package com.credibanco.assessment.card.impl;

import com.credibanco.assessment.card.api.client.exception.*;
import com.credibanco.assessment.card.dto.request.AnularTransaccionRequest;
import com.credibanco.assessment.card.dto.request.CrearTransaccionRequest;
import com.credibanco.assessment.card.mapper.TransaccionMapper;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.model.Transaccion;
import com.credibanco.assessment.card.repository.TarjetaRepository;
import com.credibanco.assessment.card.repository.TransaccionRepository;
import com.credibanco.assessment.card.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TransaccionServiceImpl implements TransaccionService {

  @Autowired
  private TransaccionRepository transaccionRepository;
  @Autowired
  private TarjetaRepository tarjetaRepository;

  @Autowired
  private TransaccionMapper transaccionMapper;

  private final static String ENROLADA = "Enrolada";

  @Override
  public Transaccion crearTransaccion(CrearTransaccionRequest request) {
    Transaccion tr = transaccionMapper.requestToEntity(request);
    try {
      if (validarEstadoTarjeta(request.getPan())) {
        tr.setDireccionCompra(request.getDireccion());
        tr.setTarjeta(tarjetaRepository.findByPan(request.getPan()).get());
        transaccionRepository.save(tr);
      }
    } catch (ConflictTransactionException e) {
      throw new ConflictTransactionException("Error creando la transaccion " + e.getConflictMessage());
    } catch (ConflictTransactionException2 e) {
      throw new ConflictTransactionException2("Error creando la transaccion " + e.getConflictMessage());
    }
    return tr;
  }

  @Override
  public Boolean anularTransaccion(AnularTransaccionRequest request) {
    Transaccion tr = transaccionRepository.findByNumeroReferencia(request.getNumeroRef()).orElseThrow(
        () -> new ConflictEnroledException("Transaccion con el numero de referencia " + request.getNumeroRef() + ", no existe"));
    if (validarTiempo(request.getNumeroRef()) && validarMontoCompra(tr.getTotalCompra(), request.getTotalCompra())
    && comprobraPan(request.getPan(), request.getNumeroRef())) {
      transaccionRepository.deleteById(tr.getId());
      return true;
    }
    return false;
  }


  private Boolean validarEstadoTarjeta(String pan) {
    if (tarjetaRepository.existsByPan(pan)) {
      if (tarjetaRepository.findByPan(pan).get().getEstado().equalsIgnoreCase(ENROLADA)) {
        return true;
      } else {
        throw new ConflictTransactionException2("Estado invalido");
      }
    } else {
      throw new ConflictTransactionException("Tarjeta no existe");
    }
  }

  private Boolean validarMontoCompra(Double monto1, Double monto2) {
    if (!monto1.toString().equalsIgnoreCase(monto2.toString())) {
      throw new ConflictTransactionException("Los Montos no son los mismos");
    }
    return true;
  }

  private Boolean validarTiempo(String numeroRef) {
    Transaccion tr = transaccionRepository.findByNumeroReferencia(numeroRef).orElseThrow(
        () -> new ConflictTransactionException("Transaccion con el numero de referencia " + numeroRef + "no existe")
    );
    LocalTime time = tr.getAudit().getCreatedAt().toLocalTime();
    System.out.println(time);
    LocalTime timeNow = LocalTime.now();
    System.out.println(timeNow);
    int valor = time.plusMinutes(5L).compareTo(timeNow);
    System.out.println((String.valueOf(valor)));
    if (valor >= 0) {
      return true;
    } else {
      throw new ConflictTransactionTimeException("Han pasado mas de 5 min, no se puede cancelar la transaccion");
    }


  }

  private Boolean comprobraPan(String pan, String numeroRef) {
    Transaccion tr = transaccionRepository.findByNumeroReferencia(numeroRef).get();
    Tarjeta tj = tarjetaRepository.findByPan(pan).orElseThrow(()-> new ConflictTransactionException("Tarjeta no encontrada"));
    Long idTj = tj.getId();
    Long idTarjetaTransaccion = tr.getTarjeta().getId();

    if(idTj != idTarjetaTransaccion){
      throw new ConflictTransactionException("Los numeros de la tarjeta no coinciden");
    }
    return true;
  }
}
