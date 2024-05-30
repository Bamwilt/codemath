/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import controlador.controlador;
import modelo.modelo;
import vista.vista;

/**
 *
 * @author Bryan Maradiaga
 */
public class MVC {
        public static void main (String[]args){
            
            modelo mod = new modelo();
        vista view = new vista();

       controlador ctrl = new controlador(view, mod) {};
        ctrl.iniciar();
        view.setVisible(true);
}
}
