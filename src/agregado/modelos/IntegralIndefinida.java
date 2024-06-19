/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agregado.modelos;

/**
 *
 * @author steve
 */
//integral indefinida
public class IntegralIndefinida extends Funcion {

    public IntegralIndefinida() {
        super();
    }
    
    //metodo de generar plantilla
    @Override
    public String generarPlantilla(String var){
        return String.format("%s expr = %s;%n int(expr, %s)",super.generarPlantilla(var), getFuncion(),
                super.generarPlantilla(var));
    }
    
}
