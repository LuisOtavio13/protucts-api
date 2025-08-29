# API Documentation

## Endpoints

### Add Product
- **POST** `/produto`
- **Body:**
  ```json
  {
    "produto": "string",
    "preso": 0
  }
  ```
- **Response:**
  - 201 Created
  - Returns the created product

### Get Product
- **GET** `/produto/{id}`
- **Response:**
  - 200 OK
  - Returns the product with the given ID

## Note

Endpoints and payloads are based on the current product model. For URL shortener features, endpoints should be updated accordingly.
