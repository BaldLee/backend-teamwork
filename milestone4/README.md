# MileStone 4: Spring Cloud II

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

## New Progress
What's different from the last milestone?
### Gateway
Now nginx are comined with zuul to work as the gateway and also the loadbalancer distributing requests from clients on the server end, thus making nginx the first layer of the whole service system. Though not configured yet, multiple zuuls are preferred in order to improve the high availability of the architecture. We keep nginx listening on the port 10010 as the entrance for zuul. Visit http://localhost:10010/service_name to access the corresponding service(service_name here are replaced by one of the five services mentioned above).
### Feign
Rating book service has been upgraded to a Feign service, endowed with the ability to invoke other services by methods based on the interface while no explicit http requests included. 
### Hystrix
Hystrix works as a circuit breaker which can coordinate with the loadbalancer well. When service is trapped in an exception or some other weird situation, or maybe just don't perform as expected, hystrix automatically breaks it and calls a fallback to ensure the other normally performed services move on the right track. We can monitor the performance of requests handling by the tool of hystrix dashboard which is enabled on the port 8083(the same as the feign service itself to simplify the structure). 
