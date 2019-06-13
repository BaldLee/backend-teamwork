# MileStone 3: basic Microservice Architecture

## Intro
In this project, there are 5 services: config server, discovery server, gateway, book server, rating book server.

All the configuration file can be found in [config_content](/config_content).

Rating book server serves as a consumer of book server. It will use the info acquired from multiple instances of book server. Therefore, Rating book server will use Ribbon to achieve client side load balance. Besides, to find out the effect of the load balance rule, I use spring actuator and prometheus to get the request number of every instance of book service.

## User Guide
1. turn on config server. Default port is 8081.
2. turn on disovery server. Default port is 8082
3. turn off config server and restart config server to register config server in discovery server. 
4. turn on gateway server. Default port is 8080
5. turn on book server multiple times to create instances server. You don't need to worry about the port conflict between these instances. The program will asign port for each instances randomly. You can find the port in the INFO LOG of the server.
6. turn on rating book server. Default port is 8083.

## Customed Ribbon Rule in rating-book-service 
You can find the customed Ribbon Rule [here](/rating-book-service/src/main/java/com/se418/ratingbookservice/MyRandomRule.java)

In the Rule, I simply change the possibility of one instance twice than any other. 

For example, The possiblility of the three instance server is 2/4, 1/4, 1/4.

I use apache bench to send 1000 requests.
```
ab -n 1000 -c 1 "http://localhost:8083/"
```
the result is as follows
![](images_for_readme/MyRandomRule/1.png)
![](images_for_readme/MyRandomRule/2.png)
![](images_for_readme/MyRandomRule/3.png)
PS: You can use prometheus and grafana to visualize the result.