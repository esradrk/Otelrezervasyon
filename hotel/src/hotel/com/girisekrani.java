package hotel.com;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class girisekrani extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtKullancAdnGiriniz;
    private JTextField textField;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private Connection con = null;
    private Statement st = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    girisekrani frame = new girisekrani();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public girisekrani() {
        try {
         con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 253, 351);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Giriş Yap");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(46, 33, 135, 36);
        contentPane.add(lblNewLabel);

        txtKullancAdnGiriniz = new JTextField();
        txtKullancAdnGiriniz.setToolTipText("");
        txtKullancAdnGiriniz.setBackground(new Color(225, 225, 225));
        txtKullancAdnGiriniz.setBounds(86, 80, 135, 25);
        contentPane.add(txtKullancAdnGiriniz);
        txtKullancAdnGiriniz.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Kullanıcı Ad");
        lblNewLabel_1.setBounds(10, 91, 66, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Şifre");
        lblNewLabel_2.setBounds(10, 146, 46, 14);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setToolTipText("");
        textField.setColumns(10);
        textField.setBackground(new Color(225, 225, 225));
        textField.setBounds(86, 141, 135, 25);
        contentPane.add(textField);

        JButton btnNewButton = new JButton("Giriş Yap");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String sql = "select id, name, password from kullanicigiris where name='"
                        + txtKullancAdnGiriniz.getText() + "' and password='" + new String(textField.getText()) + "';";
                String adminQuery = "select * from public.admingiris where name='"
                        + txtKullancAdnGiriniz.getText() + "' and password='" + new String(textField.getText()) + "';";
                try {
                    ResultSet rs = st.executeQuery(sql);
                    boolean loginFlag = false;
                    while (rs.next()) {
                        loginFlag = true;
                    }
                    if (loginFlag) {
                        musterianasayfa mw = new musterianasayfa();
                        mw.setVisible(true);

                    } else {
                    	  ResultSet adminRs = st.executeQuery(adminQuery);
                          if (adminRs.next()) {
                              dispose(); 
                              Anasayfa kullaniciAnasayfa = new Anasayfa();
                              kullaniciAnasayfa.setVisible(true); 
                          } else {
                              // Kullanıcı adı veya şifre hatalı
                              JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı!");
                          }
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        btnNewButton.setBackground(new Color(255, 128, 0));
        btnNewButton.setForeground(new Color(64, 0, 64));
        btnNewButton.setBounds(23, 202, 89, 30);
        contentPane.add(btnNewButton);

        JButton btnyeOl = new JButton("Üye Ol");
        btnyeOl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uyeol uye = new uyeol();
                uye.setVisible(true);
                dispose();
            }
        });
        btnyeOl.setForeground(new Color(64, 0, 64));
        btnyeOl.setBackground(new Color(0, 128, 255));
        btnyeOl.setBounds(132, 202, 89, 30);
        contentPane.add(btnyeOl);
    }
}
