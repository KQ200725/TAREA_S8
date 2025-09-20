/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_14;

/**
 *
 * @author User
 */
// Importo los paquetes necesarios para construir la interfaz gráfica y manejar eventos
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
// Defino la clase principal que extiende JFrame para crear una ventana
public class AgendaPersonal extends JFrame {
    
    // Componentes principales
    private JTable tablaEventos; // Aquí mostraré los eventos en forma de tabla
    private DefaultTableModel modeloTabla; // Modelo que gestiona los datos de la tabla
    private JSpinner spinnerFecha;  // Selector para la fecha del evento
    private JSpinner spinnerHora; // Selector para la hora del evento
    private JTextField campoDescripcion; // Campo para escribir la descripción del evento
    private JButton btnAgregar; // Botón para agregar un nuevo evento
    private JButton btnEliminar;  // Botón para eliminar el evento seleccionado
    private JButton btnSalir;  // Botón para cerrar la aplicación
    
   // Defino los formateadores para mostrar fecha y hora en formato legible
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
    // Constructor de la clase, donde inicializo todo
    public AgendaPersonal() {
        inicializarComponentes(); // Creo y configuro los componentes
        configurarVentana(); // Organizo los componentes en la ventana
        configurarEventos();  // Asigno la lógica a los botones
    }
    
    private void inicializarComponentes() {  // Método para crear los componentes visuales
        // Configurar modelo de tabla
        String[] columnas = {"Fecha", "Hora", "Descripción"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloTabla); // Creo la tabla con el modelo
        
        // Configurar spinners
        configurarSpinners();
        
        // Campo de descripción
        campoDescripcion = new JTextField(20);
        
        // Botones
        btnAgregar = new JButton("Agregar"); // Botón para agregar eventos
        btnEliminar = new JButton("Eliminar seleccionado");
        btnSalir = new JButton("Salir");
    }
    
