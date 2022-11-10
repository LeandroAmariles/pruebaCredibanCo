package com.credibanco.assessment.card.model.audit;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;


public class AuditListener {

  @PrePersist
  public void setCreatedOn(Auditable auditable){
    Audit audit = auditable.getAudit();

    if(audit == null){
      audit = new Audit();
      auditable.setAudit(audit);
    }
    audit.setCreatedAt(LocalDateTime.now());

  }

}
