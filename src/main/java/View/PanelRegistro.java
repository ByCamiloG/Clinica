package View;

import Model.Medico;
import Model.Paciente;
import ViewModel.ClinicaViewModel;

import javax.swing.*;
import java.awt.*;

public class PanelRegistro extends JDialog {

    private JTextField txtNombre, txtId, txtCampoExtra;
    private JComboBox<String> comboTipo;
    private JLabel lblCampoExtra;
    private ClinicaViewModel viewModel; // ✅ Usamos ViewModel

    public PanelRegistro(JFrame parent, ClinicaViewModel viewModel) {
        super(parent, "Registro de Paciente / Médico", true);
        this.viewModel = viewModel;

        setLayout(new GridLayout(5, 2, 10, 10));

        comboTipo = new JComboBox<>(new String[]{"Paciente", "Médico"});
        txtNombre = new JTextField();
        txtId = new JTextField();
        txtCampoExtra = new JTextField();
        lblCampoExtra = new JLabel("Edad");

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        add(new JLabel("Tipo:"));
        add(comboTipo);
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Identificación:"));
        add(txtId);
        add(lblCampoExtra);
        add(txtCampoExtra);
        add(btnRegistrar);
        add(btnCancelar);

        // Cambiar el campo extra según tipo
        comboTipo.addActionListener(e -> {
            if (comboTipo.getSelectedItem().equals("Paciente")) {
                lblCampoExtra.setText("Edad");
            } else {
                lblCampoExtra.setText("Especialidad");
            }
        });

        btnRegistrar.addActionListener(e -> registrarPersona());
        btnCancelar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void registrarPersona() {
        String tipo = (String) comboTipo.getSelectedItem();
        String nombre = txtNombre.getText().trim();
        String id = txtId.getText().trim();
        String extra = txtCampoExtra.getText().trim();

        if (nombre.isEmpty() || id.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        if (tipo.equals("Paciente")) {
            try {
                int edad = Integer.parseInt(extra);
                viewModel.registrarPaciente(nombre, id, edad); // ✅ usando ViewModel
                JOptionPane.showMessageDialog(this, "Paciente registrado.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Edad debe ser un número.");
                return;
            }
        } else {
            viewModel.registrarMedico(nombre, id, extra); // ✅ usando ViewModel
            JOptionPane.showMessageDialog(this, "Médico registrado.");
        }

        dispose(); // cerrar el formulario
    }
}
