package com.solid.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.solid.dao.interfaces.IRestaurantDao;
import com.solid.entities.RestaurantEntity;
import com.solid.exceptions.AppException;
import com.solid.exceptions.services.RestaurantServiceException;
import com.solid.executors.IPersistenceExecutor;
import com.solid.helpers.IdentifierHelper;
import com.solid.inputs.RestaurantInput;
import com.solid.mappers.RestaurantMapper;
import com.solid.models.RestaurantModel;
import com.solid.services.interfaces.IRestaurantService;

import jakarta.persistence.EntityManager;

/**
 * Service implementation of IRestaurantService.
 * Handles business logic related to Restaurant entities.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class RestaurantServiceImpl implements IRestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);
    private final IPersistenceExecutor<EntityManager> jpaExecutor;
    private final IRestaurantDao restaurantDao;

    /**
     * Constructs a RestaurantServiceImpl with the required dependencies.
     *
     * @param jpaExecutor   the persistence executor to manage database interactions
     * @param restaurantDao the DAO used for Restaurant entity operations
     */
    public RestaurantServiceImpl(IPersistenceExecutor<EntityManager> jpaExecutor, IRestaurantDao restaurantDao) {
        this.jpaExecutor = jpaExecutor;
        this.restaurantDao = restaurantDao;
    }

    /**
     * Creates a new restaurant and persists it using the DAO layer.
     *
     * @param input The restaurant input details.
     * @return An optional containing the created restaurant model.
     * @throws RestaurantServiceException if creation fails.
     */
    @Override
    public Optional<RestaurantModel> createRestaurant(RestaurantInput input) throws RestaurantServiceException {

        logger.info("Creating a new restaurant with input: {}", input);

        try {
            Optional<RestaurantModel> result = this.jpaExecutor.executeInTransaction(
                    entityManager -> {
                        RestaurantEntity restaurantEntity = RestaurantMapper.toEntity(input);
                        restaurantEntity.setIdentifier(IdentifierHelper.create());

                        Optional<RestaurantEntity> savedRestaurantOpt = restaurantDao.save(entityManager,
                                restaurantEntity);

                        return savedRestaurantOpt.isPresent() ? savedRestaurantOpt.map(RestaurantMapper::toModel)
                                : Optional.empty();
                    });

            logger.debug("Restaurant has been created successfully with input {}", input);
            return result;

        } catch (AppException exception) {
            String errorMessage = String.format("Failed to complete restaurant creation. Input: %s", input);
            logger.error(errorMessage, exception);

            throw new RestaurantServiceException(
                    exception.getErrorCode(),
                    errorMessage,
                    exception);
        }
    }

    @Override
    public Optional<RestaurantModel> updateRestaurant(RestaurantInput input, Integer id)
            throws RestaurantServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRestaurant'");
    }

    @Override
    public Optional<RestaurantModel> findRestaurantById(Integer id) throws RestaurantServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRestaurantById'");
    }

    @Override
    public Optional<RestaurantModel> findRestaurantByIdentifier(String identifier) throws RestaurantServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRestaurantByIdentifier'");
    }

    @Override
    public void deleteRestaurant(Integer id) throws RestaurantServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRestaurant'");
    }

    @Override
    public List<RestaurantModel> findAllRestaurantsByOwner(String owner) throws RestaurantServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllRestaurantsByOwner'");
    }

}
