import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PemesananMobil extends JFrame {
    private JTextField namaField, merkField, noPlatField, pemilikField;
    private JTextArea deskripsiArea;
    private JRadioButton tersediaRadio, tidakTersediaRadio;
    private JComboBox<String> kategoriComboBox;
    private JSlider tahunSlider;
    private JSpinner tahunSpinner;
    private JTable mobilTable;
    private DefaultTableModel tableModel;

    public PemesananMobil() {
        setTitle("Pemesanan Mobil");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menambahkan menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu Utama");

        // Item untuk form input mobil
        JMenuItem formInputMenu = new JMenuItem("Form Input Mobil");
        formInputMenu.addActionListener(e -> showInputForm());
        menu.add(formInputMenu);

        // Item untuk keluar/log out dengan konfirmasi
        JMenuItem keluarMenu = new JMenuItem("Keluar");
        keluarMenu.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", 
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menu.add(keluarMenu);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Tabel untuk menampilkan daftar mobil
        tableModel = new DefaultTableModel(new String[]{"Nama Pemesan", "Merk Mobil", "No. Plat", "Kategori", "Status", "Tahun", "Pemilik"}, 0);
        mobilTable = new JTable(tableModel);
        add(new JScrollPane(mobilTable), BorderLayout.CENTER);

        // Panel untuk tombol hapus mobil
        JPanel buttonPanel = new JPanel();
        JButton hapusButton = new JButton("Hapus Mobil");
        hapusButton.addActionListener(e -> hapusMobil());
        buttonPanel.add(hapusButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showInputForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen input
        namaField = new JTextField();
        merkField = new JTextField();
        noPlatField = new JTextField();
        pemilikField = new JTextField(); // Field untuk pemilik
        deskripsiArea = new JTextArea(3, 20);

        tersediaRadio = new JRadioButton("Tersedia");
        tidakTersediaRadio = new JRadioButton("Tidak Tersedia");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(tersediaRadio);
        statusGroup.add(tidakTersediaRadio);

        kategoriComboBox = new JComboBox<>(new String[]{
            "SUV",
            "Sedan",
            "Hatchback",
            "Pickup",
            "Coupe",
            "Convertible",
            "Minivan",
            "Station Wagon",
            "Electric",
            "Hybrid"
        });
        
        tahunSlider = new JSlider(1900, 2024);
        tahunSpinner = new JSpinner(new SpinnerNumberModel(2024, 1900, 202, 1));

        // Menyinkronkan slider dan spinner tahun
        tahunSlider.addChangeListener(e -> tahunSpinner.setValue(tahunSlider.getValue()));
        tahunSpinner.addChangeListener(e -> tahunSlider.setValue((Integer) tahunSpinner.getValue()));

        // Menambahkan komponen ke panel 
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nama Pemesan:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Merk Mobil:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(merkField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel("No. Plat:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(noPlatField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Deskripsi:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(new JScrollPane(deskripsiArea), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JPanel statusPanel = new JPanel();
        statusPanel.add(tersediaRadio);
        statusPanel.add(tidakTersediaRadio);
        panel.add(statusPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Kategori:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(kategoriComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Pemilik:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(pemilikField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Tahun Mobil:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JPanel tahunPanel = new JPanel();
        tahunPanel.add(tahunSlider);
        tahunPanel.add(tahunSpinner);
        panel.add(tahunPanel, gbc);

        // Tombol tambah mobil
        JButton tambahButton = new JButton("Tambah Mobil");
        tambahButton.addActionListener(e -> tambahMobil());
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(tambahButton, gbc);

        // Menampilkan form input 
        JOptionPane.showMessageDialog(this, panel, "Form Input Mobil", JOptionPane.PLAIN_MESSAGE);
    }

    private void tambahMobil() {
        String nama = namaField.getText();
        String merk = merkField.getText();
        String noPlat = noPlatField.getText();
        String pemilik = pemilikField.getText(); // Mendapatkan pemilik
        String deskripsi = deskripsiArea.getText();
        String status = tersediaRadio.isSelected() ? "Tersedia" : "Tidak Tersedia";
        String kategori = kategoriComboBox.getSelectedItem().toString();
        int tahun = (int) tahunSpinner.getValue();

        // Menambahkan data mobil ke tabel
        tableModel.addRow(new Object[]{nama, merk, noPlat, kategori, status, tahun, pemilik});
    }

    private void hapusMobil() {
        int selectedRow = mobilTable.getSelectedRow();
        if (selectedRow != -1) {
            // Hapus baris yang dipilih dari tabel
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih mobil yang ingin dihapus.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PemesananMobil().setVisible(true));
    }
}
