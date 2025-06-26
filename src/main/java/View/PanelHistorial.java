package View;

import ViewModel.ClinicaViewModel;
import Model.Consulta;
import Model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PanelHistorial extends JDialog {

    private JComboBox<Paciente> comboPacientes;
    private JTextArea txtHistorial;
    private ClinicaViewModel viewModel; // ✅ Usamos ViewModel

    public PanelHistorial(JFrame parent, ClinicaViewModel viewModel) {
        super(parent, "Historial Médico del Paciente", true);
        this.viewModel = viewModel;

        setLayout(new BorderLayout(10, 10));

        // Encabezado superior
        JPanel topPanel = new JPanel(new FlowLayout());
        comboPacientes = new JComboBox<>(viewModel.getPacientes().toArray(new Paciente[0]));
        JButton btnVer = new JButton("Ver Historial");
        topPanel.add(new JLabel("Seleccione Paciente:"));
        topPanel.add(comboPacientes);
        topPanel.add(btnVer);

        // Área de texto para historial
        txtHistorial = new JTextArea(15, 40);
        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);

        // Botón cerrar
        JPanel bottomPanel = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");
        bottomPanel.add(btnCerrar);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Eventos
        btnVer.addActionListener(e -> mostrarHistorial());
        btnCerrar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void mostrarHistorial() {
        Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "No hay pacientes registrados.");
            return;
        }

        List<Consulta> consultas = viewModel.getConsultas(); // ✅ Usamos el ViewModel
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder historial = new StringBuilder();

        for (Consulta c : consultas) {
            if (c.getPaciente().getIdentificacion().equals(paciente.getIdentificacion())) {
                historial.append("Fecha: ").append(c.getFechaHora().format(formatter)).append("\n");
                historial.append("Médico: ").append(c.getMedico().getNombre()).append(" - ").append(c.getMedico().getEspecialidad()).append("\n");
                historial.append("Síntomas: ").append(c.getSintomas()).append("\n");
                historial.append("Diagnóstico: ").append(c.getDiagnostico()).append("\n");
                historial.append("Tratamiento: ").append(c.getTratamiento()).append("\n");
                historial.append("--------------------------------------------\n");
            }
        }

        if (historial.length() == 0) {
            historial.append("No hay consultas registradas para este paciente.");
        }

        txtHistorial.setText(historial.toString());
    }
}
