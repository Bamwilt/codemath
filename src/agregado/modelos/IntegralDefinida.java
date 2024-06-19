/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado.modelos;

/**
 *
 * @author steve
 */
//integral definida con respento a una variable
public class IntegralDefinida extends IntegralIndefinida {

    public IntegralDefinida() {
        super();
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
    
    //generar plantilla
    @Override
    public String generarPlantilla(String var){
        return String.format("syms %s;%n expr = %s;%n int(expre, %s, %s, %s)",var, getFuncion(), var,
                getA(), getB());
    }
    
    //limites de integracion
    private String a; //limite inferior
    private String b; // limite superior
}
