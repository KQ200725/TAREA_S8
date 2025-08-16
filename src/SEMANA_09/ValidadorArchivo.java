/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_09;
import java.io.*;
/**
 *
 * @author User
 */
 

public class ValidadorArchivo {

    // Método que verifica si un archivo no está vacío
    public void verificarNoVacio(String nombreArchivo) throws ArchivoVacioException, IOException {
        // Abro el archivo usando FileReader y lo envuelvo en un BufferedReader para leer su contenido
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));

        // Leo la primera línea del archivo
        if (br.readLine() == null) {
            // Si la primera línea es null, significa que el archivo está vacío
            br.close(); // Cierro el BufferedReader antes de lanzar la excepción
            throw new ArchivoVacioException("El archivo está vacío."); // Lanzo mi excepción personalizada
        }

        // Si el archivo tiene contenido, simplemente cierro el BufferedReader
        br.close();
    }
}
