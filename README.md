# Shopping Cart 

**Trendyol Interview - Case2**

**Technologies:**

Spring Boot 2.2.3
Java 8
Gradle 6.6.1
Javax validations
Lombok
Junit
AssertJ
Mockito
Swagger
MySQL



**Prerequisites:**

1- IDE with Java 8 or higher

2- 6.6.1 or higher version of Gradle

3- After the project is imported, gradle refresh should be done.

4- Create a shopping database in MySQL.

spring.datasource.url=jdbc:mysql://**localhost:3306/shopping**
spring.datasource.**username=root**
spring.datasource.**password=root**

4- It is defined as spring.jpa.hibernate.ddl-auto = create-drop in application.properties. Therefore; when the server starts up with the import.sql file located under the src / main / resources directory, it inserts the user, campaign, category, product and coupon tables. Operation method can be changed by making configuration changes.

5- After the server up; api list at http: // localhost: 8080 / swagger-ui.html.



**Class Diagram:**

<img src="C:\Users\OvuncKarabeyoglu\Desktop\classDiagram.jpg" alt="classDiagram" style="zoom:100%;" />





**Entity Relationship Diagram:**























**Simple Example:**

![swagger-ui-default](C:\Users\OvuncKarabeyoglu\Desktop\trndy\swagger-ui-default.JPG)

User, campaign, category, product and coupon have a api controller to make their own operations. (add, get etc.) Classes within the controller can be expanded and more features can be added.

Now, user, campaign, category, product and coupon tables are created with data. So, ready to cart operations. 

![cart-swagger](C:\Users\OvuncKarabeyoglu\Desktop\trndy\cart-swagger.JPG)

1- addProductToCart 

2- addProductToCart 

3- showCart 

4- removeProductFromCart 

5- removeProductFromCart 

6- updateProductQuantityOnCart

7- showCart 

8- complete or empty

