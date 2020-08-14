# Ecommerce-Spring
Back-End: Spring Boot with test cases

Controller :- 90% Covered
Services:- 100% Covered 
Input Validations Done for Create API.


## Spring Security Enabled - JWT And token Based Authentication.

We Can customize it to use Oauth2,2Factor Authentication, Three Legged Oauth.

Run it On Port 8080 
In Order to Use First Run Spring Boot Application using:-

Maven clean install & maven spring-boot: run

Run Frontend Using:-

Npm install & npm run start 

Sign-up First:-

URL :- localhost:8080/api/auth/signup
Body :-
{"username":"sanjay","name":"sanju","email":"bangarsanju12@gmail.com","password":"sanjay"}
Response:-
{
    "success": true,
    "message": "User registered successfully"
}

##  Improvements:-

Use maven modules for Services, Repository, Resources.
More Number of Input Validations.
Dockerize it to Run On Microservices.
