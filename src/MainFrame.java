/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AKBAR NUR IMAN
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton startButton;
    private JProgressBar progressBar;

    public MainFrame() {
        setTitle("Konkurensi Praktikum");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startButton = new JButton("Mulai");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        startButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                // Menggunakan SwingWorker untuk menjaga responsivitas aplikasi
                startLongTask();
            }
        });

        add(startButton, "North");
        add(progressBar, "South");
    }

    private void startLongTask() {
        // Menggunakan SwingWorker untuk menjalankan tugas berat
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
           
            protected Void doInBackground() throws Exception {
                // Simulasi pemrosesan 60 data
                for (int i = 0; i < 60; i++) {
                    Thread.sleep(1000); // Simulasi pemrosesan data selama 1 detik
                    publish((i + 1) * 100 / 60); // Update progress
                }
                return null;
            }

          
            protected void process(java.util.List<Integer> chunks) {
                for (Integer value : chunks) {
                    progressBar.setValue(value); // Update progress bar
                }
            }

           
            protected void done() {
                progressBar.setValue(100); // Set progress bar ke 100% saat selesai
            }
        };
        worker.execute(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}