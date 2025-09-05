/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_12;

/**
 *
 * @author User
 */
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Crear una instancia de la biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // 2. Añadir libros
        Libro libro1 = new Libro("978-0321765723", "The Lord of the Rings", "J.R.R. Tolkien", "Fantasía");
        Libro libro2 = new Libro("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Clásico");
        Libro libro3 = new Libro("978-0140449195", "1984", "George Orwell", "Distopía");

        System.out.println("Añadiendo libros...");
        biblioteca.anadirLibro(libro1);
        biblioteca.anadirLibro(libro2);
        biblioteca.anadirLibro(libro3);

        // 3. Registrar usuarios
        Usuario usuario1 = new Usuario("U001", "Ana Garcia");
        Usuario usuario2 = new Usuario("U002", "Juan Perez");

        System.out.println("\nRegistrando usuarios...");
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        // 4. Prestar un libro
        System.out.println("\n--- Préstamo de libros ---");
        System.out.println(biblioteca.prestarLibro(libro1.getIsbn(), usuario1.getId()));
        System.out.println(biblioteca.prestarLibro(libro2.getIsbn(), usuario1.getId()));
        System.out.println(biblioteca.prestarLibro(libro1.getIsbn(), usuario2.getId())); // Intento de prestar un libro ya prestado

        // 5. Listar libros prestados a un usuario
        System.out.println("\n--- Libros prestados a Ana Garcia ---");
        List<Libro> librosPrestadosAna = biblioteca.listarLibrosPrestados(usuario1.getId());
        if (librosPrestadosAna != null) {
            librosPrestadosAna.forEach(System.out::println);
        }

        // 6. Devolver un libro
        System.out.println("\n--- Devolución de libro ---");
        System.out.println(biblioteca.devolverLibro(libro1.getIsbn(), usuario1.getId()));
        System.out.println(biblioteca.prestarLibro(libro1.getIsbn(), usuario2.getId())); // Ahora se puede prestar

        // 7. Buscar libros
        System.out.println("\n--- Búsqueda de libros ---");
        List<Libro> resultadosBusqueda = biblioteca.buscarLibro("lord");
        System.out.println("Resultados de búsqueda por 'lord':");
        resultadosBusqueda.forEach(System.out::println);

        // 8. Quitar un libro
        System.out.println("\n--- Quitar libro ---");
        System.out.println("Intentando quitar '1984' (no prestado): " + (biblioteca.quitarLibro(libro3.getIsbn()) ? "Éxito" : "Fracaso"));
        System.out.println("Intentando quitar 'The Great Gatsby' (prestado): " + (biblioteca.quitarLibro(libro2.getIsbn()) ? "Éxito" : "Fracaso"));

        // 9. Dar de baja a un usuario
        System.out.println("\n--- Dar de baja a usuario ---");
        System.out.println("Dando de baja a Juan Perez: " + (biblioteca.darDeBajaUsuario(usuario2.getId()) ? "Éxito" : "Fracaso"));
    }
}