package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.exception.AccountNotFoundException;  
import com.dws.challenge.exception.InsufficientBalanceException;
import lombok.Getter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  public void transferMoney(Long accountFromId, Long accountToId, BigDecimal amount) {
	    // Find 'from' account
	    Account fromAccount = accountsRepository.getAccount(accountFromId.toString());
	    if (fromAccount == null) {
	      throw new AccountNotFoundException("Account not found: " + accountFromId);
	    }

	    // Find 'to' account
	    Account toAccount = accountsRepository.getAccount(accountToId.toString());
	    if (toAccount == null) {
	      throw new AccountNotFoundException("Account not found: " + accountToId);
	    }

	    // Validate amount
	    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	      throw new IllegalArgumentException("Amount must be positive");
	    }

	    // Thread safety with synchronization
	    synchronized (fromAccount) {
	      if (fromAccount.getBalance().compareTo(amount) < 0) {
	        throw new InsufficientBalanceException("Insufficient balance in account: " + accountFromId);
	      }

	      // Update balances
	      fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
	      toAccount.setBalance(toAccount.getBalance().add(amount));

	      // Save updated accounts
	      accountsRepository.createAccount(fromAccount);
	      accountsRepository.createAccount(toAccount);
	    }

	    // Send notifications
	    // Assuming NotificationService is autowired in this class
	    // notificationService.notifyAboutTransfer(fromAccount.getId(), toAccount.getId(), amount);
	    // notificationService.notifyAboutTransfer(toAccount.getId(), fromAccount.getId(), amount);
	  }
	}
  

