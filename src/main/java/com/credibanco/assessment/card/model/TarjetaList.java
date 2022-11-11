package com.credibanco.assessment.card.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TarjetaList extends PageImpl<Tarjeta> {

  public TarjetaList(List<Tarjeta> content, Pageable pageable, long total){
    super(content, pageable, total);
  }

}