    private void configurarSpinners() {
        // Spinner de fecha - Iniciar con la fecha actual
        Date fechaActual = new Date(); // Obtengo la fecha actual
        SpinnerDateModel modeloFecha = new SpinnerDateModel(fechaActual, null, null, Calendar.DAY_OF_MONTH);
        spinnerFecha = new JSpinner(modeloFecha); // Creo el spinner con el modelo
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"); // Editor para mostrar la fecha en formato personalizado
        spinnerFecha.setEditor(editorFecha); // Asigno el editor al spinner
        spinnerFecha.setEditor(editorFecha);
        
        // Spinner de hora - Iniciar con hora actual redondeada a la hora más próxima
        Calendar calHora = Calendar.getInstance();
        calHora.set(Calendar.MINUTE, 0);  // Redondeo los minutos a cero
        calHora.set(Calendar.SECOND, 0); // Elimino los segundos
        calHora.set(Calendar.MILLISECOND, 0);
        
        // Crear fecha base solo para la hora (1 de enero de 1970 como referencia)
        Calendar fechaBase = Calendar.getInstance(); // Creo una fecha base para el spinner de hora
        fechaBase.set(1970, Calendar.JANUARY, 1);
        fechaBase.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY)); // Asigno la hora actual
        fechaBase.set(Calendar.MINUTE, 0); 
        fechaBase.set(Calendar.SECOND, 0);
        fechaBase.set(Calendar.MILLISECOND, 0);
        
        SpinnerDateModel modeloHora = new SpinnerDateModel(fechaBase.getTime(), null, null, Calendar.MINUTE);
        spinnerHora = new JSpinner(modeloHora); // Creo el spinner con el modelo
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editorHora);
        
        // Configurar tamaño preferido para los spinners
        spinnerFecha.setPreferredSize(new Dimension(120, 25)); // Ajusto el tamaño del spinner de fecha
        spinnerHora.setPreferredSize(new Dimension(80, 25));  // Ajusto el tamaño del spinner de hora
    }
       // Método para configurar la ventana principal
    private void configurarVentana() {
        setTitle("Agenda Personal"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior - Entrada de datos
        JPanel panelEntrada = crearPanelEntrada();  // Cierro la aplicación al cerrar la ventana
        add(panelEntrada, BorderLayout.NORTH);
        
        // Panel central - Lista de eventos
        JScrollPane scrollPane = new JScrollPane(tablaEventos);  // Agrego scroll a la tabla
        scrollPane.setPreferredSize(new Dimension(600, 300)); // Ajusto el tamaño del área de tabla
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior - Botones de acción
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
        
        pack(); // Ajusto el tamaño de la ventana automáticamente
        setLocationRelativeTo(null);
    }
    
    private JPanel crearPanelEntrada() {  // Método para crear el panel de entrada de datos
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Nuevo Evento")); // Le pongo un borde con título
        
        panel.add(new JLabel("Fecha (dd/MM/yyyy):")); // Etiqueta para la fecha
        panel.add(spinnerFecha);
        
        panel.add(new JLabel("Hora (HH:mm):")); // Etiqueta para la hora
        panel.add(spinnerHora);
        
        panel.add(new JLabel("Descripción:")); // Etiqueta para la descripción
        panel.add(campoDescripcion);
        
        // Agregar instrucciones para el usuario
        JLabel instrucciones = new JLabel("<html><small>Use las flechas o escriba directamente para cambiar fecha/hora</small></html>");
        instrucciones.setForeground(Color.GRAY); // Agrego las instrucciones
        panel.add(instrucciones); // Agrego las instrucciones
        panel.add(new JLabel(""));
        
        return panel; // Devuelvo el panel construido
    }
    // Método para crear el panel de botones
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        
        panel.add(btnAgregar);  // Agrego el botón de agregar
        panel.add(btnEliminar); // Agrego el botón de eliminar
        panel.add(btnSalir);  // Agrego el botón de salir
        
        return panel;  // Devuelvo el panel construido
    } 
    // Método para asignar la lógica a los botones
    private void configurarEventos() {
        // Evento para el botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEvento(); // Llamo al método para agregar evento
            }
        });
        
        // Evento para el botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });
        
        // Evento para el botón Salir
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    // Método para agregar un evento a la tabla
    private void agregarEvento() {
        // Validar descripción
        String descripcion = campoDescripcion.getText().trim();
        if (descripcion.isEmpty()) { // Verifico si está vacía
            JOptionPane.showMessageDialog(this, 
                "La descripción no puede estar vacía", 
                "Validación", 
                JOptionPane.WARNING_MESSAGE);
            campoDescripcion.requestFocus();  // Devuelvo el foco al campo
            return; // Salgo del método
        }
        
        // Obtener fecha y hora seleccionadas por el usuario
        Date fecha = (Date) spinnerFecha.getValue();
        Date hora = (Date) spinnerHora.getValue();
        
        // Formatear fecha y hora según lo seleccionado por el usuario
        String fechaFormateada = formatoFecha.format(fecha);
        String horaFormateada = formatoHora.format(hora);
        
        // Agregar fila a la tabla
        Object[] fila = {fechaFormateada, horaFormateada, descripcion};
        modeloTabla.addRow(fila);
        
        // Limpiar campo descripción y darle foco
        campoDescripcion.setText("");
        campoDescripcion.requestFocus();
        
        // Mensaje de confirmación
        JOptionPane.showMessageDialog(this, 
            "Evento agregado correctamente:\n" + 
            fechaFormateada + " a las " + horaFormateada + "\n" + descripcion, 
            "Evento Agregado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void eliminarEvento() {
        int filaSeleccionada = tablaEventos.getSelectedRow();
        
        // Verificar si hay una fila seleccionada
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Selecciona un evento primero", 
                "Selección requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener información del evento a eliminar
        String fecha = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String hora = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String descripcion = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        
        // Pedir confirmación con información del evento
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Eliminar el siguiente evento?\n\n" +
            "Fecha: " + fecha + "\n" +
            "Hora: " + hora + "\n" +
            "Descripción: " + descripcion, 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        // Si confirma, eliminar la fila
        if (confirmacion == JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(this, 
                "Evento eliminado correctamente", 
                "Evento Eliminado", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // Configurar look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear y mostrar la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgendaPersonal().setVisible(true);
            }
        });
    }
}