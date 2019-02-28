# spring-microservices
Microservices with Spring Boot and Spring Cloud


# run Zipkin 
docker run -d -p 9411:9411 openzipkin/zipkin

# run RabbitMQ
docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3



#   addresses
1.  Eureka:   
http://localhost:8761/

2. RabbitMQ:    
http://localhost:15672

3. Zipkin   
http://localhost:9411/zipkin/

4.  Conversion Service    
-   zuul        http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/PLN/quantity/100
-   not Feign:  http://localhost:8100/currency-converter-feign/from/USD/to/PLN/quantity/100 
-   Feign:      http://localhost:8100/currency-converter/from/USD/to/PLN/quantity/100

1. Configuration service    
envs configuration    
http://localhost:8888/limit-service/default 
http://localhost:8888/limit-service/qa

refresh configuration   
http://localhost:8888/actuator/bus-refresh  
POST http://localhost:8350/actuator/bus-refresh

2. Limit Service    
http://localhost:8350/limits    
http://localhost:8350/fault-tolerance-example

