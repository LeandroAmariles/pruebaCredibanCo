package com.credibanco.assessment.card.model;

import com.credibanco.assessment.card.model.audit.Audit;
import com.credibanco.assessment.card.model.audit.AuditListener;
import com.credibanco.assessment.card.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "transaccion")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE transaccion SET is_active=false WHERE id=?")
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Transaccion implements Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "numero_referencia", nullable = false)
  private String numeroReferencia;

  @Column(name = "total_compra")
  private Double totalCompra;

  @Column(name = "direccion_compra")
  private String direccionCompra;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Tarjeta_id",referencedColumnName ="id")
  private Tarjeta tarjeta;

  @Embedded
  private Audit audit;
}
