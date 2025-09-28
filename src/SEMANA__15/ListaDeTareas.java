/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA__15;

/**
 *
 * @author User
 */
import javax.swing.*; // Estoy importando los componentes gráficos 
import java.awt.*; // Aquí traigo herramientas para organizar y diseñar los elementos visuales.
import java.awt.event.*; // Importo lo necesario para que los botones y clics funcionen.
import java.util.ArrayList; 

public class ListaDeTareas extends JFrame { // Estoy creando una clase que representa mi ventana principal.

    // Declaro los elementos que voy a usar en la interfaz.
    private DefaultListModel<Tarea> modeloLista; // Aquí guardo todas las tareas que se van añadiendo.
    private JList<Tarea> listaTareas; // Esta es la lista visual que muestra las tareas.
    private JTextField campoNuevaTarea; // Este campo sirve para escribir una nueva tarea.
    private JButton btnAgregar, btnCompletar, btnEliminar; // Botones para añadir, completar y eliminar tareas.

    // Creo una clase interna para representar cada tarea.
    static class Tarea {
        String texto; // Aquí guardo el texto de la tarea.
        boolean completada; // Esta variable me dice si la tarea está hecha o no.

        Tarea(String texto) { // Cuando creo una tarea, le asigno el texto que escribí.
            this.texto = texto;
            this.completada = false; // Al principio, la tarea no está completada.
        }

        @Override
        public String toString() {
            return texto; // Cuando muestro la tarea, quiero que se vea solo el texto.
        }
    }

    public ListaDeTareas() { // Este es el constructor, aquí armo toda la ventana.
        super("Lista de Tareas"); // Le pongo un título a la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Si cierro la ventana, la aplicación se termina.
        setSize(400, 350); // Le doy un tamaño a la ventana.
        setLocationRelativeTo(null); // Hago que la ventana aparezca centrada en la pantalla.

        // Inicializo el modelo y la lista visual.
        modeloLista = new DefaultListModel<>(); // Creo el modelo donde guardaré las tareas.
        listaTareas = new JList<>(modeloLista); // Creo la lista visual y la conecto al modelo.
        listaTareas.setCellRenderer(new RendererTarea()); // Personalizo cómo se ve cada tarea (por ejemplo, si está tachada).

        // Creo el campo de texto y los botones.
        campoNuevaTarea = new JTextField(); // Campo para escribir la nueva tarea.
        btnAgregar = new JButton("Añadir Tarea"); // Botón para añadir la tarea escrita.
        btnCompletar = new JButton("Marcar como Completada"); // Botón para marcar una tarea como hecha.
        btnEliminar = new JButton("Eliminar Tarea"); // Botón para borrar una tarea.

        // Organizo el campo de texto y el botón en un panel superior.
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5)); // Panel con diseño horizontal.
        panelSuperior.add(campoNuevaTarea, BorderLayout.CENTER); // Pongo el campo en el centro del panel.
        panelSuperior.add(btnAgregar, BorderLayout.EAST); // Pongo el botón a la derecha.

        // Organizo los botones de completar y eliminar en un panel inferior.
        JPanel panelInferior = new JPanel(); // Panel simple para los botones.
        panelInferior.add(btnCompletar); // Agrego el botón de completar.
        panelInferior.add(btnEliminar); // Agrego el botón de eliminar.

        // Organizo toda la ventana con BorderLayout.
        setLayout(new BorderLayout(10, 10)); // Diseño con espacios entre los paneles.
        add(panelSuperior, BorderLayout.NORTH); // Pongo el panel de entrada arriba.
        add(new JScrollPane(listaTareas), BorderLayout.CENTER); // Pongo la lista en el centro con scroll.
        add(panelInferior, BorderLayout.SOUTH); // Pongo los botones abajo.

        // Conecto los botones y el campo de texto con sus acciones.
        btnAgregar.addActionListener(e -> agregarTarea()); // Si hago clic en "Añadir", se ejecuta el método para agregar.
        campoNuevaTarea.addActionListener(e -> agregarTarea()); // También puedo presionar Enter para añadir la tarea.

        btnCompletar.addActionListener(e -> marcarComoCompletada()); // Si hago clic en "Completar", se marca la tarea.
        btnEliminar.addActionListener(e -> eliminarTarea()); // Si hago clic en "Eliminar", se borra la tarea.

        // Permito marcar como completada con doble clic en la lista.
        listaTareas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Si hago doble clic...
                    marcarComoCompletada(); // ...se marca la tarea como completada.
                }
            }
        });
    }

    // Método para añadir una nueva tarea.
    private void agregarTarea() {
        String texto = campoNuevaTarea.getText().trim(); // Obtengo el texto y elimino espacios.
        if (!texto.isEmpty()) { // Verifico que no esté vacío.
            modeloLista.addElement(new Tarea(texto)); // Creo una nueva tarea y la añado al modelo.
            campoNuevaTarea.setText(""); // Limpio el campo de texto.
        }
    }

    // Método para marcar una tarea como completada.
    private void marcarComoCompletada() {
        int idx = listaTareas.getSelectedIndex(); // Obtengo el índice de la tarea seleccionada.
        if (idx != -1) { // Verifico que haya una selección.
            Tarea tarea = modeloLista.getElementAt(idx); // Obtengo la tarea.
            tarea.completada = !tarea.completada; // Cambio su estado (si estaba hecha, la desmarco; si no, la marco).
            listaTareas.repaint(); // Redibujo la lista para que se vea el cambio.
        }
    }

    // Método para eliminar una tarea.
    private void eliminarTarea() {
        int idx = listaTareas.getSelectedIndex(); // Obtengo el índice de la tarea seleccionada.
        if (idx != -1) { // Verifico que haya una selección.
            modeloLista.remove(idx); // Elimino la tarea del modelo.
        }
    }

    // Clase para personalizar cómo se ve cada tarea en la lista.
    static class RendererTarea extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Uso el renderizador por defecto.

            if (value instanceof Tarea) { // Verifico que el objeto sea una tarea.
                Tarea tarea = (Tarea) value;
                if (tarea.completada) { // Si está completada...
                    label.setText("<html><strike>" + tarea.texto + "</strike></html>"); // ...muestro el texto tachado.
                    label.setForeground(Color.GRAY); // Lo pongo en color gris.
                } else {
                    label.setText(tarea.texto); // Si no está completada, muestro el texto normal.
                    label.setForeground(Color.BLACK); // Lo pongo en color negro.
                }
            }

            return label; // Devuelvo el componente personalizado.
        }
    }

    // Método principal para ejecutar la aplicación.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaDeTareas().setVisible(true)); // Creo y muestro la ventana.
    }
}