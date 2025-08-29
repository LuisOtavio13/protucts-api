# Test Guide

## Unit Tests

Unit tests are located in `src/test/java/com/luis/urlShortener/`.

- `ServiceProdutoTest.java`: Tests for the service layer.
- `ProdutoServiceTest.java`: Additional service tests.
- `UrlShortenerApplicationTests.java`: General application tests.

## How to Run Tests

From the project root, run:
```
./mvnw test
```

## Adding More Tests

- Use JUnit 5 for new tests.
- Mock dependencies using Mockito.
- Place new test files in the same test package structure as the main code.
