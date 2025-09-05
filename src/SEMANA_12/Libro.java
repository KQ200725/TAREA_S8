/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_12;

/**
 *
 * @author User
 */
import java.util.Objects; 

public class Libro {
    // Atributos privados e inmutables que definen las propiedades del libro
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final String categoria;

    // Constructor que inicializa los atributos del libro al momento de crear el objeto
    public Libro(String isbn, String titulo, String autor, String categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    // Métodos getter que permiten acceder a los atributos del libro desde fuera de la clase
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    // Sobrescribe el método equals para comparar dos objetos Libro por su ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si ambos objetos apuntan a la misma referencia, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Verifica que el objeto no sea nulo y sea de la misma clase
        Libro libro = (Libro) o; // Realiza un cast seguro al tipo Libro
        return Objects.equals(isbn, libro.isbn); // Compara los ISBN para determinar igualdad lógica
    }

    // Sobrescribe hashCode para mantener coherencia con equals, usando solo el ISBN
    @Override
    public int hashCode() {
        return Objects.hash(isbn); // Genera un código hash basado en el ISBN
    }

    // Sobrescribe toString para representar el objeto Libro como una cadena legible
    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}