FROM openjdk:17-alpine

COPY . /app

WORKDIR /app

RUN javac -cp ./lib/libthrift-0.13.0.jar:./lib/slf4j-api-1.7.30.jar:./lib/slf4j-nop-1.7.30.jar:/lib/javax.annotation-api-1.3.2.jar:./gen-java:. CalculadoraCliente.java
