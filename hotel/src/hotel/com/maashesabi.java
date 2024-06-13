package hotel.com;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class maashesabi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtodeme= new JTextField();
    private JTextField txtmaas=new JTextField();
    private JTextField txtiban= new JTextField();
    private JTextField txtdurum= new JTextField();
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private Connection con = null;
    private Statement st = null;
    private JComboBox<String> personelComboBox; 
    private int personelid=0;
    private 	JButton btnNewButton_1 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maashesabi frame = new maashesabi();
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
	 public maashesabi() {
	        try {
	            con = DriverManager.getConnection(url, user, password);
	            st = con.createStatement();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 687, 182);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
		
	        JLabel lblNewLabel = new JLabel("Kime");
	        lblNewLabel.setBounds(10, 55, 46, 14);
	        contentPane.add(lblNewLabel);
		
		personelComboBox = new JComboBox();
		personelComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textdoldur(personelComboBox.getSelectedItem().toString());
				}
							}
		});
		personelComboBox.setBounds(10, 75, 110, 22);
		contentPane.add(personelComboBox);
		getcomboveeri(personelComboBox);
		txtodeme.setBounds(130, 76, 100, 20);
		contentPane.add(txtodeme);
		txtodeme.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ödeme Tarihi");
		lblNewLabel_1.setBounds(130, 55, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Maaş Ödemesi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 11, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Maaş");
		lblNewLabel_3.setBounds(240, 55, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		
		txtmaas.setBounds(240, 76, 99, 20);
		contentPane.add(txtmaas);
		txtmaas.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("İban");
		lblNewLabel_4.setBounds(349, 55, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtiban.setBounds(349, 76, 106, 20);
		contentPane.add(txtiban);
		txtiban.setColumns(10);
		
		JButton btnNewButton = new JButton("Ödeme Yap");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String selectedPerson = (String) personelComboBox.getSelectedItem();
				    if (selectedPerson != null && !selectedPerson.isEmpty()) {
				      int adet = odeme(selectedPerson);
				      if (adet > 0) {
				        JOptionPane.showMessageDialog(null, adet + " Maaş Ödeme Başarılı");
						btnNewButton_1.setEnabled(true);

				      } else {
				        JOptionPane.showMessageDialog(null, "Maaş Ödeme Başarısız", "Hata", JOptionPane.ERROR_MESSAGE);
				      }
				    } else {
				      JOptionPane.showMessageDialog(null, "Lütfen bir personel seçin!");
				    }
			   
			}
		});
		btnNewButton.setBounds(561, 70, 100, 35);
		contentPane.add(btnNewButton);
		
		 btnNewButton_1 = new JButton("Dekont Al");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	btnNewButton_1.addMouseListener(new MouseAdapter() {
		    	    @Override
		    	    public void mouseClicked(MouseEvent e) {
		    	    	

		    	    	  String selectedPerson = (String) personelComboBox.getSelectedItem();
		                  String filePath = "C:\\Users\\esra6\\Desktop\\javaprojem\\hotel\\maas_bilgileri.pdf";
		                  String maasBilgileri = "Personel: " + selectedPerson + "\nOdeme Tarihi: " + txtodeme.getText() + "\nMaas: " + 
		                  txtmaas.getText() + "\nIban: " + txtiban.getText();
		                  createPDF(filePath, "Maas Bilgileri", maasBilgileri);
		                  File file = new File(filePath);
		                  if (file.exists()) {
		                      try {
		                          Desktop.getDesktop().open(file);
		                      } catch (IOException ex) {
		                          ex.printStackTrace();
		                      }
		                  } else {
		                      JOptionPane.showMessageDialog(null, "Dosya bulunamadı: " + filePath);
		                  }

		    	    }
		    	});

		    }
		});

		btnNewButton_1.setBounds(205, 107, 205, 23);
		contentPane.add(btnNewButton_1);
		
		txtdurum.setColumns(10);
		txtdurum.setBounds(465, 76, 86, 20);
		contentPane.add(txtdurum);
		
		JLabel lblNewLabel_4_1 = new JLabel("Durum");
		lblNewLabel_4_1.setBounds(464, 55, 46, 14);
		contentPane.add(lblNewLabel_4_1);
	}	
	public void getcomboveeri(JComboBox<String> comboBox) {
		 try {
		        String sorgu = "SELECT ad, soyad FROM personeller where durum='aktif'";
		        ResultSet rs = st.executeQuery(sorgu);
		        comboBox.removeAllItems();
		        while (rs.next()) {
		            String ad = rs.getString("ad");
		            String soyad = rs.getString("soyad");
		            comboBox.addItem(ad + " " + soyad);
		        }
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, e);
		    }
	}
	
	public void textdoldur(String selectedPerson) {
	    try {
	        String[] nameParts = selectedPerson.split(" ");
	        String ad = nameParts[0];
	        String soyad = nameParts[1];
	        String sorgu = "SELECT p.maas, mo.iban, mo.odeme_tarihi, COALESCE(mo.durum, 'Ödenmedi') AS durum " +
	                "FROM public.personeller p " +
	                "LEFT JOIN public.maas_odeme mo ON p.personel_id = mo.personel_id " +
	                "WHERE p.ad = ? AND p.soyad = ?";

	        PreparedStatement pst = con.prepareStatement(sorgu);
	        pst.setString(1, ad);
	        pst.setString(2, soyad);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            txtmaas.setText(rs.getString("maas"));
	            txtiban.setText(rs.getString("iban"));
	            txtodeme.setText(rs.getString("odeme_tarihi"));
	            txtdurum.setText(rs.getString("durum"));
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
	
	
	public int odeme(String selectedPerson) {
		  int i = 0;
		  try {
		    String[] nameParts = selectedPerson.split(" ");
		    String ad = nameParts[0];
		    String soyad = nameParts[1];
		    String sorgu = "SELECT personel_id FROM personeller WHERE ad = ? AND soyad = ?";
		    PreparedStatement pst = con.prepareStatement(sorgu);
		    pst.setString(1, ad);
		    pst.setString(2, soyad);
		    ResultSet rs = pst.executeQuery();

		    if (rs.next()) {
		      int personel_id = rs.getInt("personel_id");
		      String iban = txtiban.getText().trim();
		      String tarih = java.time.LocalDate.now().toString();
		      String durum = "Ödendi";

		      String sql = "INSERT INTO public.maas_odeme(odeme_id, personel_id, odeme_tarihi, durum, iban)"
		      		+ " VALUES (nextval('maas_odeme_odeme_id_seq'), " + personel_id + ", '" + tarih + "', '" + durum + "', '" + iban + "')";
		      i = st.executeUpdate(sql);
		    } else {
		      JOptionPane.showMessageDialog(null, "Seçilen personel bulunamadı!");
		    }
		  } catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, e);
		  }
		  return i;
		}
	public static void createPDF(String filePath, String title, String content) {
	    try {
	        PdfDocument pdf = new PdfDocument(new PdfWriter(filePath));
	        Document document = new Document(pdf);
	        document.add(new Paragraph(title));
	        document.add(new Paragraph(content));
	        document.close();
	        JOptionPane.showMessageDialog(null, "PDF başarıyla oluşturuldu. İndirme bağlantısı: " + filePath);
	    } catch (FileNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "PDF oluşturma hatası: Dosya bulunamadı");
	    }
	}
	
	
}