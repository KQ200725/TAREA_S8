/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEMANA_16;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

// Esta es mi clase principal 
public class CapturaTeclaCApp extends JFrame { 

    // Declaro la lista que contendrá mis elementos Y La haré privada 
    private JList<String> itemList; 
    
    private DefaultListModel<String> listModel; 

    // Creo mi constructor 
    public CapturaTeclaCApp() { 
        
        
        // Asigno un título claro a mi ventana.
        setTitle("Captura de Tecla 'C' en JList (Key Bindings)"); 
        // Defino que la aplicación se cierre al pulsar la 'X'.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // Elijo un BorderLayout para organizar mis componentes fácilmente.
        setLayout(new BorderLayout(10, 10)); 


        
        // Inicializo mi modelo de datos para la lista.
        listModel = new DefaultListModel<>(); 
        // Agrego los elementos iniciales a mi modelo.
        listModel.addElement("Elemento 1: Presiona 'C'"); 
        listModel.addElement("Elemento 2: Presiona 'C'"); 
        listModel.addElement("Elemento 3: Presiona 'C'"); 
        // Ahora creo mi JList pasándole el modelo que acabo de llenar.
        itemList = new JList<>(listModel); 
        // Configuro la lista para que solo pueda seleccionar un elemento a la vez.
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        // Envuelvo la lista en un JScrollPane para que tenga barras de desplazamiento si es necesario.
        JScrollPane scrollPane = new JScrollPane(itemList); 
        
        // Creo el botón de ejemplo
        JButton button = new JButton("Botón de Ejemplo"); 

        // Creo una etiqueta para mostrar mensajes de estado al usuario.
        JLabel statusLabel = new JLabel("Presiona 'C' sobre la lista para alternar la selección."); 
        
      
        
        // Coloco mi lista con scroll en el centro de la ventana.
        add(scrollPane, BorderLayout.CENTER); 
        // Uso un JPanel auxiliar para organizar el botón y la etiqueta en la parte inferior.
        JPanel southPanel = new JPanel(new BorderLayout()); 
        // Coloco el botón a la izquierda del panel inferior.
        southPanel.add(button, BorderLayout.WEST); 
        // Pongo la etiqueta de estado en el centro del panel inferior.
        southPanel.add(statusLabel, BorderLayout.CENTER); 
        // Añado el panel inferior a la parte inferior de mi ventana principal.
        add(southPanel, BorderLayout.SOUTH); 

     
        
        // Creo un KeyStroke para detectar la pulsación de la tecla 'C' sin modificadores.
        KeyStroke keyStrokeC = KeyStroke.getKeyStroke(KeyEvent.VK_C, 0); 

        // Asigno un nombre único a la acción que voy a crear.
        String actionName = "alternateSelectionAction"; 

        // Defino la acción que se ejecutará; utilizo un AbstractAction para simplificar.
        Action toggleSelectionAction = new AbstractAction() { 
            // Implemento el método que se llama al activarse la acción (cuando se presiona 'C').
            @Override
            public void actionPerformed(ActionEvent e) { 
                // Obtengo el índice del elemento que el usuario tiene seleccionado.
                int selectedIndex = itemList.getSelectedIndex(); 
                
                // Si el índice es diferente de -1, significa que hay algo seleccionado.
                if (selectedIndex != -1) { 
                    // Como hay selección, la quito (alterno el estado).
                    itemList.clearSelection(); 
                    // Actualizo mi etiqueta de estado para informar al usuario.
                    statusLabel.setText("Tecla 'C' presionada. Selección del índice " + selectedIndex + " *quitada*."); 
                // Si no hay nada seleccionado, pero la lista tiene elementos:
                } else if (listModel.getSize() > 0) { 
                    // Selecciono el primer elemento de la lista.
                    itemList.setSelectedIndex(0); 
                    // Muestro el nuevo estado en la etiqueta.
                    statusLabel.setText("Tecla 'C' presionada. Se seleccionó el índice 0."); 
                // Si la lista está vacía, muestro un mensaje de que no puedo hacer nada.
                } else { 
                    statusLabel.setText("Tecla 'C' presionada. Lista vacía."); 
                }
                
                // Cumplo con el requisito de imprimir un mensaje en consola.
                System.out.println(">>> ¡ACCIÓN 'C' EJECUTADA EN CONSOLA! <<<"); 
            }
        };

        
        InputMap inputMap = itemList.getInputMap(JComponent.WHEN_FOCUSED); 
        

        inputMap.put(keyStrokeC, actionName); 

        
        itemList.getActionMap().put(actionName, toggleSelectionAction); 


    
        
        // Ajusto el tamaño de la ventana para que se adapte a todos mis componentes.
        pack(); 
        // Centro la ventana en la pantalla para una mejor experiencia de usuario.
        setLocationRelativeTo(null); 
    }

    // Este es el punto de inicio de mi aplicación.
    public static void main(String[] args) { 
        // Uso SwingUtilities.invokeLater para garantizar que la interfaz se cree en el hilo correcto (EDT).
        SwingUtilities.invokeLater(() -> { 
            // Creo una instancia de mi aplicación.
            CapturaTeclaCApp frame = new CapturaTeclaCApp(); 
            // Hago visible la ventana.
            frame.setVisible(true); 
            
            // Establezco el foco en la JList al iniciar, como es un requisito para que el KeyBinding funcione al principio.
            frame.itemList.requestFocusInWindow(); 
        });
    }
}