package com.solid.patterns.bank;

public class SavingsAccount implements BankAccount {

    @Override
    public void displayType() {
        System.out.println("Je suis un compte épargne!");
    }

}
