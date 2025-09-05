/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_12;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author User
 */
public class Usuario {



    // Atributo único e inmutable que identifica al usuario
    private final String id;

    // Nombre del usuario, puede modificarse
    private String nombre;

    // Lista que almacena los ISBN de los libros prestados
    private final List<String> isbnsPrestados;

    // Constructor que inicializa el ID, nombre y crea la lista de préstamos vacía
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.isbnsPrestados = new ArrayList<>();
    }

    // Getter para obtener el ID del usuario
    public String getId() {
        return id;
    }

    // Getter para obtener el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el nombre del usuario
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para acceder a la lista de ISBNs prestados
    public List<String> getIsbnsPrestados() {
        return isbnsPrestados;
    }

    // Sobrescribe equals para comparar usuarios por su ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son el mismo objeto en memoria, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Verifica que el objeto no sea nulo y sea de tipo Usuario
        Usuario usuario = (Usuario) o; // Cast seguro
        return Objects.equals(id, usuario.id); // Compara los IDs para determinar igualdad lógica
    }

    // Sobrescribe hashCode para mantener coherencia con equals
    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera hash basado en el ID
    }

    // Representación textual del objeto Usuario, útil para depuración o impresión
    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", isbnsPrestados=" + isbnsPrestados +
                '}';
    }
}

