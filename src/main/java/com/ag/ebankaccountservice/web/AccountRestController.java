package com.ag.ebankaccountservice.web;

import com.ag.ebankaccountservice.entities.BankAccount;
import com.ag.ebankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable  String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        if (bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@RequestBody BankAccount bankAccount, @PathVariable String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        if (bankAccount.getBalance()!=null) {
            account.setBalance(bankAccount.getBalance());
        }
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
