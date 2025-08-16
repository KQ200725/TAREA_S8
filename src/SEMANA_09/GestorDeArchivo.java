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
public class GestorDeArchivo {
    // Método para guardar contenido en un archivo
    public void guardar(String nombreArchivo, String contenido) throws IOException {
       // Creo un FileWriter para escribir en el archivo con el nombre especificado
        FileWriter fw = new FileWriter(nombreArchivo);
        // Escribo el contenido proporcionado en el archivo
        fw.write(contenido);
        // Cierro el FileWriter 
        fw.close();
    }
// Método para leer el contenido de un archivo y devolverlo como un String
    public String leer(String nombreArchivo) throws IOException {
       // Abro el archivo usando FileReader y lo envuelvo en un BufferedReader para leer línea por línea
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        // Utilizo un StringBuilder para construir el contenido leído
        StringBuilder sb = new StringBuilder();
       
        String linea;
        // Leo cada línea del archivo hasta que no haya más
        while ((linea = br.readLine()) != null) {
            sb.append(linea).append("\n");
        }
        br.close(); //Cierro 
        // Devuelvo el contenido completo como un solo String
        return sb.toString();
    }

}
