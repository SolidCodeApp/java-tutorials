package com.solid.patterns.factorymethod;

import com.solid.patterns.bank.BankAccount;
import com.solid.patterns.bank.CurrentAccount;
import com.solid.patterns.bank.SavingsAccount;

public class StaticAccountFactory {
    public static BankAccount createAccount(String type) {
        return switch (type.toLowerCase()) {
            case "courant" -> new CurrentAccount();
            case "epargne" -> new SavingsAccount();
            default -> throw new IllegalArgumentException("Type de compte inconnu!");
        };
    }
}
