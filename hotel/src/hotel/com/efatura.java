package hotel.com;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class efatura extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txthizmet;
    private JTextField txtfiyat;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private DefaultTableModel hoteltb;
    private Connection con = null;
    private Statement st = null;
    private JTable table22;
    private DefaultTableModel hoteltb2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    efatura frame = new efatura();
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
    public efatura() {
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 689, 363);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton_2_1 = new JButton("Odalar");
        btnNewButton_2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                odalar oda = new odalar();
                oda.setVisible(true);
                dispose();
            }
        });
        btnNewButton_2_1.setBounds(528, 142, 143, 35);
        contentPane.add(btnNewButton_2_1);

        JButton btnNewButton_2 = new JButton("Rezervasyonlarım");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rezervasyonlarim rez = new rezervasyonlarim();
                rez.setVisible(true);
                dispose();
            }
        });
        btnNewButton_2.setBounds(528, 78, 143, 37);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_2_1_2 = new JButton("Personeller");
        btnNewButton_2_1_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                personeller personel = new personeller();
                personel.setVisible(true);
                dispose();
            }
        });
        btnNewButton_2_1_2.setBounds(528, 207, 143, 37);
        contentPane.add(btnNewButton_2_1_2);

        JButton btnNewButton_2_1_3_1 = new JButton("E-Fatura");
        btnNewButton_2_1_3_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                efatura fatura = new efatura();
                fatura.setVisible(true);
                dispose();
            }
        });
        btnNewButton_2_1_3_1.setBounds(528, 266, 143, 35);
        contentPane.add(btnNewButton_2_1_3_1);

        JButton btnNewButton_4 = new JButton("");
        btnNewButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Anasayfa ana = new Anasayfa();
                ana.setVisible(true);
                dispose();
            }
        });
        btnNewButton_4.setIcon(new ImageIcon(efatura.class.getResource("/resim/ana.JPG")));
        btnNewButton_4.setBounds(512, 9, 41, 51);
        contentPane.add(btnNewButton_4);

        JButton btnNewButton_3 = new JButton("");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                girisekrani geriGiris = new girisekrani();
                geriGiris.setVisible(true);
                dispose();
            }
        });
        btnNewButton_3.setIcon(new ImageIcon(efatura.class.getResource("/resim/23.JPG")));
        btnNewButton_3.setBounds(628, 9, 35, 35);
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ayarlar ayar = new ayarlar();
                ayar.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(efatura.class.getResource("/resim/AYAR.JPG")));
        btnNewButton_1.setBounds(563, 9, 65, 64);
        contentPane.add(btnNewButton_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 74, 208, 64);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1 = new JLabel("Hizmet");
        lblNewLabel_1.setBounds(24, 46, 65, 14);
        contentPane.add(lblNewLabel_1);

        txthizmet = new JTextField();
        txthizmet.setColumns(10);
        txthizmet.setBounds(85, 43, 86, 20);
        contentPane.add(txthizmet);

        JLabel lblNewLabel_1_1 = new JLabel("Fiyat");
        lblNewLabel_1_1.setBounds(204, 46, 65, 14);
        contentPane.add(lblNewLabel_1_1);

        txtfiyat = new JTextField();
        txtfiyat.setColumns(10);
        txtfiyat.setBounds(265, 43, 86, 20);
        contentPane.add(txtfiyat);

        JButton btnNewButton = new JButton("Ekle");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String hizmet = txthizmet.getText().trim();
                int fiyat = Integer.parseInt(txtfiyat.getText().trim());
                int adet = hizmetekle(hizmet, fiyat);
                if (adet > 0) {
                    JOptionPane.showMessageDialog(null, "Başarıyla eklendi!");
                    setrezervelerToTableModel();
                    txtfiyat.setText("");
                    txthizmet.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Eklenirken bir hata oluştu.");
                }
            }
        });
        btnNewButton.setBounds(366, 42, 89, 23);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Ek Hizmetler");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(23, 9, 116, 26);
        contentPane.add(lblNewLabel);

        JLabel lblFaturalar = new JLabel("Faturalar");
        lblFaturalar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFaturalar.setBounds(23, 152, 116, 26);
        contentPane.add(lblFaturalar);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(24, 189, 494, 112);
        contentPane.add(scrollPane_1);

        table22 = new JTable();
        scrollPane_1.setViewportView(table22);

        setrezervelerToTableModel();
        setrezervelerToTableModel2();
    }

    public ArrayList<ekhizmet> gethizmetler() {
        ArrayList<ekhizmet> hizmetler = new ArrayList<ekhizmet>();
        String sql = "SELECT id, hizmetadi, fiyat FROM public.ekhizmetler;";

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ekhizmet hiz = new ekhizmet();
                hiz.setId(rs.getInt(1));
                hiz.setHizmet(rs.getString(2));
                hiz.setFiyat(rs.getInt(3));

                hizmetler.add(hiz);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return hizmetler;
    }

    public void setrezervelerToTableModel() {
        ArrayList<ekhizmet> hizmetlee = gethizmetler();
        table.removeAll();
        hoteltb = new DefaultTableModel();
        hoteltb.addColumn("Hizmet");
        hoteltb.addColumn("Fiyat");

        for (ekhizmet hiz : hizmetlee) {
            Object[] row = { hiz.getHizmet(), hiz.getFiyat() };
            hoteltb.addRow(row);
        }
        table.setModel(hoteltb);
    }

    public int hizmetekle(String hizmet, int fiyat) {
        String sql = "INSERT INTO public.ekhizmetler (hizmetadi, fiyat) VALUES ('" + hizmet + "', " + fiyat + ")";
        int i = 0;
        try {
            i = st.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return i;
    }

    public ArrayList<faturarezerve> getfaturalar() {
        ArrayList<faturarezerve> faturalar = new ArrayList<faturarezerve>();
        String sql = "SELECT rezervasyonlarid, odaid, ad, soyad, giristarihi, cikistarihi, telno, kullanici_id, odeme_durum, toplam_borc, kartno FROM public.rezervasyonlar;";

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                faturarezerve fa = new faturarezerve();
                fa.setRezervasyonlarid(rs.getInt("rezervasyonlarid"));
                fa.setOdaid(rs.getInt("odaid"));
                fa.setAd(rs.getString("ad"));
                fa.setSoyad(rs.getString("soyad"));
                fa.setGiristarihi(rs.getString("giristarihi"));
                fa.setCikistarihi(rs.getString("cikistarihi"));
                fa.setTelno(rs.getString("telno"));
                fa.setKullanici_id(rs.getInt("kullanici_id"));
                fa.setOdeme_durum(rs.getString("odeme_durum"));
                fa.setToplam_borc(rs.getString("toplam_borc"));
                fa.setKartno(rs.getString("kartno"));

                faturalar.add(fa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return faturalar;
    }

    public void setrezervelerToTableModel2() {
        ArrayList<faturarezerve> fatu = getfaturalar();
        hoteltb2 = new DefaultTableModel();
        hoteltb2.addColumn("Rezervasyon ID");
        hoteltb2.addColumn("Oda ID");
        hoteltb2.addColumn("Ad");
        hoteltb2.addColumn("Soyad");
        hoteltb2.addColumn("Giriş Tarihi");
        hoteltb2.addColumn("Çıkış Tarihi");
        hoteltb2.addColumn("Telefon No");
        hoteltb2.addColumn("Kullanıcı ID");
        hoteltb2.addColumn("Ödeme Durumu");
        hoteltb2.addColumn("Toplam Borç");
        hoteltb2.addColumn("Kart No");

        for (faturarezerve f : fatu) {
            Object[] row = { f.getRezervasyonlarid(), f.getOdaid(), f.getAd(), f.getSoyad(), f.getGiristarihi(), f.getCikistarihi(), f.getTelno(), f.getKullanici_id(), f.getOdeme_durum(), f.getToplam_borc(), f.getKartno() };
            hoteltb2.addRow(row);
        }
        table22.setModel(hoteltb2);
    }
}
