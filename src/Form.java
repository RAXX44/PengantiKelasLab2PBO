import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Form extends JFrame {
    private JTextField beratField, tinggiField;

    public Form() {
        setTitle("Kalkulator BMI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 340);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildHeader(), BorderLayout.NORTH);
        add(buildForm(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel buildHeader() {
        JLabel title = new JLabel("Kalkulator BMI", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));
        return title;
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 30, 30));

        beratField = new JTextField();
        JPanel beratPanel = new JPanel(new BorderLayout());
        beratPanel.setBorder(BorderFactory.createTitledBorder("Berat badan (kg)"));
        beratPanel.add(beratField, BorderLayout.CENTER);

        tinggiField = new JTextField();
        JPanel tinggiPanel = new JPanel(new BorderLayout());
        tinggiPanel.setBorder(BorderFactory.createTitledBorder("Tinggi badan (cm)"));
        tinggiPanel.add(tinggiField, BorderLayout.CENTER);

        JButton hitungButton = new JButton("Hitung BMI");
        styleButton(hitungButton, new Color(85, 140, 255));
        hitungButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hitungButton.addActionListener(e -> hitungBMI());

        panel.add(beratPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(tinggiPanel);
        panel.add(Box.createVerticalStrut(25));
        panel.add(hitungButton);

        return panel;
    }

    private void hitungBMI() {
        try {
            double berat = Double.parseDouble(beratField.getText());
            double tinggi = Double.parseDouble(tinggiField.getText());

            if (berat <= 0 || tinggi <= 0) {
                JOptionPane.showMessageDialog(this, "Berat dan tinggi harus lebih dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double tinggiM = tinggi / 100.0;
            double bmi = berat / (tinggiM * tinggiM);

            dispose();
            new Hasil(bmi);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
    }
}
