package View;

import ViewModel.ClinicaViewModel;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private ClinicaViewModel viewModel;
    private String rol;

    public VentanaPrincipal(ClinicaViewModel viewModel, String rol) {
        this.viewModel = viewModel;
        this.rol = rol;

        setTitle("Clínica - Gestión de Consultas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registrar Paciente / Médico");
        JButton btnConsulta = new JButton("Asignar Consulta");
        JButton btnHistorial = new JButton("Ver Historial Médico");

        // Mostrar botones según el rol
        if (rol.equals("admin") || rol.equals("administrativo")) {
            panelBotones.add(btnRegistrar);
        }
        if (rol.equals("admin")) {
            panelBotones.add(btnConsulta);
        }
        panelBotones.add(btnHistorial);

        add(panelBotones, BorderLayout.CENTER);

        // Eventos
        btnRegistrar.addActionListener(e -> new PanelRegistro(this, viewModel));
        btnConsulta.addActionListener(e -> new PanelConsulta(this, viewModel));
        btnHistorial.addActionListener(e -> new PanelHistorial(this, viewModel));

        setVisible(true);
    }
}
