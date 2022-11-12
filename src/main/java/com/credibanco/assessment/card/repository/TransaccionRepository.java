package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

  Optional<Transaccion> findByNumeroReferencia(String numerReferencia);
}
