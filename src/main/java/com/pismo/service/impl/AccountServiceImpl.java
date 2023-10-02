package com.pismo.service.impl;

import com.pismo.convertor.DTOToModelConvertor;
import com.pismo.convertor.ModelToDTOConvertor;
import com.pismo.dto.AccountDTO;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.model.Account;
import com.pismo.repository.AccountRepository;
import com.pismo.service.AccountService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private DTOToModelConvertor dtoToModelConvertor;

  @Autowired
  private ModelToDTOConvertor modelToDTOConvertor;

  @Override
  public AccountDTO addAccount(AccountDTO accountDTO) {
    log.info("Creating a new account");
    Account account = dtoToModelConvertor.convertAccountDTOToAccount(accountDTO);
    account = accountRepository.save(account);
    log.info("Account created with id : {}", account.getId());
    return modelToDTOConvertor.convertAccountToAccountDTO(account);
  }

  @Override
  public AccountDTO getAccount(Long accountId) {
    log.info("Fetching account by id : {}", accountId);
    Optional<Account> account = accountRepository.findById(accountId);
    return account.map(value -> modelToDTOConvertor.convertAccountToAccountDTO(value))
        .orElseThrow(() -> {
              log.error("Account not found for id : {}", accountId);
              return new AccountNotFoundException();
            }
        );
  }
}
