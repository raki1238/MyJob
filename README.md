# MyJob
This is created for an test purpose

This Project contains two types of webservices mainly SOAP and REST

# MySQL DB Table creation
Please execute the CreateTable.sql file to create the table in MySQL db.

# Available SOAP services:

•gcdList - Gets the list of all GCDs stored in the database.
•gcd - Gets the GCD for the numbers stored in the head of the queue.
•gcdSum - Gets the sum of all GCDs stored in database.

Endpoint address: http://localhost:8080/WebServices/services/gcdprocessor
WSDL : {http://soapws.com/}GcdServiceService
Target namespace: http://soapws.com/ 

# Available RESTful services:
1. setNumbers - To set the numbers in the JMS Queue and in MySQL database.
POST URL: http://localhost:8080/WebServices/webapi/mygcd/setNumbers
Body:
{
	"num1": 18,
	"num2": 27
}

2. elementsList: To get all the elements stored in the database
GET URL: http://localhost:8080/WebServices/webapi/mygcd/elementsList


