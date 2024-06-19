/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado.modelos;

/**
 *
 * @author steve
 */

//clase abstracta de funciones
public abstract class Funcion {
    
    public Funcion()
    {
       
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
    
    
   private String funcion;
   //metodo abstracto para generar codigo
   public String generarPlantilla(String var){
       return String.format("Syms %s;%n", var);
   }
}
