/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import modelo.modelo;
import vista.vista;



/**
 *
 * @author Bryan Maradiaga
 */
public abstract class controlador implements ActionListener {
    private vista view;
    private modelo model;

    public controlador(vista view, modelo model){
        this.view = view;
        this.model = model;
       
          
        this.view.generar.addActionListener(this);
        this.view.generar.setFocusable(false); 
        
        this.view.generartxt.addActionListener(this);
        this.view.generartxt.setFocusable(false); 
        
        this.view.botonGuardado.addActionListener(this);
        this.view.botonGuardado.setFocusable(false); 
        
        this.view.desarrollador.addActionListener(this);
        this.view.desarrollador.setFocusable(false); 
        
        //DETECTAR PRESIONAR BOTON EN TXTEJERCICIO  
        this.view.txtejercicio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) /*boton enter*/ {
                    generarCodigo();    //llamar a generar codigo
                }
            }
        });
        //fin de presionar
        
        
        //comandos 
          // Aquí es donde configuramos los Action Commands para los botones
    this.view.generar.setActionCommand("generar");
    this.view.generartxt.setActionCommand("generartxt");
       this.view.botonGuardado.setActionCommand("elegirtxt");
         this.view.desarrollador.setActionCommand("???");
    }

    public void iniciar(){
        view.setTitle("Generador de Código");
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    switch (command) {
        case "generar" -> generarCodigo(); 
        case "generartxt" -> model.crearTxT();
        case "elegirtxt" -> model.elegirArchivo();
        case "???" -> model.cuestionario();
        default ->  {
            }
    }
        // Agregar más casos para otros botones si es necesario
        // Manejar cualquier otro tipo de evento aquí si es necesario
        }

    private void generarCodigo() {
        model.setEjercicio(view.txtejercicio.getText());
        // Verificar si ya existe un archivo de texto
        String fileName = model.getName();
        if (fileName == null) {
            // Si no existe, generar el archivo de texto
            model.crearTxT();
        } else {
            // Si existe, imprimir el código sin crear un nuevo archivo
            model.imprimircodigo();
            view.txtcodigo.setText(model.getCodigo());
            
            // actualizar y decir al usuario que txt esta utilizando
            this.view.textoguardado.setText("CODIGO GUARDADO EN>>> " + model.getName());
            // Feedback visual
            Color originalColor = view.generar.getBackground();
            view.generar.setBackground(Color.CYAN);
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    view.generar.setBackground(originalColor);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

  
}
