package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class AccountDTO {

  @JsonProperty(value = "account_id")
  @Null(message = "Account id should be blank")
  private Long id;

  @JsonProperty(value = "document_number")
  private String documentNumber;

}
