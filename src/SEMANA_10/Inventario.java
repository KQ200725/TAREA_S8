/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_10;
import java.io.*;
import java.util.*;
/**
 *
 * @author User
 */
// Clase que gestiona el inventario de productos
class Inventario {

    // Lista que almacena los productos registrados en el inventario
    private List<Producto> productos = new ArrayList<>();

    
    private static final String ARCHIVO = "inventario.txt";

    // Constructor: al crear un objeto Inventario, carga los datos desde el archivo
    public Inventario() {
        cargarDesdeArchivo(); // Llama al método que lee los productos desde el archivo
    }

    // Método público para agregar un producto al inventario
    public void agregarProducto(Producto p) {
        productos.add(p); // Añade el producto a la lista
        if (guardarEnArchivo()) { // Intenta guardar la lista actualizada en el archivo
            System.out.println(" Producto agregado: " + p.getNombre()); // Mensaje de éxito
        } else {
            System.out.println(" Error al guardar el producto."); // Mensaje de error
        }
    }

    // Método público para actualizar la cantidad de un producto existente
    public void actualizarCantidad(String id, int nuevaCantidad) {
        for (Producto p : productos) { // Recorre la lista de productos
            if (p.getId().equals(id)) { // Busca el producto con el ID especificado
                p.setCantidad(nuevaCantidad); // Actualiza la cantidad del producto
                if (guardarEnArchivo()) { // Guarda los cambios en el archivo
                    System.out.println(" Cantidad actualizada para: " + p.getNombre()); // Mensaje de éxito
                }
                return; // Sale del método después de actualizar
            }
        }
        System.out.println("️ Producto no encontrado."); // Mensaje si no se encuentra el producto
    }

    // Método público para eliminar un producto del inventario
    public void eliminarProducto(String id) {
        Iterator<Producto> it = productos.iterator(); // Crea un iterador para recorrer la lista
        while (it.hasNext()) { // Mientras haya elementos en la lista
            if (it.next().getId().equals(id)) { // Si el ID coincide con el buscado
                it.remove(); // Elimina el producto de la lista
                if (guardarEnArchivo()) { // Guarda los cambios en el archivo
                    System.out.println("️ Producto eliminado."); // Mensaje de éxito
                }
                return; // Sale del método después de eliminar
            }
        }
        System.out.println("️ Producto no encontrado."); // Mensaje si no se encuentra el producto
    }

    // Método público para mostrar todos los productos del inventario
    public void mostrarInventario() {
        System.out.println("\n Inventario actual:"); // Título de la sección
        for (Producto p : productos) { // Recorre la lista de productos
            // Muestra los datos del producto con formato
            System.out.printf("ID: %s | Nombre: %s | Precio: %.2f | Cantidad: %d%n",
                              p.getId(), p.getNombre(), p.getPrecio(), p.getCantidad());
        }
    }

    // Método privado para cargar los productos desde el archivo
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO); // Crea un objeto File con el nombre del archivo
        if (!archivo.exists()) return; // Si el archivo no existe, no hace nada

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea; // Variable para almacenar cada línea del archivo
            while ((linea = br.readLine()) != null) { // Lee línea por línea
                Producto p = Producto.desdeArchivo(linea); // Convierte la línea en un objeto Producto
                if (p != null) productos.add(p); // Si el producto es válido, lo agrega a la lista
            }
        } catch (IOException e) {
            // Muestra mensaje si ocurre un error al leer el archivo
            System.out.println(" Error al cargar inventario: " + e.getMessage());
        }
    }

    // Método privado para guardar los productos en el archivo
    private boolean guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : productos) { // Recorre la lista de productos
                bw.write(p.toArchivo()); // Escribe la representación en texto del producto
                bw.newLine(); // Inserta un salto de línea
            }
            return true; // Indica que se guardó correctamente
        } catch (IOException e) {
            // Muestra mensaje si ocurre un error al guardar
            System.out.println(" Error al guardar inventario: " + e.getMessage());
            return false; // Indica que hubo un error
        }
    }
}
