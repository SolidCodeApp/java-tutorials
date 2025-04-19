package com.solid.patterns.bank;

public class CurrentAccount implements BankAccount {

    @Override
    public void displayType() {
        System.out.println("Je suis un compte courant!");
    }

}
