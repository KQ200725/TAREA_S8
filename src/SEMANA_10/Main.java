/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_10;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n MENU DE INVENTARIO");
            System.out.println("1. Agregar producto");
            System.out.println("2. Actualizar cantidad");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Mostrar inventario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    inventario.agregarProducto(new Producto(id, nombre, precio, cantidad));
                    break;
                case 2:
                    System.out.print("ID del producto: ");
                    String idAct = sc.nextLine();
                    System.out.print("Nueva cantidad: ");
                    int nuevaCant = sc.nextInt();
                    sc.nextLine();
                    inventario.actualizarCantidad(idAct, nuevaCant);
                    break;
                case 3:
                    System.out.print("ID del producto a eliminar: ");
                    String idDel = sc.nextLine();
                    inventario.eliminarProducto(idDel);
                    break;
                case 4:
                    inventario.mostrarInventario();
                    break;
                case 0:
                    System.out.println(" Saliendo.");
                    break;
                default:
                    System.out.println(" Opción inválida.");
            }
        } while (opcion != 0);
    }
}