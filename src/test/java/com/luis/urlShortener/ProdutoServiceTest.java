package com.luis.urlShortener;


import com.luis.urlShortener.dto.Dto;
import com.luis.urlShortener.model.Model;
import com.luis.urlShortener.reposiroty.Repo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ProdutoService class.
 * Uses Mockito to mock the Repository and verify service behavior.
 */
class ProdutoServiceTest {

    private Repository repository;
    private ProdutoService service;

    /**
     * Initializes the mock repository and service before each test.
     */
    @BeforeEach
    void setUp() {
        repository = mock(Repository.class);
        service = new ProdutoService(repository);
    }

    /**
     * Tests getById when the Model exists in the repository.
     * Verifies that the correct Model is returned.
     */
    @Test
    void testGetById_WhenModelExists() {
        Model model = new Model(1, "Produto A");
        model.setId(10L);

        when(repository.findById(10L)).thenReturn(Optional.of(model));

        Model result = service.getById(10L);

        assertNotNull(result);
        assertEquals("Produto A", result.getProduto());
        assertEquals(1, result.getPreso());
    }

    /**
     * Tests getById when the Model does not exist.
     * Verifies that null is returned.
     */
    @Test
    void testGetById_WhenModelDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Model result = service.getById(99L);

        assertNull(result);
    }

    /**
     * Tests update when the Model exists.
     * Verifies that the Model is updated and returned with status 200 OK.
     */
    @Test
    void testUpdate_WhenModelExists() {
        Model existing = new Model(1, "Produto Antigo");
        existing.setId(5L);

        Dto dto = new Dto(2, "Produto Novo");

        when(repository.findById(5L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Model.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Model> response = service.update(5L, dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Produto Novo", response.getBody().getProduto());
        assertEquals(2, response.getBody().getPreso());
    }

    /**
     * Tests update when the Model does not exist.
     * Verifies that a 404 Not Found response is returned.
     */
    @Test
    void testUpdate_WhenModelDoesNotExist() {
        Dto dto = new Dto(3, "Produto Inexistente");

        when(repository.findById(100L)).thenReturn(Optional.empty());

        ResponseEntity<Model> response = service.update(100L, dto);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}

