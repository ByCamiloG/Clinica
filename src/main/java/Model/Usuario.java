package Model;

public class Usuario {
    private String usuario;
    private String clave;
    private String rol; // admin, medico, administrativo

    public Usuario(String usuario, String clave, String rol) {
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }

    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }
    public String getRol() { return rol; }

    @Override
    public String toString() {
        return usuario + ";" + clave + ";" + rol;
    }
}
