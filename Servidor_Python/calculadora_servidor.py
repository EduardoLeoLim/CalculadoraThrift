import sys
sys.path.append("gen-py")

from Calculadora import Calculadora

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

# Implementación del servicio

class CalculadoraHandler:   # Manejador de llamadas al sevicio
	# Métdos del servicio
    def sumar(self, a, b):
        resultado = a + b
        respuesta = "El resultado es: {}".format(resultado)
        return respuesta
    
    def restar(self, a, b):
        resultado = a - b
        return "El resultado es: {}".format(resultado)

    def multiplicar(self, a, b):
        resultado = a * b
        return "El resultado es: {}".format(resultado)

    def dividir(self, a, b):
        try:
            resultado = a / b
            return "El resultado es: {}".format(resultado)
        except ZeroDivisionError:
            return "No se puede dividr entre cero"

# Crear una instancia del manejador
manejador = CalculadoraHandler()

# Iniciacilizar un procesador para el servicio
procesador = Calculadora.Processor(manejador)

# Crear el socket TCP
puerto = 9090
transporte_serv = TSocket.TServerSocket(port=puerto)

# Buffer -> Eficiencia
transporte_fact = TTransport.TBufferedTransportFactory()

# Protocolo binario
protocolo_fact = TBinaryProtocol.TBinaryProtocolFactory()

# crear un servidor con funcionalidad básica
servidor = TServer.TSimpleServer(procesador, transporte_serv, transporte_fact, protocolo_fact)
print("Iniciando el servidor en el puerto %s" % puerto)
servidor.serve()