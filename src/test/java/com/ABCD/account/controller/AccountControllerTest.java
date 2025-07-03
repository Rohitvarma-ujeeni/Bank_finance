package com.ABCD.account.controller;

import com.ABCD.account.entity.Account;
import com.ABCD.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAccount() throws Exception {
        Account inputAccount = new Account();
        inputAccount.setAccountNo("A123");
        inputAccount.setName("Hari");
        inputAccount.setEmail("hari@example.com");
        inputAccount.setPolicy("Life");

        Mockito.when(accountService.create(any(Account.class))).thenReturn(inputAccount);

        mockMvc.perform(post("/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNo").value("A123"))
                .andExpect(jsonPath("$.name").value("Hari"))
                .andExpect(jsonPath("$.email").value("hari@example.com"))
                .andExpect(jsonPath("$.policy").value("Life"));
    }
}
