/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PruebaFactura {
    public static void main(String[] args) throws IOException {
        // lector de entrada estandar
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // menu de seleccion
        int eleccion = 0;
        Factura ejemplo = new Factura("tornillo1", "tornillo", 2, 33);
        while (eleccion != -1) {
            System.out.println("\nElija una opcion (-1 para salir)");
            System.out.println("1- Ver factura");
            System.out.println("2- Cambiar identificador de articulo");
            System.out.println("3- Cambiar descripcion de articulo");
            System.out.println("4- Cambiar precio de articulo");
            System.out.println("5- Cambiar numero de articulos");
            System.out.println("6- Facturar");
            System.out.print("\n>>> ");

            eleccion = Integer.parseInt(reader.readLine());
            switch (eleccion) {
                case 1:
                    ejemplo.imprimir();
                    break;

                case 2:
                    System.out.println("Nuevo identificador: ");
                    ejemplo.setIdentificadorArticulo(reader.readLine());
                    break;

                case 3:
                    System.out.println("Nueva descripcion: ");
                    ejemplo.setDescripcionArticulo(reader.readLine());
                    break;

                case 4:
                    System.out.println("Nuevo precio: ");
                    ejemplo.setPrecio(Integer.parseInt(reader.readLine()));
                    break;

                case 5:
                    System.out.println("Nueva cantidad: ");
                    ejemplo.setCantArticulos(Integer.parseInt(reader.readLine()));
                    break;

                case 6:
                    System.out.println(ejemplo.obtenerMontoFactura());
                    break;

                default:
                    break;
            }
        }
    }
}
