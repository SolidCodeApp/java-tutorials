package com.solid.executors;

import java.util.function.Consumer;
import java.util.function.Function;
import com.solid.enums.EErrorCode;
import com.solid.exceptions.AppException;
import com.solid.managers.ISessionManager;
import jakarta.persistence.EntityManager;

public class JpaExecutorImpl implements IPersistenceExecutor<EntityManager> {

    private final ISessionManager<EntityManager> jpaSessionManager;

    public JpaExecutorImpl(ISessionManager<EntityManager> jpaSessionManager) {
        this.jpaSessionManager = jpaSessionManager;
    }

    private <R> R safeExecuteWithSession(Function<EntityManager, R> action,
            boolean withTransaction) {

        EntityManager entityManager = null;

        try {
            entityManager = withTransaction ? this.jpaSessionManager.startTransaction()
                    : this.jpaSessionManager.createSession();

            R result = action.apply(entityManager);

            if (withTransaction) {
                this.jpaSessionManager.commitTransaction(entityManager);
            }
            return result;

        } catch (RuntimeException exception) {

            if (withTransaction) {
                this.jpaSessionManager.rollbackTransaction(entityManager);
            }

            if (exception instanceof AppException) {
                throw (AppException) exception;
            }

            throw new AppException(EErrorCode.UNKNOWN_ERROR,
                    "An error occurred during safeExecutionWithSession", exception);
        } finally {
            this.jpaSessionManager.endSession(entityManager);
        }
    }

    @Override
    public void execute(Consumer<EntityManager> consumer) throws AppException {
        this.safeExecuteWithSession(entityManager -> {
            consumer.accept(entityManager);
            return null; // Cause consumer returns nothing
        },
                false);
    }

    @Override
    public <T> T execute(Function<EntityManager, T> function) throws AppException {
        return this.safeExecuteWithSession(function, false);
    }

    @Override
    public <T> T executeInTransaction(Function<EntityManager, T> function) throws AppException {
        return this.safeExecuteWithSession(function, true);
    }

    @Override
    public void executeInTransaction(Consumer<EntityManager> consumer) throws AppException {
        this.safeExecuteWithSession(entityManager -> {
            consumer.accept(entityManager);
            return null;
        }, true);
    }
}