/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manajemen.buku;

/**
 *
 * @author AKBAR NUR IMAN
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class BookManagementApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, publisherField, yearField, genreField;
    private JButton addButton, updateButton, deleteButton;

    public BookManagementApp() {
        setTitle("Book Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk form input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Publisher:"));
        publisherField = new JTextField();
        inputPanel.add(publisherField);

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        inputPanel.add(genreField);

        addButton = new JButton("Add");
        inputPanel.add(addButton);

        updateButton = new JButton("Update");
        inputPanel.add(updateButton);

        deleteButton = new JButton("Delete");
        inputPanel.add(deleteButton);

        // Panel untuk tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Publisher", "Year", "Genre"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        loadData();

        // Event Listeners
        addButton.addActionListener(this::addBook);
        updateButton.addActionListener(this::updateBook);
        deleteButton.addActionListener(this::deleteBook);
    }

    private void loadData() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM books";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            tableModel.setRowCount(0);
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getString("year"),
                        resultSet.getString("genre")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addBook(ActionEvent e) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO books (title, author, publisher, year, genre) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titleField.getText());
            statement.setString(2, authorField.getText());
            statement.setString(3, publisherField.getText());
            statement.setString(4, yearField.getText());
            statement.setString(5, genreField.getText());
            statement.executeUpdate();
            loadData();
            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "UPDATE books SET title=?, author=?, publisher=?, year=?, genre=? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, titleField.getText());
                statement.setString(2, authorField.getText());
                statement.setString(3, publisherField.getText());
                statement.setString(4, yearField.getText());
                statement.setString(5, genreField.getText());
                statement.setInt(6, (int) tableModel.getValueAt(selectedRow, 0));
                statement.executeUpdate();
                loadData();
                clearFields();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteBook(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM books WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, (int) tableModel.getValueAt(selectedRow, 0));
                statement.executeUpdate();
                loadData();
                clearFields();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        yearField.setText("");
        genreField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookManagementApp app = new BookManagementApp();
            app.setVisible(true);
        });
    }
}

