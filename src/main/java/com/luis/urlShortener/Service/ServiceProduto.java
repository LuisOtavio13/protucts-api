package com.luis.urlShortener.Service;

import com.luis.urlShortener.dto.Dto;
import com.luis.urlShortener.model.Model;
import com.luis.urlShortener.reposiroty.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
/**
 * Service class responsible for handling business logic related to Model entities.
 * Provides methods for retrieving and updating data in the database.
 */

@Service
public class ServiceProduto {
    @Autowired
    private final Repo repository;

    /**
     * Constructor-based injection of the repository dependency.
     *
     * @param repository the JPA repository for Model entities
     */
    public ServiceProduto(Repo repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a Model entity by its ID.
     *
     * @param id the unique identifier of the Model
     * @return the Model if found, or null if not present
     */
    public Model getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Model entity with new data from the provided DTO.
     * If the entity exists, applies changes and returns HTTP 200 OK.
     * If not found, returns HTTP 404 Not Found.
     *
     * @param id    the ID of the Model to update
     * @param dto   the DTO containing updated data
     * @return ResponseEntity with the updated Model or a 404 status
     */
    public ResponseEntity<Model> update(Long id, Dto dto) {
        return repository.findById(id).map(existingModel -> {
            existingModel.setPreso(dto.preso());
            existingModel.setProduto(dto.produto());
            repository.save(existingModel);
            return ResponseEntity.ok(existingModel);
        }).orElse(ResponseEntity.notFound().build());
    }
}



