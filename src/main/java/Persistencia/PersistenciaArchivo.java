package Persistencia;

import Model.Clinica;
import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.IPersistencia;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class PersistenciaArchivo implements IPersistencia {

    @Override
    public void guardar(Clinica clinica, String archivo) throws Exception {
        // Guardar en .ser
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(clinica);
        }

        // Guardar historial de consultas en datos.txt
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("datos.txt"), "UTF-8"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Consulta c : clinica.getConsultas()) {
                writer.println("Fecha: " + c.getFechaHora().format(formatter));
                writer.println("Paciente: " + c.getPaciente().getNombre() + " (" + c.getPaciente().getIdentificacion() + ")");
                writer.println("Médico: " + c.getMedico().getNombre() + " - " + c.getMedico().getEspecialidad());
                writer.println("Síntomas: " + c.getSintomas());
                writer.println("Diagnóstico: " + c.getDiagnostico());
                writer.println("Tratamiento: " + c.getTratamiento());
                writer.println("----------------------------------------");
            }
        }

        // Guardar pacientes en pacientes.txt
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("pacientes.txt"), "UTF-8"))) {
            for (Paciente p : clinica.getPacientes()) {
                writer.println("Nombre: " + p.getNombre() + " - ID: " + p.getIdentificacion() + " - Edad: " + p.getEdad());
            }
        }

        // Guardar médicos en medicos.txt
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("medicos.txt"), "UTF-8"))) {
            for (Medico m : clinica.getMedicos()) {
                writer.println("Nombre: " + m.getNombre() + " - ID: " + m.getIdentificacion() + " - Especialidad: " + m.getEspecialidad());
            }
        }
    }

    @Override
    public Clinica cargar(String archivo) throws Exception {
        File f = new File(archivo);
        if (!f.exists()) return new Clinica();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Clinica) ois.readObject();
        }
    }
}
