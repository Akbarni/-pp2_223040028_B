package latihan1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BiodataApp extends JFrame {

    public BiodataApp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // Background sesuai dengan gambar

        // Panel untuk form input dengan GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen

        // Label Form Title
        JLabel title = new JLabel("Form Biodata", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(title, gbc);

        // Label dan TextField untuk Nama
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField textFieldNama = new JTextField(15);
        inputPanel.add(textFieldNama, gbc);

        // Label dan RadioButtons untuk Jenis Kelamin
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Jenis Kelamin:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.LIGHT_GRAY);
        JRadioButton radioMale = new JRadioButton("Laki-Laki");
        JRadioButton radioFemale = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioMale);
        genderGroup.add(radioFemale);
        genderPanel.add(radioMale);
        genderPanel.add(radioFemale);
        inputPanel.add(genderPanel, gbc);

        // Label dan TextField untuk Nomor HP
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(new JLabel("Nomor HP:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField textFieldTelepon = new JTextField(15);
        inputPanel.add(textFieldTelepon, gbc);

        // Checkbox untuk Warga Negara Asing
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        JCheckBox checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(checkBoxWNA, gbc);

        // Tombol Simpan
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        JButton buttonSimpan = new JButton("Simpan");
        inputPanel.add(buttonSimpan, gbc);

        // Tambahkan inputPanel ke JFrame
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Pengaturan ukuran dan tampilan JFrame
        this.add(mainPanel);
        this.setTitle("Form Biodata");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BiodataApp());
    }
}
