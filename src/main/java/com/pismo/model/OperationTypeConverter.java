package com.pismo.model;

import com.pismo.contants.OperationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(OperationType operationType) {
    return operationType.getId();
  }

  @Override
  public OperationType convertToEntityAttribute(Integer id) {
    return OperationType.getById(id);
  }
}