/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Clinica implements Serializable {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;

    public Clinica() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        consultas = new ArrayList<>();
    }

    // Métodos para agregar
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    // Getters
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    // Método para guardar en .ser y .txt
    public void guardarDatos(String archivo) throws IOException {
        // Guardar binario
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(this);
        }

        // Guardar como texto legible
        try (PrintWriter writer = new PrintWriter(new FileWriter("datos.txt"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Consulta c : consultas) {
                writer.println("Fecha: " + c.getFechaHora().format(formatter));
                writer.println("Paciente: " + c.getPaciente().getNombre() + " (" + c.getPaciente().getIdentificacion() + ")");
                writer.println("Médico: " + c.getMedico().getNombre() + " - " + c.getMedico().getEspecialidad());
                writer.println("Síntomas: " + c.getSintomas());
                writer.println("Diagnóstico: " + c.getDiagnostico());
                writer.println("Tratamiento: " + c.getTratamiento());
                writer.println("----------------------------------------");
            }
        }
    }

    // Método para cargar desde archivo binario
    public static Clinica cargarDatos(String archivo) throws IOException, ClassNotFoundException {
        File f = new File(archivo);
        if (!f.exists()) {
            return new Clinica(); // Archivo no existe, crear clínica vacía
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Clinica) in.readObject();
        }
    }
}