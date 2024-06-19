/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado.modelos;

/**
 *
 * @author steve
 */

//la primer derivada de una funcion
public class Derivada extends Funcion{
    //constructor
    public Derivada(){
        super();
    }
    // no tiene mas campos
    
    //metodo de generar plantilla
    @Override 
    public String generarPlantilla(String var){
        return String.format("syms f(%s);%nf(%s) = %s;%n Df = diff(f, %s)",
                var, var,  getFuncion(), var);
        
    }
}
