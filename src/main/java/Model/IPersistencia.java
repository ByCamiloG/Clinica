package Model;

import Model.Clinica;

public interface IPersistencia {
    void guardar(Clinica clinica, String archivo) throws Exception;
    Clinica cargar(String archivo) throws Exception;
}
