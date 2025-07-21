/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_08;
import java.util.List;
/**
 *
 * @author User
 */
public class Dashboard {
    
    private TareaServicio tareaServicio;  // Servicio para manejar la lógica de tareas
    private MenuUtil menuUtil;            // Utilidad para manejo de menús e interfaz
    private boolean sistemaActivo;        // Bandera para controlar el ciclo principal
    
    // Constructor que inicializa todas las dependencias
    public Dashboard() {
        this.tareaServicio = new TareaServicio(); // Crear instancia del servicio
        this.menuUtil = new MenuUtil();           // Crear instancia del menú
        this.sistemaActivo = true;                // Inicializar sistema como activo
        System.out.println(" Sistema inicializado correctamente.");
    }
    //inciamos
    /**
     * Método principal que inicia la aplicación
     * Controla el flujo principal del programa
     */
    public void iniciar() {
        try {
            // Limpiar pantalla y mostrar bienvenida
            menuUtil.limpiarPantalla();
            mostrarBienvenida();
            
            // Ciclo principal del sistema - se ejecuta mientras esté activo
            while (sistemaActivo) {
                // Mostrar el menú principal
                menuUtil.mostrarMenu();
                
                // Leer la opción seleccionada por el usuario
                int opcion = menuUtil.leerOpcion();
                
                // Procesar la opción seleccionada
                procesarOpcion(opcion);
            }
            
            // Mostrar mensaje de despedida al salir del sistema
            mostrarDespedida();
            
        } catch (Exception e) {
            // Manejar cualquier error inesperado
            menuUtil.mostrarError("Error inesperado en el sistema: " + e.getMessage());
        } finally {
            // Cerrar recursos al finalizar
            menuUtil.cerrar();
        }
    }
    
    // Método privado para mostrar mensaje de bienvenida y estado del sistema
    private void mostrarBienvenida() {
        menuUtil.mostrarEncabezado();
        menuUtil.mostrarMensaje("¡Bienvenido al Sistema de Gestión de Tareas!");
        menuUtil.mostrarMensaje(" Estado del proyecto: ACTIVO ✓");
        menuUtil.mostrarMensaje(" Versión: 1.0.0");
        menuUtil.mostrarMensaje("Desarrollado para SEMANA_08 - POO");
        
        // Mostrar estadísticas iniciales
        tareaServicio.mostrarEstadisticas();
        System.out.println();
    }
    
    // Método privado para procesar la opción seleccionada por el usuario
    private void procesarOpcion(int opcion) {
        System.out.println(); // Línea en blanco para mejor legibilidad
        
        // Switch para manejar cada opción del menú
        switch (opcion) {
            case 1:
                // Opción 1: Mostrar todas las tareas
                mostrarTodasLasTareas();
                break;
            case 2:
                
                completarTarea();
                break;
            case 3:
                
                agregarNuevaTarea();
                break;
            case 4:
              
                mostrarEstadisticas();
                break;
            case 5:
               
                sistemaActivo = false; 
                break;
            default:
               
                menuUtil.mostrarError("Opción no válida. Seleccione una opción del 1 al 5.");
        }
        
        // Pausar para que el usuario pueda leer el resultado (excepto al salir)
        if (sistemaActivo) {
            menuUtil.pausar();
            System.out.println();
        }
    }
    
    // Método para mostrar todas las tareas registradas en el sistema
    private void mostrarTodasLasTareas() {
        // Obtener la lista de tareas del servicio
        List<Tarea> tareas = tareaServicio.obtenerTareas();
        
        System.out.println(" LISTA COMPLETA DE TAREAS:");
        System.out.println("─".repeat(50)); // Línea divisoria
        
        // Verificar si hay tareas para mostrar
        if (tareas.isEmpty()) {
            menuUtil.mostrarMensaje("ℹ️  No hay tareas registradas en el sistema.");
        } else {
            // Iterar y mostrar cada tarea con formato mejorado
            int contador = 1;
            for (Tarea tarea : tareas) {
                // Mostrar información detallada de cada tarea
                System.out.printf("%d. %s\n", contador, tarea.toString());
                System.out.printf("   Descripción: %s\n", tarea.getDescripcion());
                System.out.println("   " + "─".repeat(45));
                contador++;
            }
        }
    }
    
