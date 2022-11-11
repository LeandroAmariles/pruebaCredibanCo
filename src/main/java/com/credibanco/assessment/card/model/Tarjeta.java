package com.credibanco.assessment.card.model;


import com.credibanco.assessment.card.model.audit.Audit;
import com.credibanco.assessment.card.model.audit.AuditListener;
import com.credibanco.assessment.card.model.audit.Auditable;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@ToString
@Table(name = "tarjeta")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE tajeta SET is_active=false WHERE tarjeta_id=?")
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Tarjeta implements Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "numero_validacion")
  private String numeroValidacion;

  @Column(name = "numero_tarjeta")
  @Size(min = 16, max = 19)
  private String pan;

  @Column(name = "titular")
  private String titular;


  @Column(name = "cedula")
  @Size(min = 10, max = 15)
  private String cedula;

  @Column(name = "telefono")
  private String telefono;

  @Column(name = "tipo")
  private String tipo;

  @Embedded
  private Audit audit;
}
