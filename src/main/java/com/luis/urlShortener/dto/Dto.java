package com.luis.urlShortener.dto;

import com.luis.urlShortener.model.Model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) used to receive and validate input data for Model creation or updates.
 * Contains validation annotations to ensure required fields are provided.
 *
 * @param preso   the integer value representing the 'preso' field; must not be null
 * @param Produto the string value representing the 'Produto' field; must not be blank
 */
public record Dto(@NotNull int preso, @NotBlank String produto) {

    /**
     * Converts this DTO into a Model entity.
     * Useful for persisting validated data to the database.
     *
     * @return a new Model instance based on the DTO fields
     */
    public Model toModel() {
        return new Model(this.preso, this.produto);
    }
}
