# Transactions

This repository has 3 core APIs for now which handles below scenarios.

All these APIs are also attached here as postman collection.

1. Create Account
```
POST /accounts (create an account)
Request Body:
{
    "document_number": "12345678900"
}
```
   
3. Get Account
```
GET /accounts/:accountId (retrieve the account information)
Response Body:
{
    "account_id": 1,
    "document_number": "12345678900"
}
   ```
3. Create Transactions
```
POST /transactions (create a transaction)
Request Body:
{
    "account_id": 1,
    "operation_type_id": 4,
    "amount": 123.45
}
```

To run this application in local :
1. Use IDE like intellJ to run spring boot application class as java main class.
2. Use maven command `mvn spring-boot:run`
3. Use docker, run below commands

   `docker build -t pismo/transaction .`

   `docker run -d pismo/transation -p8080:8080`
