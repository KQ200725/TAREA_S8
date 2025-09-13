/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_13;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AplicacionTareasSimple extends JFrame {

    private final JTextField campoTextoTarea;
    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaTareas;

    public AplicacionTareasSimple() {
        // Configuración de la ventana principal
        setTitle("Administrador de Tareas");
        setSize(400, 300); // Defino el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout(10, 10));

        // Paneles para organizar los componentes
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 10, 0));

        // Creación de componentes
        JLabel etiquetaTitulo = new JLabel("Ingrese una nueva tarea:");
        campoTextoTarea = new JTextField(20);
        JButton botonAgregar = new JButton("Agregar"); // Creo el botón para agregar tareas
        JButton botonLimpiar = new JButton("Limpiar"); // Creo el botón para limpiar el campo de texto

        // Configuración de la lista de tareas
        modeloLista = new DefaultListModel<>(); // Inicializo el modelo de lista que almacenará las tareas
        listaTareas = new JList<>(modeloLista); // Creo la lista visual usando el modelo.
        JScrollPane panelDesplazamiento = new JScrollPane(listaTareas);

        // Añadir componentes a los paneles
        panelEntrada.add(etiquetaTitulo);
        panelEntrada.add(campoTextoTarea);
        
        panelBotones.add(botonAgregar); // Agrego el botón "Agregar" al panel de botones
        panelBotones.add(botonLimpiar);  // Agrego el botón "Limpiar" al panel de botones

        // Añadir paneles y la lista a la ventana principal
        add(panelEntrada, BorderLayout.NORTH);
        add(panelDesplazamiento, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Manejo de eventos (ActionListener)
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTarea();
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampo();
            }
        });
    }

    // Método para agregar una tarea a la lista
    private void agregarTarea() {
        String textoTarea = campoTextoTarea.getText().trim(); // Obtengo el texto del campo y elimino espacios
        if (!textoTarea.isEmpty()) { // Verifico que no esté vacío
            modeloLista.addElement(textoTarea); // Agrego la tarea al modelo de lista
            campoTextoTarea.setText(""); // Limpiar el campo de texto
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una tarea válida.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método para limpiar el campo de texto
    private void limpiarCampo() { // Este método limpia el campo de texto
        campoTextoTarea.setText(""); // Simplemente borro el contenido del campo
    }

    public static void main(String[] args) {  // creoque lmetodo main
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionTareasSimple().setVisible(true); // Creo una instancia de mi aplicación y la hago visible
            }
        });
    }
}