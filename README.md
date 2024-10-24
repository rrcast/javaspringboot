## Requirements 
- Java 21
- Maven
- Docker

## Build the application
1. Clone this repo
  go to src/main/resources
2. run the command docker-compose up -d
3. run the application


## Test from postman
   Now you can call the API
   you can start inserting data 
example: 
http://localhost:8080/api/products
RequestBody:
{
    "name": "First Product",
    "price": 29.99,
    "description": "This is the first product"
}
Response:
{
    "id": 1,
    "name": "First Product",
    "description": "This is the first product",
    "price": 29.99
}

Then you can use :
GET http://localhost:8080/api/products to get the products 
or GET http://localhost:8080/api/products/{id} to get an specific product PUT http://localhost:8080/api/products/{id} to update. DELETE http://localhost:8080/api/products/{id} to delete 



After that to verify the app is running as expected you can insert an order with
POST: http://localhost:8080/api/orders

{
    "customerId": 1,
    "totalAmount": 99.99,
    "orderDate": "2024-10-23T10:00:00"
}

and then 
POST : http://localhost:8080/api/order-items

{
    "order": {
        "id": 1
    },
    "product": {
        "id": 1
    },
    "quantity": 2
}
