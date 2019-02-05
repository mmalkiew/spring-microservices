# spring-microservices
Microservices with Spring Boot and Spring Cloud


# run Zipkin 
docker run -d -p 9411:9411 openzipkin/zipkin

# run RabbitMQ
docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3

docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 rabbitmq:3-management
