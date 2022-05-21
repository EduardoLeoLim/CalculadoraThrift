import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.TException;
import java.util.Scanner;

public class CalculadoraCliente {

    public static int leerNumero() {
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingresa un número: ");
        return lector.nextInt();
    }

    public static void main(String[] args)  {
        TSocket transporte = new TSocket("localhost", 9090);
		TBinaryProtocol protocolo = new TBinaryProtocol(transporte);
		Calculadora.Client cliente = new Calculadora.Client(protocolo);

        try {
            transporte.open();
            String eleccion;
            do {
                Scanner lector = new Scanner(System.in);
                eleccion = "";
                int a = 0;
                int b = 0;
                //Borrar consola
                System.out.println("\nCalculadora con Apache Thrift\n");
                System.out.println("Elige una operación:");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicación");
                System.out.println("4. Divisón");
                System.out.println("5. Salir");

                System.out.print("Ingresa el número de la operación: ");
                eleccion = lector.nextLine();

                String res = "";

                switch (eleccion) {
                    case "1":
                        res = cliente.sumar(CalculadoraCliente.leerNumero(), CalculadoraCliente.leerNumero());
                        System.out.print(res + "\n");
                        break;
                    case "2":
                        res = cliente.restar(CalculadoraCliente.leerNumero(), CalculadoraCliente.leerNumero());
                        System.out.print(res + "\n");
                        break;
                    case "3":
                        res = cliente.multiplicar(CalculadoraCliente.leerNumero(), CalculadoraCliente.leerNumero());
                        System.out.print(res + "\n");
                        break;
                    case "4":
                        res = cliente.dividir(CalculadoraCliente.leerNumero(), CalculadoraCliente.leerNumero());
                        System.out.print(res + "\n");
                        break;
                    case "5":
                        System.out.println("Cerrando Calculadora. Presione una tecla\n");
                        break;
                    default:
                        System.out.println("Opción no disponible.\n");
                }
                //Pausar consola
            } while(!eleccion.equals("5"));
            transporte.close();
        } catch (TException ex) {
            System.out.print(ex.getMessage());
        }
    }
}