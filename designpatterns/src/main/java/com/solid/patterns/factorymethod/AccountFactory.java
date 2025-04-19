package com.solid.patterns.factorymethod;

import com.solid.patterns.bank.BankAccount;

public abstract class AccountFactory {

    public void openAccount() {
        BankAccount account = createAccount();
        account.displayType();
    }

    public abstract BankAccount createAccount();

}
