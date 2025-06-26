package ViewModel;

import Model.*;
import Model.IPersistencia;
import Persistencia.PersistenciaArchivo;

import java.util.List;

public class ClinicaViewModel {

    private Clinica clinica;
    private IPersistencia persistencia;

    public ClinicaViewModel() {
        persistencia = new PersistenciaArchivo();

        try {
            this.clinica = persistencia.cargar("datos.ser");
        } catch (Exception e) {
            this.clinica = new Clinica();
        }
    }

    public void guardar() throws Exception {
        persistencia.guardar(clinica, "datos.ser");
    }

    public void registrarPaciente(String nombre, String id, int edad) {
        Paciente p = new Paciente(nombre, id, edad);
        clinica.agregarPaciente(p);
    }

    public void registrarMedico(String nombre, String id, String especialidad) {
        Medico m = new Medico(nombre, id, especialidad);
        clinica.agregarMedico(m);
    }

    public void asignarConsulta(Paciente paciente, Medico medico, String sintomas, String diagnostico, String tratamiento) {
        Consulta c = new Consulta(paciente, medico, java.time.LocalDateTime.now(), sintomas, diagnostico, tratamiento);
        clinica.agregarConsulta(c);
    }

    public List<Paciente> getPacientes() {
        return clinica.getPacientes();
    }

    public List<Medico> getMedicos() {
        return clinica.getMedicos();
    }

    public List<Consulta> getConsultas() {
        return clinica.getConsultas();
    }
}
