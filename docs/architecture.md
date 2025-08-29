# Architecture Overview

This document describes the architecture of the URL Shortener project.

## Main Components

- **Controller**: Handles HTTP requests and responses.
- **ServiceProduto**: Contains business logic for product management (currently, not URL shortening).
- **Repo**: Repository interface for data persistence.
- **Model**: Entity representing a product (with fields `produto` and `preso`).
- **Dto**: Data Transfer Object for communication between layers.

## Current Data Model

The current `Model` class is:
```java
public class Model {
    private Long id;
    private String produto;
    private int preso;
    // getters and setters
}
```

## Flow

1. Controller receives a request.
2. Controller calls ServiceProduto for business logic.
3. ServiceProduto interacts with Repo for data persistence.
4. Data is mapped to/from Model and Dto as needed.

## Note

The current implementation is for product management, not URL shortening. To support URL shortening, the model and service should be updated accordingly.
