FROM thrift:latest

RUN apt-get update
RUN apt install python3

COPY . /app

WORKDIR /app

ENTRYPOINT python3 calculadora_servidor.py