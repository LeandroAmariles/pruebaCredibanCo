package com.credibanco.assessment.card.model.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Audit {

  @Column(name = "is_active", nullable = false)
  private Boolean isActive = Boolean.TRUE;

  @Column(name = "created_at", nullable = false, updatable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdAt;

}
