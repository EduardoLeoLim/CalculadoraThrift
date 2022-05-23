FROM openjdk:16-alpine3.13

COPY . /app

WORKDIR /app

RUN javac -cp ./lib/libthrift-0.13.0.jar:./lib/slf4j-api-1.7.30.jar:./lib/slf4j-nop-1.7.30.jar:./gen-java:. CalculadoraCliente.java
