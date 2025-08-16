/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_09;

/**
 *
 * @author User
 */
public class Main {
public static void main(String[] args)  {
    // Creo una instancia del gestor de archivos para manejar escritura y lectura
    GestorDeArchivo gestor = new GestorDeArchivo();
    
    // Creo una instancia del validador para verificar si el archivo tiene contenido
    ValidadorArchivo validador = new ValidadorArchivo();
    
    // Defino el nombre del archivo que voy a usar
    String nombreArchivo = "ejemplo_de_texto.txt";
    
    // Defino el contenido que quiero guardar en el archivo
    String contenido = "Hola, este es un ejemplo.";

    try {
        // Llamo al método guardar para escribir el contenido en el archivo
        gestor.guardar(nombreArchivo, contenido);
        System.out.println("Contenido guardado.");

        // Llamo al método leer para obtener el contenido del archivo como String
        String leido = gestor.leer(nombreArchivo);
        System.out.println("Contenido leido:\n" + leido);

        // Verifico que el archivo no esté vacío usando el validador
        validador.verificarNoVacio(nombreArchivo);
        System.out.println("El archivo no esta vacio.");
        
    } catch (ArchivoVacioException ave) {
        // Capturo la excepción personalizada si el archivo está vacío
        System.out.println("Error: " + ave.getMessage());
        
    } catch (Exception e) {
        // Capturo cualquier otro error inesperado
        System.out.println("Ocurrio un error: " + e.getMessage());
    }
}
}
