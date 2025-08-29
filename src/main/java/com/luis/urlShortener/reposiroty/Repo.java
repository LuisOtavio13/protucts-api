package com.luis.urlShortener.reposiroty;

import com.luis.urlShortener.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for accessing and managing Model entities in the database.
 * Extends JpaRepository to provide standard CRUD operations and query capabilities.
 */

public interface Repo extends JpaRepository<Model, Long> {

}
