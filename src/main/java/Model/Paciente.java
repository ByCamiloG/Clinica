/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

public class Paciente extends Persona {
    private int edad;

    public Paciente(String nombre, String identificacion, int edad) {
        super(nombre, identificacion);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String getTipo() {
        return "Paciente";
    }
    
    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}