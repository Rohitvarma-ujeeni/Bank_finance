package com.ABCD.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ABCD.account.entity.Account;
import com.ABCD.account.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(service.create(account));
    }

    @PutMapping("/updateAccount/{accountNo}")
    public ResponseEntity<?> updateAccount(@PathVariable String accountNo, @RequestBody Account account) {
        return ResponseEntity.ok(service.update(accountNo, account));
    }

    @GetMapping("/viewPolicy/{accountNo}")
    public ResponseEntity<?> viewPolicy(@PathVariable String accountNo) {
        return service.view(accountNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletePolicy/{accountNo}")
    public ResponseEntity<?> deletePolicy(@PathVariable String accountNo) {
        service.delete(accountNo);
        return ResponseEntity.ok("Deleted successfully");
    }
}
