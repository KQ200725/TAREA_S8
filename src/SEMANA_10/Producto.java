/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_10;

/**
 *
 * @author User
 */
// Clase que representa un producto con ID, nombre, precio y cantidad
class Producto {
    private String id;  // Identificador único del producto
    private String nombre; // Nombre descriptivo del producto
    private double precio; // Precio unitario del producto
    private int cantidad; // Cantidad disponible en inventario

    // Constructor que inicializa todos los atributos del producto
    public Producto(String id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getId() { return id; } // Devuelve el ID del producto
    public String getNombre() { return nombre; } // Devuelve el nombre del producto
    public double getPrecio() { return precio; } // Devuelve el precio del producto
    public int getCantidad() { return cantidad; } // Devuelve la cantidad disponible del producto

   // Permite actualizar la cantidad del producto
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String toArchivo() {
        return id + "|" + nombre + "|" + precio + "|" + cantidad;
    }
// Método estático que crea un objeto Producto a partir de una línea de texto
    public static Producto desdeArchivo(String linea) {
        // Divide la línea en partes usando el separador '|'
        String[] partes = linea.split("\\|");
        if (partes.length != 4) return null;
        try {
            // Extrae y convierte cada parte a su tipo correspondiente
            String id = partes[0];
            String nombre = partes[1];
            double precio = Double.parseDouble(partes[2]);
            int cantidad = Integer.parseInt(partes[3]);
            // Crea y devuelve el nuevo producto
            return new Producto(id, nombre, precio, cantidad);
      
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
