package Model;

import java.io.*;
import java.util.*;

public class GestorUsuarios {
    private static final String ARCHIVO = "usuarios.txt";

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        File file = new File(ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    lista.add(new Usuario(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void guardarUsuario(Usuario usuario) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            writer.println(usuario.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
