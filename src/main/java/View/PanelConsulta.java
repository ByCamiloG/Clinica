package View;

import ViewModel.ClinicaViewModel;
import Model.Consulta;
import Model.Medico;
import Model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class PanelConsulta extends JDialog {

    private JComboBox<Paciente> comboPacientes;
    private JComboBox<Medico> comboMedicos;
    private JTextArea txtSintomas, txtDiagnostico, txtTratamiento;
    private ClinicaViewModel viewModel; // ✅ ViewModel, no Clinica

    public PanelConsulta(JFrame parent, ClinicaViewModel viewModel) {
        super(parent, "Asignar Consulta Médica", true);
        this.viewModel = viewModel; // ✅ ahora sí declarado

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel buttonPanel = new JPanel();

        comboPacientes = new JComboBox<>(viewModel.getPacientes().toArray(new Paciente[0]));
        comboMedicos = new JComboBox<>(viewModel.getMedicos().toArray(new Medico[0]));
        txtSintomas = new JTextArea(2, 20);
        txtDiagnostico = new JTextArea(2, 20);
        txtTratamiento = new JTextArea(2, 20);

        formPanel.add(new JLabel("Paciente:"));
        formPanel.add(comboPacientes);
        formPanel.add(new JLabel("Médico:"));
        formPanel.add(comboMedicos);
        formPanel.add(new JLabel("Síntomas:"));
        formPanel.add(new JScrollPane(txtSintomas));
        formPanel.add(new JLabel("Diagnóstico:"));
        formPanel.add(new JScrollPane(txtDiagnostico));
        formPanel.add(new JLabel("Tratamiento:"));
        formPanel.add(new JScrollPane(txtTratamiento));

        JButton btnRegistrar = new JButton("Registrar Consulta");
        JButton btnCancelar = new JButton("Cancelar");

        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnCancelar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrarConsulta());
        btnCancelar.addActionListener(e -> dispose());

        setSize(450, 400);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void registrarConsulta() {
        Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
        Medico medico = (Medico) comboMedicos.getSelectedItem();
        String sintomas = txtSintomas.getText().trim();
        String diagnostico = txtDiagnostico.getText().trim();
        String tratamiento = txtTratamiento.getText().trim();

        if (paciente == null || medico == null || sintomas.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        // ✅ Usamos ViewModel para registrar la consulta
        viewModel.asignarConsulta(paciente, medico, sintomas, diagnostico, tratamiento);
        JOptionPane.showMessageDialog(this, "Consulta registrada correctamente.");
        dispose();
    }
}
