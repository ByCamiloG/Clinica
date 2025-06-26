

package excepciones;


public class UsuarioNoEncontradoException extends Exception {
    public UsuarioNoEncontradoException() {
        super("Usuario o contraseña incorrectos.");
    }
}
