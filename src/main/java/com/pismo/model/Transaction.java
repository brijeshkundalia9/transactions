package com.pismo.model;

import com.pismo.contants.OperationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long transactionId;

  @ManyToOne(targetEntity = Account.class)
  private Account account;

  private OperationType operationTypeId;

  private Float amount;

  private LocalDateTime eventDate;

  private Float balance;

}
