package hotel.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ayarlar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtsifre = new JTextField();
    private JTextField txtdegistir = new JTextField();;
    private JComboBox<String> sifreComboBox; 
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private Connection con = null;
    private Statement st = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ayarlar frame = new ayarlar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ayarlar() {
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 324, 290);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Eski Şifre ");
        lblNewLabel.setBounds(10, 85, 68, 19);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Kullanıcılar");
        lblNewLabel_1.setBounds(10, 47, 90, 14);
        contentPane.add(lblNewLabel_1);

        sifreComboBox = new JComboBox<>();
        sifreComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textdoldur(sifreComboBox.getSelectedItem().toString());
                }
            }
        });
        sifreComboBox.setBounds(100, 43, 113, 22);
        contentPane.add(sifreComboBox);
        getcomboveeri(sifreComboBox);

        JButton btnNewButton_1 = new JButton("Kaydet");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sifreDegistir();
                Anasayfa ana=new Anasayfa();
                ana.setVisible(true);
                dispose();            }
        });
        btnNewButton_1.setBounds(100, 187, 89, 35);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("Ayarlar");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setBounds(10, 11, 79, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblYeniifre = new JLabel("Yeni Şifre ");
        lblYeniifre.setBounds(10, 122, 68, 19);
        contentPane.add(lblYeniifre);

        txtsifre.setBounds(100, 84, 89, 20);
        contentPane.add(txtsifre);
        txtsifre.setColumns(10);

        txtdegistir.setColumns(10);
        txtdegistir.setBounds(100, 121, 89, 20);
        contentPane.add(txtdegistir);
    }

    public void getcomboveeri(JComboBox<String> comboBox) {
        try {
            String sorgu = "SELECT name FROM public.admingiris;";
            ResultSet rs = st.executeQuery(sorgu);
            comboBox.removeAllItems();
            while (rs.next()) {
                String ad = rs.getString("name");
                comboBox.addItem(ad);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void textdoldur(String selectedPerson) {
        try {
            String sorgu = "SELECT password FROM public.admingiris WHERE name = ?;";
            PreparedStatement pst = con.prepareStatement(sorgu);
            pst.setString(1, selectedPerson);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtsifre.setText(rs.getString("password"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void sifreDegistir() {
        try {
            String selectedPerson = sifreComboBox.getSelectedItem().toString();
            String yeniSifre = txtdegistir.getText();
            String sorgu = "UPDATE public.admingiris SET password = ? WHERE name = ?;";
            PreparedStatement pst = con.prepareStatement(sorgu);
            pst.setString(1, yeniSifre);
            pst.setString(2, selectedPerson);
            int satır = pst.executeUpdate();
            if (satır > 0) {
                JOptionPane.showMessageDialog(null, "Şifre başarıyla güncellendi.");
                txtdegistir.setText("");  
                txtsifre.setText(yeniSifre); 
            } else {
                JOptionPane.showMessageDialog(null, "Şifre güncellenirken bir hata oluştu.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
