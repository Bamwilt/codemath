/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Bryan Maradiaga
 */
public class modelo {
 private String codigo, name, ejercicio;
 private int numerotxt;
  
    public void imprimircodigo(){
        //el ejercicio en texto 
        codigo =EjercicioBienEscrito();
        //escribir el texto en el txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(codigo);
        } catch (IOException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String EjercicioBienEscrito() {
    // Expresión regular para detectar cualquier letra
    String ExpresionRegularLetras = "([a-zA-Z])";
    // Expresión regular para detectar letras seguidas opcionalmente de un dígito
    String ExpresionRegularLetrasDigitos = "([a-zA-Z])(\\d?)";

    // Creamos un patrón a partir de la primera expresión regular
    Pattern patronLetras = Pattern.compile(ExpresionRegularLetras);
    Matcher coincidenciasLetras = patronLetras.matcher(ejercicio);

    // Creamos un conjunto para almacenar las letras encontradas
    Set<String> letrasUnicas = new HashSet<>();

    // Creamos un StringBuffer para construir el string con "syms letra;" de manera eficiente
    StringBuffer symsMasLetra = new StringBuffer();

    // Usamos un bucle while para encontrar todas las letras y agregarlas con "syms "
    while (coincidenciasLetras.find()) {
        String letra = coincidenciasLetras.group(1);
        if (!letrasUnicas.contains(letra)) {
            // Agregamos la letra al conjunto y al StringBuffer solo si no ha sido detectada antes
            letrasUnicas.add(letra);
            symsMasLetra.append("syms ").append(letra).append(";\n");
        }
    }

    // Creamos un patrón a partir de la segunda expresión regular
    Pattern patronLetrasYDigitos = Pattern.compile(ExpresionRegularLetrasDigitos);
    Matcher coincidenciasLetrasYDigitos = patronLetrasYDigitos.matcher(ejercicio);

    // Creamos un StringBuffer para construir el string modificado de manera eficiente
    StringBuffer ejercicioModificado = new StringBuffer();

    // Usamos un bucle while para encontrar y reemplazar todas las ocurrencias
    while (coincidenciasLetrasYDigitos.find()) {
        // Obtenemos la letra
        String letra = coincidenciasLetrasYDigitos.group(1);
        // Obtenemos el dígito, si existe
        String digito = coincidenciasLetrasYDigitos.group(2);

        // Verificamos si hay un dígito después de la letra
        if (digito.isEmpty()) {
            // No hay dígito, dejamos la letra como está
            coincidenciasLetrasYDigitos.appendReplacement(ejercicioModificado, letra);
        } else {
            // Si hay una letra después de un número, reemplazamos por '^' en lugar de '*'
            coincidenciasLetrasYDigitos.appendReplacement(ejercicioModificado, "$1^" + digito);
        }
    }
    // Añadimos el resto del texto original que no tuvo coincidencias
    coincidenciasLetrasYDigitos.appendTail(ejercicioModificado);

    // Modificamos el ejercicio modificado para que las letras juntas se conviertan en producto
    String ejercicioModificadoFinal = ejercicioModificado.toString().replaceAll("([a-zA-Z])([a-zA-Z])", "$1*$2");
    // Modificamos el ejercicio modificado para que los números seguidos de letra tengan un asterisco '*'
    ejercicioModificadoFinal = ejercicioModificadoFinal.replaceAll("(\\d+)([a-zA-Z])", "$1*$2");

    // Construimos la cadena LetrasMasComas
    StringBuilder letrasMasComas = new StringBuilder();
    for (String letra : letrasUnicas) {
        letrasMasComas.append(letra).append(",");
    }
    if (letrasMasComas.length() > 0) {
        // Eliminamos la última coma
        letrasMasComas.deleteCharAt(letrasMasComas.length() - 1);
    }
     // Generamos el código de salida con las instrucciones solicitadas

    return symsMasLetra.toString() + "\n ecuacion= " + ejercicioModificadoFinal + 
            "\n soluciones = solve(ecuacion," + letrasMasComas.toString() + ");\n disp('Las soluciones de la ecuación son:');\n"+
            " disp(soluciones);";
}

  public void crearTxT() {
      
        String defaultName;
        // Cuadro de diálogo para seleccionar la opción
        Object[] options = {"Nombre por defecto", "Crear un nombre"};
        int choice = JOptionPane.showOptionDialog(null,
                "Creando archivo nuevo para ecuaciones:",
                "Crear Archivo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                options, options[0]);

        // Asignar el nombre según la elección del usuario
        if (choice == JOptionPane.YES_OPTION) {
            // Opción: Nombre por defecto
            do {
                defaultName = "Ecuacion Matlab " + numerotxt + ".txt";
                numerotxt++;
            } while (new File(defaultName).exists());
            name = defaultName;
        } else if (choice == JOptionPane.NO_OPTION) {
            // Opción: Crear un nombre
            name = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo:", "Nombre del archivo", JOptionPane.PLAIN_MESSAGE);
            if (name == null || name.trim().isEmpty()) {
                // Si el usuario no ingresa un nombre, usar el nombre por defecto
                do {
                    defaultName = "Ecuacion Matlab " + numerotxt + ".txt";
                    numerotxt++;
                } while (new File(defaultName).exists());
                name = defaultName;
            } else {
                // Añadir la extensión .txt si no está presente
                if (!name.endsWith(".txt")) {
                    name = name + ".txt";
                }
            }
        }

        // Crear un objeto File con el nombre proporcionado
        File file = new File(name);
        // Verificar si el archivo ya existe
        if (!file.exists()) {
            // Crear el archivo solo si no existe
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("disp('MATLAB ECUACION')\n");
                String Ruta = file.getAbsolutePath();

                // Crear un cuadro de diálogo con un campo de texto para la ruta y un botón de copiar
                JTextField TextoDeRuta = new JTextField(Ruta);
                JButton BotonCopiar = new JButton("Copiar");

                // boton copiar ruta
                BotonCopiar.addActionListener(e -> {
                    StringSelection selection = new StringSelection(Ruta);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, null);
                    BotonCopiar.setText("Copiado");
                    BotonCopiar.setFocusable(false);
                    // feedback
                    BotonCopiar.setBackground(Color.CYAN);
                    // fin feedback
                });

                // crear mensaje de creacion en el panel
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel("Archivo creado exitosamente. Ruta:"), BorderLayout.NORTH);
                panel.add(TextoDeRuta, BorderLayout.CENTER);
                panel.add(BotonCopiar, BorderLayout.EAST);
                // mensaje
                JOptionPane.showMessageDialog(null, panel, "Crear Archivo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
  
public void elegirArchivo() {
    // Crear un cuadro de diálogo para elegir el archivo de texto
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Seleccione un archivo de texto");
    
    // Agregar un filtro para mostrar solo archivos de texto
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
    fileChooser.setFileFilter(filter);
    
    // Mostrar el cuadro de diálogo y esperar a que el usuario elija un archivo
    int result = fileChooser.showOpenDialog(null); // Cambia null por el componente de la vista si es necesario
    
    // Verificar si el usuario eligió un archivo
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        // Obtener solo el nombre del archivo seleccionado
        String fileName = selectedFile.getName();
        
        // Establecer el nombre del archivo seleccionado en el modelo
        setName(fileName);
    }
}

public void cuestionario() {
   String mensaje = "El patrón Modelo-Vista-Controlador (MVC) se utiliza para organizar " +
                      "el código en una aplicación de manera que las preocupaciones estén separadas.\n\n" +
                      "Recuerda que:\n" +
                      "- El modelo representa los datos y la lógica del programa aqui va el codigo base.\n" +
                      "- La vista es la interfaz de usuario que muestra los datos, recuerda que toda interfas interactuable va en publico.\n" +
                      "- El controlador actúa como intermediario, maneja las interacciones. recuerda que si una interfaz tiene accion hay que darle un Listener " +
                      "mvc inicializa todo";

    JOptionPane.showMessageDialog(null, mensaje, "Información sobre el patrón MVC", JOptionPane.INFORMATION_MESSAGE); 
}


  
     //getseters 
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getNumerotxt() {
        return numerotxt;
    }

    public void setNumerotxt(int numerotxt) {
        this.numerotxt = numerotxt;
    }
    
    
}
 
