package com.pismo.service;

import com.pismo.dto.AccountDTO;

public interface AccountService {

  AccountDTO addAccount(AccountDTO accountDTO);

  AccountDTO getAccount(Long accountId);
}
