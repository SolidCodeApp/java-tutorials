package com.solid.patterns.factorymethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.solid.patterns.bank.BankAccount;

public class RegistryAccountFactory {

    private static final Map<String, Supplier<BankAccount>> registry = new HashMap();

    public static void register(String type, Supplier<BankAccount> supplier) {
        registry.put(type, supplier);
    }

    public static BankAccount createAccount(String type) {
        if (!registry.containsKey(type)) {
            throw new IllegalArgumentException("Type de compte inconnu : " + type);
        }
        return registry.get(type).get();
    }

}
