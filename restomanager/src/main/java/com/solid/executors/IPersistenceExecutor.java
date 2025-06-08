package com.solid.executors;

import java.util.function.Consumer;
import java.util.function.Function;

import com.solid.exceptions.AppException;

public interface IPersistenceExecutor<S> {

    public void execute(Consumer<S> consumer) throws AppException;

    public <T> T execute(Function<S, T> function) throws AppException;

    public void executeInTransaction(Consumer<S> consumer) throws AppException;

    public <T> T executeInTransaction(Function<S, T> function) throws AppException;

}
