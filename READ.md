1)Shopping-kart-service application contains the getAPI which takes product names as input and calulates the total price including tax (18%) and discounts based on product

2)Application port 8080 which is configured in application.properties file

3)You can download the application from GitHub repository

4)Application needs jdk1.8 and Maven 3.3.9 or later version

5)Once application downloaded please import to IDE as Existing Maven Project

6)build the applicatoion and start as spring boot project

7)Use the below Swagger URL to get to know the info about API's in application
  http://localhost:8080/shopping-services/v2/api-docs
  
8)Here is the getAPI url to calulate the totla prices of the given products
  http://localhost:8080/shopping-services/api/v1/kart/products/{products}
  URL will take input as a request parameter and please provide products name as space sepearated values
  
9)To test the getAPI please configure below URL in postman and GET API
   http://localhost:8080/shopping-services/api/v1/kart/products/T-shirt T-shirt Shoes Jacket
   
   Expected Response
   -----------------
   {
    "serviceResponse": {
        "subTotal": "8500",
        "tax": "1530",
        "total": "8280",
        "discounts": [
            "10% off on shoes: -Rs. 500",
            "50% off on jacket: -Rs. 1250"
        ]
    }
   }
    
9)Please use below steps to start the application from CMD
   a)once project downloaded please open the CMD and goto project directory where pom.xml resides
   b)use mvn clean install cmd to build the application
   c)jar file will created in target folder
   d)go to that target folder and open the cmd prompt
   e)use java -jar shopping-kart-service-0.0.1-SNAPSHOT to start the application
   d)once application started successfully you can test the application using steps form 7 to 9.