package com.pismo.service;

import com.pismo.dto.AccountDTO;

public interface AccountService {

  /**
   * Creates new account.
   * @param accountDTO
   * @return accountDTO object
   */
  AccountDTO addAccount(AccountDTO accountDTO);

  /**
   * Fetches existing account for provided accountId
   *
   * @param accountId
   * @return accountDTO object
   */
  AccountDTO getAccount(Long accountId);
}
