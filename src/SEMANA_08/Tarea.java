/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_08;

/**
 *
 * @author User
 */
public class Tarea {
      // Atributos privados para encapsulación
    private String id;          
    private String titulo;      
    private String descripcion; 
    private String estado;      
    
    // Constructor para inicializar una tarea
    public Tarea(String id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = "Pendiente"; // Estado inicial por defecto
    }
    
    // Métodos getter para acceder a los atributos
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
    
    // Método para cambiar el estado de la tarea
    public void completarTarea() {
        this.estado = "Completada";
    }
    
    // Método toString para mostrar información de la tarea
    @Override
    public String toString() {
        return String.format("ID: %s | %s | Estado: %s", id, titulo, estado);
    }
}
