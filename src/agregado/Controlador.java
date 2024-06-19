/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado;

import agregado.modelos.Derivada;
import agregado.modelos.EDO;
import agregado.modelos.IntegralDefinida;
import agregado.modelos.IntegralIndefinida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author steve
 */
public abstract class Controlador  {
    
    public Controlador(Ventana vistaVentana){
        this.vistaVentana = vistaVentana;
        this.vistaVentana.btnGuardarDerivada.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDerivadaActionPerformed(evt);
            }
        });
        
    }
    
    /**
     *
     * @param evt
     */
    public void btnGuardarDerivadaActionPerformed(ActionEvent evt){
        
       this.derivada.setFuncion((String) vistaVentana.txtDerivada.getText());
        funcionDiff = this.derivada.generarPlantilla((String) vistaVentana.txtVariableDerivada.getText());
       JOptionPane.showMessageDialog(null, funcionDiff, "texto", 0);
        //System.out.println(derivada.generarPlantilla(vistaVentana.txtDerivada.getText()));
    }
   
    private String funcionDiff;
    private Ventana vistaVentana;
    // variables de plantilla de funciones
    Derivada derivada = new Derivada();
    private IntegralIndefinida integralIndefinida;
    private IntegralDefinida integralDefinida;
    private EDO ecuacionDiferencial;
    //fin de varialbes de plantilla
}
