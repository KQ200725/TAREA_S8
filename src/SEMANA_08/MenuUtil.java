/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_08;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class MenuUtil {
     private Scanner scanner; // Scanner para leer entrada del usuario
    
    // Constructor que inicializa el scanner
    public MenuUtil() {
        this.scanner = new Scanner(System.in);
    }
    
    // Método para mostrar el encabezado del sistema
    public void mostrarEncabezado() {
        System.out.println("========================================");
        System.out.println("     SISTEMA DE GESTIÓN DE TAREAS      ");
        System.out.println("           SEMANA 08 - POO             ");
        System.out.println("========================================");
        System.out.println();
    }
    
    // Método para mostrar las opciones del menú principal
    public void mostrarMenu() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│           MENÚ PRINCIPAL            │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 1. Ver todas las tareas             │");
        System.out.println("│ 2. Completar una tarea              │");
        System.out.println("│ 3. Agregar nueva tarea              │");
        System.out.println("│ 4. Ver estadísticas                 │");
        System.out.println("│ 5. Salir del sistema                │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Seleccione una opción (1-5): ");
    }
    
    // Método para leer la opción seleccionada por el usuario
    public int leerOpcion() {
        try {
            // Intentar convertir la entrada a número entero
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Si no es un número válido, retornar -1
            return -1;
        }
    }
    
    // Método para leer texto del usuario con un mensaje personalizado
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim(); // trim() elimina espacios al inicio y final
    }
    
    // Método para mostrar mensajes al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
        System.out.println(); // Línea en blanco para mejor legibilidad
    }
    
    // Método para mostrar mensajes de error
    public void mostrarError(String mensaje) {
        System.out.println(" ERROR: " + mensaje);
        System.out.println();
    }
    
    // Método para mostrar mensajes de éxito
    public void mostrarExito(String mensaje) {
        System.out.println(" ÉXITO: " + mensaje);
        System.out.println();
    }
    
    // Método para pausar la ejecución hasta que el usuario presione Enter
    public void pausar() {
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine(); // Esperar entrada del usuario
    }
    
    // Método para limpiar la pantalla (simulado con líneas en blanco)
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println(); // Imprimir líneas en blanco
        }
    }
    
    // Método para cerrar el scanner al finalizar
    public void cerrar() {
        if (scanner != null) {
            scanner.close(); // Cerrar el scanner para liberar recursos
        }
    }
}
