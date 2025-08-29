package com.luis.urlShortener.controller;

import com.luis.urlShortener.Service.ServiceProduto;
import com.luis.urlShortener.dto.Dto;
import com.luis.urlShortener.model.Model;
import com.luis.urlShortener.reposiroty.Repo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that handles HTTP requests for Model-related operations.
 * Provides endpoints for creating, retrieving, updating, and deleting Model entities.
 * All routes are prefixed with "/api".
 */
@RestController
@RequestMapping("/api")
public class Controller {

    /**
     * Injects the Repository interface for performing CRUD operations on Model entities.
     */
    @Autowired
    private Repo repository;

    /**
     * Injects the ServiceProduto class to handle business logic related to product operations.
     */
    @Autowired
    private ServiceProduto service;


    /**
     * Retrieves a Model entity by its unique identifier.
     * If the entity is found, returns it with HTTP 200 OK.
     * If not found, returns HTTP 404 Not Found.
     *
     * @param id the ID of the Model to retrieve
     * @return ResponseEntity containing the Model or a 404 status
     */

    @GetMapping("/{id}")
    public  ResponseEntity<Model> findByIdModel(@PathVariable Long id){
        Model a =  service.getById(id);
       if (a != null) {
           return ResponseEntity.ok(a);
       } else {
           return ResponseEntity.notFound().build();
       }

   }
    /**
     * Retrieves a list of all Model entities from the database.
     * Returns the complete collection without any filtering or pagination.
     *
     * @return a List containing all Model instances
     */

    @GetMapping
    public List<Model> findAllModel(){
       return repository.findAll();
   }
    /**
     * Creates a new Model entity based on the provided DTO.
     * Validates the input data and persists the entity to the database.
     *
     * @param model the DTO containing data for the new Model
     */

    @PostMapping
    public void createModel(@RequestBody @Valid Dto model) {
       repository.save(model.toModel());
   }
    /**
     * Deletes a Model entity by its unique identifier.
     * If the entity exists, it is removed from the database and returns HTTP 200 OK.
     * If the entity is not found, returns HTTP 404 Not Found.
     *
     * @param id the ID of the Model to delete
     * @return ResponseEntity indicating the result of the deletion
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Model> deleteById(@PathVariable Long id){
       if(repository.existsById(id)){
           repository.deleteById(id);
           return  ResponseEntity.ok().build();
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
   }
    /**
     * Updates an existing Model entity identified by its ID using the provided DTO.
     * Validates the input data before applying changes.
     * If the entity exists, returns the updated Model with HTTP 200 OK.
     * If the entity is not found, returns HTTP 404 Not Found.
     *
     * @param id the ID of the Model to update
     * @param novoModel the DTO containing updated data
     * @return ResponseEntity with the updated Model or a 404 status
     */

    @PutMapping("/{id}")
    public ResponseEntity<Model> modifyModel(@PathVariable Long id, @RequestBody @Valid Dto novoModel){
    if(repository.existsById(id)) {
        return service.update(id, novoModel);
    }else{
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
