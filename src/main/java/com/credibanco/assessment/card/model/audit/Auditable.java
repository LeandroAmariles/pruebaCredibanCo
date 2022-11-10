package com.credibanco.assessment.card.model.audit;

public interface Auditable {

  Audit getAudit();
  void setAudit(Audit audit);
}
