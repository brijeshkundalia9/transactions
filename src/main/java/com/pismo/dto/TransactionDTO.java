package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.validator.annotation.TransactionAmountOperationType;
import lombok.Data;

@Data
@TransactionAmountOperationType
public class TransactionDTO {

  @JsonProperty(value = "account_id")
  private long accountId;

  @JsonProperty(value = "operation_type_id")
  private int operationTypeId;

  @JsonProperty(value = "amount")
  private float amount;

}
