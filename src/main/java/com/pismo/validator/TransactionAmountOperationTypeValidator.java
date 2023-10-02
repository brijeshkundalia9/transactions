package com.pismo.validator;

import com.pismo.contants.OperationType;
import com.pismo.dto.TransactionDTO;
import com.pismo.validator.annotation.TransactionAmountOperationType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TransactionAmountOperationTypeValidator implements
    ConstraintValidator<TransactionAmountOperationType, TransactionDTO> {


  @Override
  public void initialize(TransactionAmountOperationType constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(TransactionDTO transactionDTO,
      ConstraintValidatorContext constraintValidatorContext) {

    OperationType op = OperationType.getById(transactionDTO.getOperationTypeId());
    float amount = transactionDTO.getAmount();

    return switch (op) {
      case NORMAL_PURCHASE, PURCHASE_WITH_INSTALLMENTS, WITHDRAWAL -> (amount < 0);
      case CREDIT_VOUCHERS -> (amount > 0);
    };
  }
}
