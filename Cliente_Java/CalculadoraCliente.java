import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.TException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CalculadoraCliente {

    public static int leerNumero() {
        int numero = 0;
        Boolean numeroValido = true;
        System.out.print("Ingresa un número: ");
        
        do {    
            try {
                Scanner lector = new Scanner(System.in);
                numero = lector.nextInt();
                numeroValido = true;
            } catch (InputMismatchException ex) {
                System.out.print("Por favor ingresa un número entero: ");
                numeroValido = false;
            }
        } while (numeroValido == false);
        return numero;    
    }

    public static void main(String[] args)  {
        String HOST = "localhost";
        int PORT = 9090;
        
        if (args.length == 2) {
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
        }


        TSocket transporte = new TSocket(HOST, PORT);
		TBinaryProtocol protocolo = new TBinaryProtocol(transporte);
		Calculadora.Client cliente = new Calculadora.Client(protocolo);

        try {
            transporte.open();
            String eleccion;
            //Borrar consola
            System.out.println("\nCalculadora con Apache Thrift\n");
            System.out.println("Elige una operación:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. Divisón");
            System.out.println("5. Salir");
            do {
                Scanner lector = new Scanner(System.in);
                eleccion = "";
                int a = 0;
                int b = 0;


                System.out.print("\nIngresa el número de la operación: ");
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
                        System.out.println("\nSaliendo...\n");
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