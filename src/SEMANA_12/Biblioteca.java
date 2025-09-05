/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_12;

/**
 *
 * @author User
 */
import java.util.*; // Importa todas las clases de utilidades necesarias como Map, List, Set, HashMap, ArrayList y HashSet.

public class Biblioteca { // Define la clase principal que gestiona libros, usuarios y préstamos.

    private final Map<String, Libro> catalogoPorIsbn = new HashMap<>(); // Mapa que asocia cada ISBN con su objeto Libro para búsquedas rápidas.

    private final Map<String, Usuario> usuariosPorId = new HashMap<>(); // Mapa que asocia cada ID de usuario con su objeto Usuario.

    private final Set<String> isbnsPrestados = new HashSet<>(); // Conjunto que almacena los ISBN de libros prestados, evitando duplicados.

    public boolean anadirLibro(Libro libro) { // Método que intenta añadir un libro al catálogo.
        if (catalogoPorIsbn.containsKey(libro.getIsbn())) { return false; } // Si el ISBN ya existe, no se añade.
        catalogoPorIsbn.put(libro.getIsbn(), libro); return true; // Si no existe, se añade al catálogo y retorna true.
    }

    public boolean quitarLibro(String isbn) { // Método que intenta quitar un libro del catálogo.
        if (isbnsPrestados.contains(isbn)) { return false; } // Si el libro está prestado, no se puede quitar.
        return catalogoPorIsbn.remove(isbn) != null; // Si no está prestado, se elimina del catálogo y retorna si fue exitoso.
    }

    public boolean registrarUsuario(Usuario usuario) { // Método que registra un nuevo usuario.
        if (usuariosPorId.containsKey(usuario.getId())) { return false; } // Si el ID ya existe, no se registra.
        usuariosPorId.put(usuario.getId(), usuario); return true; // Si no existe, se añade al mapa y retorna true.
    }

    public boolean darDeBajaUsuario(String id) { // Método que elimina un usuario del sistema.
        return usuariosPorId.remove(id) != null; // Elimina el usuario por ID y retorna si fue exitoso.
    }

    public String prestarLibro(String isbn, String idUsuario) { // Método que presta un libro a un usuario.
        if (!catalogoPorIsbn.containsKey(isbn)) { return "Error: El libro no existe en el catálogo."; } // Verifica que el libro exista.
        if (!usuariosPorId.containsKey(idUsuario)) { return "Error: El usuario no está registrado."; } // Verifica que el usuario exista.
        if (isbnsPrestados.contains(isbn)) { return "Error: El libro ya está prestado."; } // Verifica que el libro no esté prestado.
        isbnsPrestados.add(isbn); // Marca el libro como prestado.
        Usuario usuario = usuariosPorId.get(idUsuario); // Obtiene el usuario por ID.
        usuario.getIsbnsPrestados().add(isbn); // Añade el ISBN a la lista de libros prestados del usuario.
        return "Éxito: Libro prestado a " + usuario.getNombre() + "."; // Retorna mensaje de éxito con el nombre del usuario.
    }

    public String devolverLibro(String isbn, String idUsuario) { // Método que procesa la devolución de un libro.
        Usuario usuario = usuariosPorId.get(idUsuario); // Obtiene el usuario por ID.
        if (usuario == null) { return "Error: El usuario no está registrado."; } // Verifica que el usuario exista.
        if (!isbnsPrestados.contains(isbn) || !usuario.getIsbnsPrestados().contains(isbn)) { return "Error: El libro no estaba prestado a este usuario."; } // Verifica que el libro esté prestado al usuario.
        isbnsPrestados.remove(isbn); // Elimina el ISBN del conjunto de libros prestados.
        usuario.getIsbnsPrestados().remove(isbn); // Elimina el ISBN de la lista del usuario.
        return "Éxito: Libro devuelto por " + usuario.getNombre() + "."; // Retorna mensaje de éxito con el nombre del usuario.
    }

    public List<Libro> buscarLibro(String query) { // Método que busca libros por título, autor o categoría.
        List<Libro> resultados = new ArrayList<>(); // Lista para almacenar los resultados de búsqueda.
        String lowerCaseQuery = query.toLowerCase(); // Convierte la consulta a minúsculas para búsqueda flexible.
        for (Libro libro : catalogoPorIsbn.values()) { // Itera sobre todos los libros del catálogo.
            if (libro.getTitulo().toLowerCase().contains(lowerCaseQuery) || libro.getAutor().toLowerCase().contains(lowerCaseQuery) || libro.getCategoria().toLowerCase().contains(lowerCaseQuery)) { resultados.add(libro); } // Si el título, autor o categoría contiene la consulta, se añade a resultados.
        }
        return resultados; // Retorna la lista de libros que coinciden con la búsqueda.
    }

    public List<Libro> listarLibrosPrestados(String idUsuario) { // Método que lista los libros prestados a un usuario.
        Usuario usuario = usuariosPorId.get(idUsuario); // Obtiene el usuario por ID.
        if (usuario == null) { return null; } // Si el usuario no existe, retorna null.
        List<Libro> librosPrestados = new ArrayList<>(); // Lista para almacenar los libros prestados.
        for (String isbn : usuario.getIsbnsPrestados()) { librosPrestados.add(catalogoPorIsbn.get(isbn)); } // Por cada ISBN prestado, añade el libro correspondiente a la lista.
        return librosPrestados; // Retorna la lista de libros prestados al usuario.
    }
}