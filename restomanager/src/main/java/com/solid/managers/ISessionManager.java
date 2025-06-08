package com.solid.managers;

import com.solid.exceptions.managers.SessionManagerException;

/**
 * Interface for managing session and transaction lifecycles for a specific
 * persistence context.
 * Implementations can provide support for different persistence providers
 * (e.g., JPA, Hibernate).
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public interface ISessionManager<S> {

    /**
     * Creates a new session without starting a transaction.
     */
    public S createSession() throws SessionManagerException;

    /**
     * Ends the given session, closing all associated resources.
     */
    public void endSession(S session) throws SessionManagerException;

    /**
     * Starts a new session with a transaction.
     */
    public S startTransaction() throws SessionManagerException;

    /**
     * Commits the active transaction for the given session.
     */
    public void commitTransaction(S session) throws SessionManagerException;

    /**
     * Rolls back the active transaction for the given session.
     */
    public void rollbackTransaction(S session) throws SessionManagerException;

}
