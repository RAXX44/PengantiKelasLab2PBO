import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Hasil extends JFrame {
    public Hasil(double bmi) {
        setTitle("Hasil BMI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 250, 255));

        add(buildTitle(), BorderLayout.NORTH);
        add(buildContent(bmi), BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel buildTitle() {
        JLabel title = new JLabel("Hasil Perhitungan BMI", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(new EmptyBorder(20, 10, 10, 10));
        return title;
    }

    private JPanel buildContent(double bmi) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                new LineBorder(new Color(180, 200, 255), 2, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        JLabel bmiLabel = new JLabel(String.format("BMI Anda: %.2f", bmi), SwingConstants.CENTER);
        bmiLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel kategoriLabel = new JLabel("Kategori: " + getKategori(bmi), SwingConstants.CENTER);
        kategoriLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JButton kembaliButton = new JButton("Hitung Ulang");
        styleButton(kembaliButton, new Color(255, 100, 100));

        kembaliButton.addActionListener(e -> {
            dispose();
            new Form();
        });

        panel.add(bmiLabel);
        panel.add(kategoriLabel);
        panel.add(kembaliButton);

        return panel;
    }

    private String getKategori(double bmi) {
        if (bmi < 18.5) return "Kurus (Underweight)";
        else if (bmi < 24.9) return "Normal";
        else if (bmi < 29.9) return "Gemuk (Overweight)";
        else return "Obesitas (Obese)";
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

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
