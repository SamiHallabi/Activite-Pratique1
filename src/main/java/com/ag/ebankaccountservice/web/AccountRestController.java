package com.ag.ebankaccountservice.web;

import com.ag.ebankaccountservice.entities.BankAccount;
import com.ag.ebankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@RequestBody BankAccount bankAccount, @PathVariable String id) {
        BankAccount bankAccount1 = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        if (bankAccount1.getBalance()!=null) {
            bankAccount1.setBalance(bankAccount.getBalance());
        }
        if (bankAccount1.getBalance()!=null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount1.getCurrency()!=null) bankAccount1.setCurrency(bankAccount.getCurrency());
        if (bankAccount1.getCreatedAt()!=null) bankAccount1.setCreatedAt(new Date());
        if (bankAccount1.getType()!=null) bankAccount1.setType(bankAccount.getType());
        return bankAccountRepository.save(bankAccount1);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
