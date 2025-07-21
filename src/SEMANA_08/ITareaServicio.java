/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_08;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */

/**
 * Interfaz que define operaciones sobre tareas
 * Aplica principio de Inversión de Dependencias (DIP)
 */

    interface ITareaServicio {
    void agregarTarea(Tarea tarea);
    List<Tarea> obtenerTareas();
    Tarea buscarPorId(String id);
    void completarTarea(String id);
}

/**
 * Implementación del servicio de tareas
 * Aplica principio de Responsabilidad Única (SRP)
 */
public class TareaServicio implements ITareaServicio {
    // Lista para almacenar las tareas en memoria
    private List<Tarea> tareas;
    
    // Constructor que inicializa la lista de tareas
    public TareaServicio() {
        this.tareas = new ArrayList<>();
        // Agregar algunas tareas ficticias para demostración
        cargarTareasFicticias();
    }
    
    // Método privado para cargar datos de prueba
    private void cargarTareasFicticias() {
        agregarTarea(new Tarea("001", "Estudiar POO", "Revisar conceptos de herencia y polimorfismo"));
        agregarTarea(new Tarea("002", "Hacer ejercicios", "Completar ejercicios del capítulo 5"));
        agregarTarea(new Tarea("003", "Proyecto final", "Desarrollar aplicación de gestión"));
    }
    
    // Implementación del método para agregar tareas
    @Override
    public void agregarTarea(Tarea tarea) {
        // Validar que la tarea no sea nula
        if (tarea != null) {
            tareas.add(tarea);
        }
    }
    
    // Implementación del método para obtener todas las tareas
    @Override
    public List<Tarea> obtenerTareas() {
        // Retornar una copia de la lista para proteger el estado interno
        return new ArrayList<>(tareas);
    }
    
    // Implementación del método para buscar tarea por ID
    @Override
    public Tarea buscarPorId(String id) {
        // Buscar en la lista usando streams para mayor eficiencia
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst()
                .orElse(null); // Retornar null si no encuentra la tarea
    }
    
    // Implementación del método para completar una tarea
    @Override
    public void completarTarea(String id) {
        Tarea tarea = buscarPorId(id);
        // Verificar que la tarea existe antes de modificarla
        if (tarea != null) {
            tarea.completarTarea();
        }
    
    }
}

