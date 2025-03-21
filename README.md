# Shopping Cart 

**Trendyol Interview - Case2**

**Technologies:**

Spring Boot 2.2.3

Java 8

Gradle 6.6.1

Javax Validations

Lombok

Junit

AssertJ

Mockito

Swagger

MySQL

**Prerequisites:**

1- IDE with Java 8 or higher

2- 6.6.1 or higher version of Gradle

3- After the project is imported, gradle refresh should be done. If build gradle operation gets an error about distTar

	-  def targetDir = "/"
		distTar {
			into("$targetDir/sql") {
				from('src/main/resources/sql') {
					include 'import.sql'
				}
			}
		}  
lines removed from build.gradle.

 -  Then, build gradle again
 -  After successful build add removed lines and save  build.gradle.

4- Create a shopping database in MySQL.

`spring.datasource.url=jdbc:mysql://**localhost:3306/shopping**`

`spring.datasource.**username=root**`

`spring.datasource.**password=root**`

4- It is defined as spring.jpa.hibernate.ddl-auto = create-drop in application.properties. Therefore; when the server starts up with the import.sql file located under the src / main / resources directory, it inserts the user, campaign, category, product and coupon datas into the tables. 

5- After the server up; the api list can be accessed from http: // localhost: 8080 / swagger-ui.html.



**ER Diagram:**

![ERDiagram](/.github/ERDiagram.JPG)



**Simple Example:**

![SwaggerUI](/.github/swagger-ui-default.JPG)

User, campaign, category, product and coupon have a api controller to make their own operations. (add, get etc.) Classes within the controller can be expanded and more features can be added.

Now, user, campaign, category, product and coupon tables are created with data. So, ready to cart operations. 

![CartApiController-swaggerUI](/.github/CartApiController-swagger.JPG)

**1- addProductToCart (productId:2, quantity:4)**

productId: 2 refers to Skinny Jeans on Product table, with price 100 TL. Skinny Jeans is related to Jeans subcategory.(Jeans sub category related to Clothes category(parent)). Jeans sub category has a campaign (mininum quantity: 3 %10 discount rate).

**2- addProductToCart (productId:4, quantity:1)**

productId: 4 refers to Wireless Headphone on Product table, with price 800 TL. Wireless Headphone is related to Headphones subcategory.(Headphones sub category related to Electronics category(parent)). Headphones sub category has not a campaign.

**3- showCart (cartId:1)**

Coupons are defined in the system. System calculates most profitable coupons and use it. Delivery cost and total cart price are calculated dynamically based on product quantity on cart or cart total price.

```
------ SHOPPING CART ------
Product: Skinny Jeans        Qty: 4  Price: 400.00 TL  Discounted Price: 360.00 TL
Product: Wireless Headphone  Qty: 1  Price: 800.00 TL
Coupon Discount Amount: -232.00 TL
Delivery Cost: 0 TL
------ CART SUMMARY ------
Total Price: 928.00 TL
```

**4- removeProductFromCart (cartItemId:3)**

cartItemId:3 refers to added product Wireless Headphone to Shopping Cart. With this service, added product in the cart is removed.

**5- updateProductQuantityOnCart(cartItemId:2, quantity:1)**

cartItemId:2 refers to added to product Skinny Jeans to Shopping Cart. With this service, added product quantity is updated.

**6- showCart (cartId:1)**

After five operations; only one Skinny Jean left in the cart. Therefore, Campaign usage was not made because the campaign conditions for the added product could not be met. The delivery cost was determined because the terms of the number of items in the cart or the total price of the cart were not met.

```
------ SHOPPING CART ------
Product: Skinny Jeans  Qty: 1  Price: 100.00 TL
Delivery Cost: 8.99 TL
------ CART SUMMARY ------
Total Price: 108.99 TL
```

**7- complete or empty**

Empty service find session user and removes all items from the cart and breaks relationships.

Complete service; It is assumed that the payment transaction has been made in the complete service and find session user then it completes the user's active cart process.



