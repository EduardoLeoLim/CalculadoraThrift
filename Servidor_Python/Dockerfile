FROM python:3-alpine3.10

RUN pip install thrift

COPY . /app

WORKDIR /app

EXPOSE 9090

ENTRYPOINT python3 calculadora_servidor.py