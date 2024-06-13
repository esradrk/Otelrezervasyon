package hotel.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class uyeol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtad;
	private JTextField txtkad;
	private JTextField txtemail;
	private JTextField txtsifre;
	private JTextField txtdtarihi;
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
					uyeol frame = new uyeol();
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
	public uyeol() {
		   try {
		         con = DriverManager.getConnection(url, user, password);
		            st = con.createStatement();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adı Soyadı");
		lblNewLabel.setBounds(21, 73, 83, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı");
		lblKullancAd.setBounds(21, 108, 83, 25);
		contentPane.add(lblKullancAd);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(21, 144, 83, 25);
		contentPane.add(lblEmail);
		
		JLabel lblifre = new JLabel("Şifre");
		lblifre.setBounds(21, 185, 83, 25);
		contentPane.add(lblifre);
		
		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi");
		lblDoumTarihi.setBounds(21, 221, 83, 25);
		contentPane.add(lblDoumTarihi);
		
		JButton btnNewButton = new JButton("Üye Ol");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ad=txtad.getText().trim();
				String kullanici=txtkad.getText().trim();
				String email=txtemail.getText().trim();
				String sifre=txtsifre.getText().trim();
				String dogumtarihi=txtdtarihi.getText().trim();
	
				  if (ad.isEmpty() || kullanici.isEmpty() || email.isEmpty() || sifre.isEmpty()||dogumtarihi.isEmpty()) {
			            JOptionPane.showMessageDialog(contentPane, "Lütfen tüm alanları doldurunuz.", "Eksik Bilgi", 
			            		JOptionPane.WARNING_MESSAGE);}
				  else {
			        int eklemeSonucu = uyeoll( ad, kullanici, email, sifre, dogumtarihi);
			        int girissonuc=girisyap(kullanici,sifre);
			        if (eklemeSonucu > 0 & girissonuc>0) {
			            JOptionPane.showMessageDialog(null, "Üyelik işlemi başarıyla tamamlandı!");
			        } 
			      girisekrani giris=new girisekrani();
			      giris.setVisible(true);
			      dispose();
				  }
			}
		});
		btnNewButton.setBounds(73, 269, 103, 44);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ÜYE OL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 76, 25);
		contentPane.add(lblNewLabel_1);
		
		txtad = new JTextField();
		txtad.setBounds(110, 75, 86, 20);
		contentPane.add(txtad);
		txtad.setColumns(10);
		
		txtkad = new JTextField();
		txtkad.setColumns(10);
		txtkad.setBounds(110, 110, 86, 20);
		contentPane.add(txtkad);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(110, 146, 137, 20);
		contentPane.add(txtemail);
		
		txtsifre = new JTextField();
		txtsifre.setColumns(10);
		txtsifre.setBounds(110, 187, 86, 20);
		contentPane.add(txtsifre);
		
		txtdtarihi = new JTextField();
		txtdtarihi.setColumns(10);
		txtdtarihi.setBounds(110, 223, 86, 20);
		contentPane.add(txtdtarihi);
		
		
		
	}
	
public ArrayList<uye> getuyeler()
{
ArrayList<uye> uyeler=new ArrayList<uye>();
String sql="SELECT uye_id, adsoyad, kullanici_ad, email, sifre, dogum_tarihi\r\n"
		+ "	FROM public.uyeler;";
try {
	ResultSet rs=st.executeQuery(sql);
	while(rs.next()) {
		uye uyee=new uye();
		uyee.setId(rs.getInt(1));;
		uyee.setAdsoyad(rs.getString(2));
		uyee.setKullaniciadi(rs.getString(2));
		uyee.setEmail(rs.getString(3));
		uyee.setSifre(rs.getString(4));
		uyee.setDogumtarihi(rs.getString(5));
		uyeler.add(uyee);
	}

}
catch (SQLException e) {
	JOptionPane.showMessageDialog(null, e);
}
return uyeler;
}	



public int uyeoll(String adsoyad, String kullaniciad, String email, String sifre, String dogumtarihi) {
	 boolean kontrol = kontrolEt(kullaniciad, email);
	    if (kontrol) {
	        JOptionPane.showMessageDialog(null, "Bu kullanıcı adı veya e-posta zaten kullanılıyor. "
	        		+ "Lütfen farklı bir kullanıcı adı veya e-posta girin.");
	        return 0; 
	    } else {
	        String sql = "INSERT INTO public.uyeler(adsoyad, kullanici_ad, email, sifre, dogum_tarihi) VALUES"
	        		+ " ('" + adsoyad + "', '" + kullaniciad + "', '" + email + "', '" + sifre + "', '" + dogumtarihi + "')";
	        int i = 0;
	        try {
	            i = st.executeUpdate(sql);
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	        return i;
	    }
}
public int girisyap(String kad,String sifre)
{
String sql="INSERT INTO public.kullanicigiris( name, password)VALUES ( '"+kad+"', '"+sifre+"');";	
int i = 0;
try {
    i = st.executeUpdate(sql);
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e);
}
return i;
}

public boolean kontrolEt(String kullaniciad, String email) {
    boolean result = false;
    String sql = "SELECT COUNT(*) FROM public.uyeler WHERE kullanici_ad = '" + kullaniciad + "' OR email = '" + email + "'";
    try {
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            if (count > 0) {
                result = true;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    return result;
}

}
