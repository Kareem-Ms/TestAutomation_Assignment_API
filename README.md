# TestAutomation_Assignment_API

## The main Frameworks included in the project:
- Rest-Assured
- TestNG
- Allure Report
- Json Reader for Data management

## Project Design:
- Page Object Model (POM) design pattern
- Data Driven framework
- Have a supporting Utilities package in src/main/java file path, named "utils" that includes many wrapper methods which services the project like ApiActions class

## Steps to Execute Code
- Clone the code from the Repository 
- Open POM.xml file then reload that file to install dependecies
- Go to "simpleBookTests" class then run that class.
- You can access allure report by executing the following command "allure serve" in terminal after running the code

## Code Explanation
- in the src/main/java/org folder there is a package called "utils" this package contain helper classes like:
    - "ApiActions" which is designed to handel all the apis request methods including POST,GET,PATCH and DELETE.
    - "PropertiesManager" this class contains methods to read from a property file which exist under src/main/resources to access something like BaseUrl
    - "JsonFileManager" this class is used to read data from json file to inject these data in the test classes
- in the src/main/java/org you will find a package called "pages" this package used to include all the apis that will be used in testing so for example the "GetBookAPI" class contain method to get Book information and this methods interacts with ApiActions class to form a request
- in the src/test/java/org you will find a package called "testData" this package contains test data for the "SimpleBookTests" class
- in the src/test/java/org you will find a package called "tests" this package contain one test classes "SimpleBookTests" this class have mainly five test cases which interacts with pages.
- There are some test cases that depends on "registerClientSuccessfully" to get a token then use it in the rest of cases
