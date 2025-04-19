package com.solid.patterns.factorymethod;

import com.solid.patterns.bank.BankAccount;
import com.solid.patterns.bank.CurrentAccount;
import com.solid.patterns.bank.SavingsAccount;

public class ParametrizedAccountFactory {

    public BankAccount createAccount(String type) {
        return switch (type.toLowerCase()) {
            case "courant" -> new CurrentAccount();
            case "epargne" -> new SavingsAccount();
            default -> throw new IllegalArgumentException("Type de compte inconnu!");
        };
    }

}
