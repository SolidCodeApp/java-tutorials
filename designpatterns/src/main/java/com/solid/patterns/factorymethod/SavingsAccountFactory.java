package com.solid.patterns.factorymethod;

import com.solid.patterns.bank.BankAccount;
import com.solid.patterns.bank.SavingsAccount;

public class SavingsAccountFactory extends AccountFactory {

    @Override
    public BankAccount createAccount() {
        return new SavingsAccount();
    }

}
