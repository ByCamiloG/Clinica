/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime fechaHora,
                    String sintomas, String diagnostico, String tratamiento) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    // Getters
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    // Setters (opcional si deseas modificar luego)
    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "paciente=" + paciente.getNombre() +
                ", medico=" + medico.getNombre() +
                ", fechaHora=" + fechaHora +
                ", sintomas='" + sintomas + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                '}';
    }
}