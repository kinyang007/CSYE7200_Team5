# **CSYE 7200 Final Project -- Team 5** <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Scala_logo.png/200px-Scala_logo.png" width="82" height="34"/>
* Repository for *CSYE7200 Big-Data System Engineering Using Scala* Spring 2020 Final Project  
### **Team Members:**  
* Jixiao Yang - yang.jix@husky.neu.edu
* Xiaoge Zhang - zhang.xiaog@husky.neu.edu
* Junyi Fang - fang.ju@husky.neu.edu
<br/><br/>

# **Title: Ticket Agency**  
## **1. Abstract**
A ticket agency system that allows a large number of users to buy tickets for at least three events concurrently, with numbers of tickets ranging from 1,000 to 100,000
## **2. Tools and APIs**
* [Scala 2.13.1](https://www.scala-lang.org/api/2.13.1/)
* [Akka 2.6.4](https://doc.akka.io/docs/akka/2.6.4/typed/index.html)
* [Play Framework 2.8.1](https://www.playframework.com/documentation/2.8.x/Home)
* [Alpakka 2.0.0-RC2 -- MongoDB](https://doc.akka.io/docs/alpakka/2.0.0-RC2/mongodb.html)
* [MongoDB 4.2](https://docs.mongodb.com/)
* [MongoDB Scala Driver 2.9.0](https://mongodb.github.io/mongo-scala-driver/2.9/)
## **3. How to run**
* [Generate Data](https://github.com/kinyang007/CSYE7200_Team5/tree/master/Generate%20Data)
* Run project [TicketAgency](https://github.com/kinyang007/CSYE7200_Team5/tree/master/TicketAgency)
    ```shell
    $ cd TicketAgency
    $ sbt run
    ```
## **4. System Pressure Tests Results**
* 1000 Users
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/1000users1.png"/>
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/1000users2.png"/>

* 2000 Users
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/2000users1.png"/>
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/2000users2.png"/>

* 4000 Users
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/4000users1.png"/>
<img src="https://github.com/kinyang007/CSYE7200_Team5/blob/master/Load%20Testing%20Reports/report%20pictures/4000users2.png"/>