    // Método para completar una tarea existente
    private void completarTarea() {
        System.out.println(" COMPLETAR TAREA");
        System.out.println("─".repeat(30));
        
        // Primero mostrar las tareas pendientes
        List<Tarea> tareas = tareaServicio.obtenerTareas();
        System.out.println("Tareas disponibles:");
        
        boolean hayTareasPendientes = false;
        for (Tarea tarea : tareas) {
            // Solo mostrar tareas que no estén completadas
            if (!tarea.getEstado().equals("Completada")) {
                System.out.println("  • ID: " + tarea.getId() + " - " + tarea.getTitulo());
                hayTareasPendientes = true;
            }
        }
        
        if (!hayTareasPendientes) {
            menuUtil.mostrarMensaje("ℹ️  No hay tareas pendientes para completar.");
            return;
        }
        
        // Solicitar ID de la tarea al usuario
        String id = menuUtil.leerTexto("\nIngrese el ID de la tarea a completar: ");
        
        // Validar que el ID no esté vacío
        if (id.isEmpty()) {
            menuUtil.mostrarError("El ID no puede estar vacío.");
            return;
        }
        
        // Buscar la tarea por ID
        Tarea tarea = tareaServicio.buscarPorId(id);
        
        if (tarea != null) {
            // Verificar si ya está completada
            if (tarea.getEstado().equals("Completada")) {
                menuUtil.mostrarError("La tarea '" + tarea.getTitulo() + "' ya está completada.");
            } else {
                // Completar la tarea
                tareaServicio.completarTarea(id);
                menuUtil.mostrarExito("Tarea '" + tarea.getTitulo() + "' completada exitosamente.");
            }
        } else {
            // Tarea no encontrada
            menuUtil.mostrarError("No se encontró una tarea con el ID: " + id);
        }
    }
    
    // Método para agregar una nueva tarea al sistema
    private void agregarNuevaTarea() {
        System.out.println(" AGREGAR NUEVA TAREA");
        System.out.println("─".repeat(30));
        
        // Solicitar datos de la nueva tarea con validaciones
        String id = menuUtil.leerTexto("Ingrese ID de la nueva tarea: ");
        if (id.isEmpty()) {
            menuUtil.mostrarError("El ID es obligatorio.");
            return;
        }
        
        // Verificar que no exista una tarea con el mismo ID
        if (tareaServicio.buscarPorId(id) != null) {
            menuUtil.mostrarError("Ya existe una tarea con el ID: " + id);
            return;
        }
        
        String titulo = menuUtil.leerTexto("Ingrese título de la tarea: ");
        if (titulo.isEmpty()) {
            menuUtil.mostrarError("El título es obligatorio.");
            return;
        }
        
        String descripcion = menuUtil.leerTexto("Ingrese descripción: ");
        if (descripcion.isEmpty()) {
            descripcion = "Sin descripción"; // Valor por defecto
        }
        
        // Crear y agregar la nueva tarea
        Tarea nuevaTarea = new Tarea(id, titulo, descripcion);
        tareaServicio.agregarTarea(nuevaTarea);
        
        menuUtil.mostrarExito("Tarea agregada exitosamente:");
        System.out.println("  " + nuevaTarea.toString());
        System.out.println("  Descripción: " + nuevaTarea.getDescripcion());
    }
    
    // Método para mostrar estadísticas del sistema
    private void mostrarEstadisticas() {
        System.out.println(" ESTADÍSTICAS DEL SISTEMA");
        System.out.println("─".repeat(40));
        
        // Delegar al servicio para mostrar estadísticas
        tareaServicio.mostrarEstadisticas();
    }
    
    // Método privado para mostrar mensaje de despedida
    private void mostrarDespedida() {
        menuUtil.limpiarPantalla();
        System.out.println("¡GRACIAS POR USAR EL SISTEMA!");
        System.out.println("═════════════════════════════════");
        System.out.println("Sistema de Gestión de Tareas - SEMANA_08");
        System.out.println("Desarrollado con Programación Orientada a Objetos");
        System.out.println("¡Hasta pronto!");
        System.out.println();
    }
    
    /**
     * Método main - Punto de entrada de la aplicación
     * Este método se ejecuta cuando se inicia el programa
     */
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Gestión de Tareas...");
        System.out.println();
        
        try {
            // Crear una instancia del dashboard e iniciar la aplicación
            Dashboard dashboard = new Dashboard();
            dashboard.iniciar(); // Llamar al método iniciar para comenzar el programa
            
        } catch (Exception e) {
            // Manejar errores críticos del sistema
            System.err.println("Error crítico en el sistema: " + e.getMessage());
            System.err.println("El programa se cerrará.");
        }
        
        System.out.println(" Sistema finalizado.");
    }
}
