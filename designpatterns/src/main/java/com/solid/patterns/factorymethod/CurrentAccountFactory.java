package com.solid.patterns.factorymethod;

import com.solid.patterns.bank.BankAccount;
import com.solid.patterns.bank.CurrentAccount;

public class CurrentAccountFactory extends AccountFactory {

    @Override
    public BankAccount createAccount() {
        return new CurrentAccount();
    }

}
