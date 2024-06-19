/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado.modelos;

/**
 *
 * @author steve
 */
public class EDO extends Funcion {

    public EDO() {
        super();
    }
    //metodo para generar plantilla de una edo de primer order
    public String generarPlantilla(String var, String fun) {
        return String.format(" clear;%nclc;%n sysm %s %s(%s);%n eqn = diff(%s, %s) == %s;%n sol=(dsolve(eqn);%n"
                + "disp('La solucion de la ecuacion diferencial d%s/d%s = %s es:%n disp(sol)')",
                var, fun, var, fun, var, getFuncion(), fun, var, getFuncion());
        // var es la variable independiente, general x
        // fun es la funcion dependiete
        // clear y clc borran pantalla y variables ya establecidas
        //getFuncion corresponde a la variable del usuario
    }
    
}
