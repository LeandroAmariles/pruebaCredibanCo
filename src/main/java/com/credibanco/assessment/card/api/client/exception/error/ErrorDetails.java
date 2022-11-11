package com.credibanco.assessment.card.api.client.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;


import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {
  @NotNull ErrorCode code;
  @NotNull String detail;
}
