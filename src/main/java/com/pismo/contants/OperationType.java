package com.pismo.contants;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum OperationType {

  NORMAL_PURCHASE(1, "Normal Purchase"),
  PURCHASE_WITH_INSTALLMENTS(2, "Purchase with installments"),
  WITHDRAWAL(3, "Withdrawal"),
  CREDIT_VOUCHERS(4, "Credit Voucher");

  private final int id;
  private final String name;

  private static Map<Integer, OperationType> enumMap = new HashMap<>();

  OperationType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  static {
    for(OperationType op : OperationType.values()) {
      enumMap.put(op.id, op);
    }

  }

  public static OperationType getById(int id) {
    return enumMap.get(id);
  }

}
