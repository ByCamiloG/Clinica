package View;

import Model.Usuario;
import Model.GestorUsuarios;
import ViewModel.ClinicaViewModel;
import excepciones.CampoVacioException;
import excepciones.UsuarioNoEncontradoException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private List<Usuario> usuarios;

    public LoginFrame() {
        setTitle("Inicio de Sesión - Clínica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 230);
        setLocationRelativeTo(null);

        usuarios = GestorUsuarios.cargarUsuarios();

        // Crear admin por defecto si no hay usuarios
        if (usuarios.isEmpty()) {
            Usuario admin = new Usuario("admin", "admin123", "admin");
            GestorUsuarios.guardarUsuario(admin);
            usuarios.add(admin);
        }

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Panel de campos (formulario)
        JPanel panelCampos = new JPanel(new GridLayout(2, 1, 10, 10));

        // Línea usuario
        JPanel filaUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(120, 22));
        filaUsuario.add(lblUsuario);
        filaUsuario.add(txtUsuario);

        // Línea contraseña
        JPanel filaClave = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblClave = new JLabel("Contraseña:");
        txtClave = new JPasswordField();
        txtClave.setPreferredSize(new Dimension(120, 22));
        filaClave.add(lblClave);
        filaClave.add(txtClave);

        panelCampos.add(filaUsuario);
        panelCampos.add(filaClave);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnLogin = new JButton("Ingresar");
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnSalir = new JButton("Salir");

        Dimension btnSize = new Dimension(100, 30);
        btnLogin.setPreferredSize(btnSize);
        btnRegistrar.setPreferredSize(btnSize);
        btnSalir.setPreferredSize(btnSize);

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnSalir);

        mainPanel.add(panelCampos, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        add(mainPanel);

        // Eventos
        btnLogin.addActionListener(e -> {
            try {
                validarLogin();
            } catch (CampoVacioException | UsuarioNoEncontradoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnRegistrar.addActionListener(e -> abrirRegistro());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void validarLogin() throws CampoVacioException, UsuarioNoEncontradoException {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();

        if (user.isEmpty()) throw new CampoVacioException("Usuario");
        if (pass.isEmpty()) throw new CampoVacioException("Contraseña");

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(user) && u.getClave().equals(pass)) {
                dispose();
                new VentanaPrincipal(new ClinicaViewModel(), u.getRol());
                return;
            }
        }

        throw new UsuarioNoEncontradoException();
    }

    private void abrirRegistro() {
        JTextField nuevoUsuario = new JTextField();
        JPasswordField nuevaClave = new JPasswordField();
        JComboBox<String> roles = new JComboBox<>(new String[]{"medico", "administrativo"});

        // Reducir tamaño
        nuevoUsuario.setPreferredSize(new Dimension(120, 22));
        nuevaClave.setPreferredSize(new Dimension(120, 22));

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Nuevo usuario:"));
        panel.add(nuevoUsuario);
        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(nuevaClave);
        panel.add(new JLabel("Rol:"));
        panel.add(roles);

        int res = JOptionPane.showConfirmDialog(this, panel, "Registrar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String user = nuevoUsuario.getText().trim();
                String pass = new String(nuevaClave.getPassword()).trim();
                String rol = roles.getSelectedItem().toString();

                if (user.isEmpty()) throw new CampoVacioException("Usuario");
                if (pass.isEmpty()) throw new CampoVacioException("Contraseña");

                Usuario nuevo = new Usuario(user, pass, rol);
                GestorUsuarios.guardarUsuario(nuevo);
                usuarios.add(nuevo);

                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            } catch (CampoVacioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
