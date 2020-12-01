# **SNEAKER INVENTORY PROJECT**

## **Contributors**
- [Terence Peterson](https://github.com/Terence21)
- [Akeem Yusef-Powar]()
- [David Lee]()

## **Project Overview**

The Sneaker Inventory project is a website that will allow users to market their sneaker collection. While the intention of the site isn't to allow for actual resell, the goal is to have this webiste act as a repository for any of the many exeisting reselling sites. The fundamental features of this webiste include displaying and sharing sneaker inventories, as well as having access to other features such as current market value.

A HTML/CSS/VanillaJS (subject to change to React/Vue) front end will allow users to access and update details regarding their sneaker collection. This interface will  have a login page, inventory access not limited to adding, viewing, and sorting their collection based on a variety of filters. It will also allow users to upload pictures that will be associated with a specific entry. Users will also be able to see the current price of individual items or their whole collection based on data extracted from (StockX). Additionally, if time allows, there will be a feature for following other users and adding accessbility to their inventories. 

Data regarding sneaker collections will be stored in a MySQL database, and communication with the frontend will be aided by a Spring Boot web service. There will be data associated with a member’s personal account and details regarding their sneaker collection. Browser Automation of stockX to retrieve current market value will be done with Selenium web driver and Scaping will be done with JSoup or HtmlUnit. Member details for the database will include (Name, Account number to be used as primary key, date joined and a reference to their collection, number of items in collection). Data kept regarding sneakers includes (brand, model, SKU number, price, size and purchase date).

## **Vision Statement**

**_FOR_** Sneaker Enthusiast
**_WHO_** Collect, buy and sell sneakers
**_THE_** Sneaker Inventory is a web based service
**_THAT_** provides inventory tracking, current value tracking, and sharing collection to community.
**_UNLIKE_** using excel spread sheet to track inventory
**_OUR PRODUCT_** let users easily track current value of their sneaker collection and share it with others.

## **Personas**
![image](https://user-images.githubusercontent.com/54731009/97953265-f5f44800-1d6d-11eb-8073-4869c74c4a1f.png)
![image](https://user-images.githubusercontent.com/54731009/97953293-086e8180-1d6e-11eb-8e84-f796abe9039c.png)
![image](https://user-images.githubusercontent.com/54731009/97953309-12908000-1d6e-11eb-9a67-d5b5aa5fd81d.png)



## **Feature List**

[Version 1](https://github.com/3296Fall2020/projects-01-sneaker-inventory/projects/1) <br/>
[Version 2](https://github.com/3296Fall2020/projects-01-sneaker-inventory/projects/2) <br/>
[Version 3](https://github.com/3296Fall2020/projects-01-sneaker-inventory/projects/3) <br/>

## **Technologies**
- Java (Main program)
-	MySQL and JDBC (Database)
- Spring Boot (Web Service)
- Selenium (Browser Automation)
- Jsoup/HtmlUnit(Web Scraping)
-	HTML/CSS/JavaScript (GUI where user will input and view their inventory)


## **Testing**

Database:

![GitHub Logo](database_testing.png)

As the project is built from the ground up, the only way to really test was to write code for results. As functionality was added to the database, each function was tested for accuracy and consistency.

The above block shows various functions the database needs to handle. By changing the values, we were able to see how accurately data entered the tables and how reliable the code was at retrieving data by comparing expected results with real results.
 Here we found out SQL calls were case sensitive, and others were not. This meant Strings were not handled in a uniform manner meaning case now becomes a defining identifier, which we did not want.
 
We learnt SQL in java, SQL in mySQLWorkBench and SQL Developer handle data types differently, so code needed to be implemented in a certain way to account for how java handled the Strings that would become the SQL queries/statements.  Example, a table create statement in MySQLWorkbench would not execute in java because of how certain data types were defined and some constrained were not supported. 

Frontend:

Testing for Frontend webpage was done manually.  Since Frontend of webpage was mostly visual in website, testing was done for each page by clicking buttons and see if click redirect to supposed page with correct link.  Page was also reviewed to see if the output rendered on each page was correct.  For forms that require input from user, testing was done by clicking submit buttons without user input and see if page displays correct error messages, and with correct input whether correct success message pops up.
