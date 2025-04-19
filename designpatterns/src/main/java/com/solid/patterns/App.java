package com.solid.patterns;

import com.solid.patterns.bank.BankAccount;
import com.solid.patterns.bank.CurrentAccount;
import com.solid.patterns.bank.SavingsAccount;
import com.solid.patterns.factorymethod.AccountFactory;
import com.solid.patterns.factorymethod.CurrentAccountFactory;
import com.solid.patterns.factorymethod.ParametrizedAccountFactory;
import com.solid.patterns.factorymethod.RegistryAccountFactory;
import com.solid.patterns.factorymethod.SavingsAccountFactory;
import com.solid.patterns.factorymethod.StaticAccountFactory;
import com.solid.patterns.singleton.DatabaseConnection;
import com.solid.patterns.singleton.UserSession;

public class App {
    public static void main(String[] args) {
        // runSingletonPatterns();
        runFactoryMethodPatterns();
    }

    /**
     * This method allows to run the 4 main variation of Factory Method.
     * 1. Factory Method (classic)
     * 2. Parametized Factory Method
     * 3. Static Factory Method
     * 4. Registry-based Factory Method
     */
    public static void runFactoryMethodPatterns() {
        System.out.println("\n-------------------Classic Factory -----------------------");
        // Factory method (classic)
        AccountFactory savingsAccountFactory = new SavingsAccountFactory();
        savingsAccountFactory.openAccount();

        AccountFactory currentAccountFactory = new CurrentAccountFactory();
        currentAccountFactory.openAccount();

        System.out.println("\n-------------------Parametrized Factory -----------------------");
        // Factory method (parametrized)
        ParametrizedAccountFactory factory = new ParametrizedAccountFactory();
        BankAccount currentAccount = factory.createAccount("courant");
        currentAccount.displayType();

        System.out.println("\n-------------------Static Factory -----------------------");
        // Factory method (static)
        BankAccount savingsAccount = StaticAccountFactory.createAccount("epargne");
        savingsAccount.displayType();

        // Factory method (Registry-based)
        RegistryAccountFactory.register("courant", CurrentAccount::new);
        RegistryAccountFactory.register("epargne", SavingsAccount::new);

        System.out.println("\n-------------------Registry-Based Factory -----------------------");

        BankAccount c1 = RegistryAccountFactory.createAccount("courant");
        BankAccount e1 = RegistryAccountFactory.createAccount("epargne");

        c1.displayType();
        e1.displayType();
    }

    /**
     * Demonstration method that initializes and runs the
     * three main variations of the Singleton design pattern:
     *
     * 1. Eager Singleton: The instance is created at class loading time.
     * 2. Lazy Singleton: The instance is created only when it is first requested.
     * 3. Multiton: A variation of the Singleton that allows multiple instances,
     * each identified by a unique key.
     *
     * This method is used to compare the behavior of each implementation
     * and verify their correct functionality.
     */
    public static void runSingletonPatterns() {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        DatabaseConnection db3 = DatabaseConnection.getInstance();

        System.out.println("Db1 est égale à db2 ? : " + (db1 == db2));
        System.out.println("Db2 est égale à db3 ? : " + (db2 == db3));

        UserSession session1 = UserSession.getInstance("user1");
        UserSession session2 = UserSession.getInstance("user2");
        UserSession session3 = UserSession.getInstance("user1");

        System.out.println("Est-ce que la session1 == Session3 : " + (session1 == session3)); // Vrai

        System.out.println("Est-ce que la session1 == Session2 : " + (session1 == session2)); // Faux
    }
}