package com.pismo.validator.annotation;

import com.pismo.validator.TransactionAmountOperationTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TransactionAmountOperationTypeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionAmountOperationType {

  String message() default
      "Transactions of type purchase and withdrawal are registered with negative amounts, while "
          + "transactions of credit voucher are registered with positive value.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}