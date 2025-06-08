package com.solid.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.configuration.IJpaProvider;
import com.solid.enums.EErrorCode;
import com.solid.exceptions.managers.SessionManagerException;
import jakarta.persistence.EntityManager;

/**
 * JPA implementation of the ISessionManager interface.
 * Manages the lifecycle of EntityManager sessions and transactions using a JPA
 * provider.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class JpaSessionManagerImpl implements ISessionManager<EntityManager> {

    private Logger logger = LoggerFactory.getLogger(JpaSessionManagerImpl.class);

    private final IJpaProvider jpaProvider;

    public JpaSessionManagerImpl(IJpaProvider jpaProvider) {
        this.jpaProvider = jpaProvider;
    }

    /**
     * Creates a new EntityManager session.
     *
     * @return a new EntityManager instance
     * @throws SessionManagerException if session creation fails
     */
    @Override
    public EntityManager createSession() throws SessionManagerException {
        logger.info("Creating a new Session of type EntityManager...");

        try {
            EntityManager session = this.jpaProvider.getEntityManagerFactory().createEntityManager();

            logger.debug("Session of type EntityManager has been created successfully.");

            return session;
        } catch (RuntimeException exception) {
            throw new SessionManagerException(EErrorCode.SESSION_ERROR,
                    "Failed to create new Session of type EntityManager",
                    exception);
        }
    }

    /**
     * Closes the provided EntityManager session if it is open.
     *
     * @param session the EntityManager session to close
     * @throws SessionManagerException if session closure fails
     */
    @Override
    public void endSession(EntityManager session) throws SessionManagerException {
        logger.info("Ending Session of type EntityManager...");
        try {
            if (session != null && session.isOpen()) {
                session.close();
                logger.debug("Session of type EntityManager has been closed successfully.");

            } else {
                logger.warn("Attempted to end Session, but it was already closed or null. No action taken.");
            }
        } catch (RuntimeException exception) {
            throw new SessionManagerException(EErrorCode.SESSION_ERROR,
                    "Failed to close Session of type EntityManager",
                    exception);
        }
    }

    /**
     * Starts a transaction within a new EntityManager session.
     *
     * @return the EntityManager with an active transaction
     * @throws SessionManagerException if transaction start fails
     */
    @Override
    public EntityManager startTransaction() throws SessionManagerException {
        logger.debug("Starting a new Transaction on Session of type EntityManager...");

        try {
            EntityManager session = this.createSession();
            session.getTransaction().begin();
            logger.debug("Transaction on Session of type EntityManager has been started successfully.");

            return session;
        } catch (RuntimeException exception) {
            throw new SessionManagerException(EErrorCode.SESSION_ERROR,
                    "Failed to start new Transaction on Session of type EntityManager",
                    exception);
        }
    }

    /**
     * Commits the active transaction on the provided EntityManager session.
     *
     * @param session the EntityManager with an active transaction
     * @throws SessionManagerException if commit fails
     */
    @Override
    public void commitTransaction(EntityManager session) throws SessionManagerException {
        logger.debug("Committing Transaction on Session of type EntityManager...");

        try {
            session.getTransaction().commit();
            logger.debug("Transaction on Session of type EntityManager has been committed successfully.");

        } catch (RuntimeException exception) {
            throw new SessionManagerException(EErrorCode.SESSION_ERROR,
                    "Failed to commit Transaction on Session of type EntityManager",
                    exception);
        }
    }

    /**
     * Rolls back the active transaction on the provided EntityManager session if
     * active.
     *
     * @param session the EntityManager with a potentially active transaction
     * @throws SessionManagerException if rollback fails
     */
    @Override
    public void rollbackTransaction(EntityManager session) throws SessionManagerException {
        logger.debug("Rolling back Transaction on Session of type EntityManager...");

        try {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                logger.debug("Transaction on Session of type EntityManager has been rolled back successfully.");

            } else {
                logger.warn("Attempted to rollback Transaction, but it was not active. No action taken.");
            }
        } catch (RuntimeException exception) {
            throw new SessionManagerException(EErrorCode.SESSION_ERROR,
                    "Failed to rollback Transaction on Session of type EntityManager",
                    exception);
        }
    }
}