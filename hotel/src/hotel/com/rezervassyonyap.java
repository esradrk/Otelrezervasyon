package hotel.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class rezervassyonyap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtgiris;
	private JTextField txtcıkıs;
	private JTextField txttel;
	private JTable hoteltable;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String user = "postgres";
    private String password = "1234";
	private Connection con = null;
	private Statement st = null;
	private int selectedrezerveId=0;
	private int odaidrezerve=0;

	private DefaultTableModel hoteltb;
	private JTextField txtad;
	private JTextField txdsoyad;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rezervassyonyap frame = new rezervassyonyap();
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
	public rezervassyonyap() {
		   try {
		         con = DriverManager.getConnection(url, user, password);
		            st = con.createStatement();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rezervasyon İşlemi");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(28, 39, 157, 19);
		contentPane.add(lblNewLabel);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tek", "Çift"}));
		comboBox.setBounds(28, 95, 88, 22);
		contentPane.add(comboBox);
		
		hoteltable = new JTable();
		hoteltable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    int tabloindex = hoteltable.getSelectedRow();

		        if (tabloindex >= 0) {  
		            int hotelid = Integer.parseInt(hoteltb.getValueAt(tabloindex, 0).toString());

		            selectedrezerveId = hotelid;
		            odaidrezerve = hotelid;

		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen listeden bir oda seçiniz.", "Seçim Yapılmadı", JOptionPane.WARNING_MESSAGE);
		        }
				
			}
		});
		hoteltable.setModel(new DefaultTableModel(		
				
		));
		hoteltable.setBounds(28, 169, 483, 161);
		contentPane.add(hoteltable);
	    scrollPane = new JScrollPane(hoteltable);
		scrollPane.setBounds(28, 169, 483, 130); 
		contentPane.add(scrollPane);
		JLabel lblNewLabel_1 = new JLabel("Oda Türü");
		lblNewLabel_1.setBounds(28, 70, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		txtgiris = new JTextField();
		txtgiris.setToolTipText("");
		txtgiris.setBounds(143, 96, 86, 20);
		contentPane.add(txtgiris);
		txtgiris.setColumns(10);
		
		txtcıkıs = new JTextField();
		txtcıkıs.setColumns(10);
		txtcıkıs.setBounds(253, 96, 86, 20);
		contentPane.add(txtcıkıs);
		
		JLabel lblNewLabel_1_1 = new JLabel("Giriş Tarihi");
		lblNewLabel_1_1.setBounds(143, 70, 88, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Çıkış Tarihi");
		lblNewLabel_1_2.setBounds(253, 70, 88, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Ara");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			        String girisTarihi = txtgiris.getText().trim(); 

			        if (girisTarihi.isEmpty()) {
			            JOptionPane.showMessageDialog(contentPane, "Lütfen rezervason tarihlerinizi giriniz.", "Uyarı",
			            		JOptionPane.WARNING_MESSAGE);
			        } else {
			            String seciliOdaTipi = comboBox.getSelectedItem().toString();
			            filtreleVeGoster(seciliOdaTipi);
			        }
			        
			}
		});
		btnNewButton.setBounds(358, 80, 67, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_3 = new JLabel("Telefon Numarası");
		lblNewLabel_1_3.setBounds(178, 310, 110, 25);
		contentPane.add(lblNewLabel_1_3);
		
		txttel = new JTextField();
		txttel.setBounds(283, 310, 110, 23);
		contentPane.add(txttel);
		txttel.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Rezervasyan Et");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String ad = txtad.getText().trim();
			        String soyad = txdsoyad.getText().trim();
			        String telefonNo = txttel.getText().trim();
			        if (ad.isEmpty() || soyad.isEmpty() || telefonNo.isEmpty()) {
			            JOptionPane.showMessageDialog(contentPane, "Lütfen tüm alanları doldurunuz.", "Eksik Bilgi", 
			            		JOptionPane.WARNING_MESSAGE);
			        } else {
			            int adet = rezervasyonYap(selectedrezerveId, odaidrezerve, ad, soyad, txtgiris.getText(), txtcıkıs.getText(), telefonNo);
			            if (adet > 0) {
			                odaDurumunuGuncelle(odaidrezerve);  
			                JOptionPane.showMessageDialog(null, adet + " kayıt eklendi.");
			                setrezervelerToTableModel();
			            }
			            selectedrezerveId = 0;
			            odaidrezerve = 0;
		        }
			}
		});
		btnNewButton_1.setBounds(250, 346, 143, 36);
		contentPane.add(btnNewButton_1);
		
		JLabel lblBoOdalar = new JLabel("Boş Odalar");
		lblBoOdalar.setForeground(Color.WHITE);
		lblBoOdalar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBoOdalar.setBounds(28, 139, 117, 19);
		contentPane.add(lblBoOdalar);
		
		JButton btnNewButton_1_1 = new JButton("Rezervasyon Yap");
		btnNewButton_1_1.setBounds(534, 88, 143, 37);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Rezervasyonlarım");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				musterirezervasyon rezerve=new musterirezervasyon();
				rezerve.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setBounds(534, 171, 143, 42);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1_3_1 = new JButton("E-Fatura");
		btnNewButton_2_1_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				musterifatura fst=new musterifatura();
				fst.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1_3_1.setBounds(534, 262, 143, 37);
		contentPane.add(btnNewButton_2_1_3_1);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				girisekrani geriGiris = new girisekrani();
	               geriGiris.setVisible(true);
	                dispose(); 
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(rezervassyonyap.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(557, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				musterianasayfa ana = new musterianasayfa();
	               ana.setVisible(true);
	                dispose(); 
				
				
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(rezervassyonyap.class.getResource("/resim/ana.JPG")));
		btnNewButton_4.setBounds(499, 11, 46, 47);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_2 = new JLabel("Ad");
		lblNewLabel_2.setBounds(26, 310, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Soyad");
		lblNewLabel_2_1.setBounds(28, 351, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		txtad = new JTextField();
		txtad.setBounds(82, 310, 86, 20);
		contentPane.add(txtad);
		txtad.setColumns(10);
		
		txdsoyad = new JTextField();
		txdsoyad.setColumns(10);
		txdsoyad.setBounds(82, 348, 86, 20);
		contentPane.add(txdsoyad);
		
		setrezervelerToTableModel();
	}
	
	
	
	public ArrayList<rezerveyap> getrezerveyapmak(){
		ArrayList<rezerveyap> rezervasyonlar=new ArrayList<rezerveyap>();
		String sql = "SELECT * FROM public.\"Odalar\" WHERE durum = 'boş';";
				try {
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
						rezerveyap rezerve=new rezerveyap();
						rezerve.setId(rs.getInt(1));
						rezerve.setOdanumara(rs.getInt(2));
						rezerve.setOdatipi(rs.getString(3));
						rezerve.setFiyat(rs.getString(4));
						rezervasyonlar.add(rezerve);
					}
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				return rezervasyonlar;
	}
	
	
	public void setrezervelerToTableModel() {
		ArrayList<rezerveyap> rezervasyonlar = getrezerveyapmak();
		hoteltable.removeAll();
		hoteltb = new DefaultTableModel();
		hoteltb.addColumn("Id");
		hoteltb.addColumn("Oda Numarası");
		hoteltb.addColumn("Oda Tipi");
		hoteltb.addColumn("Fiyat");
		for (rezerveyap rezerve : rezervasyonlar) {
			Object[] row = { rezerve.getId(), rezerve.getOdanumara(), rezerve.getOdatipi(),rezerve.getFiyat() };
			hoteltb.addRow(row);
		}
		hoteltable.setModel(hoteltb);}
	public void filtreleVeGoster(String odaTipi) {
	    ArrayList<rezerveyap> odalar = new ArrayList<>();
	    String sql = "SELECT id, odanumarası, odatipi, fiyat FROM public.\"Odalar\" WHERE durum = 'boş' AND odatipi = '" + odaTipi + 
	    		"' ORDER BY id;";
	    try {
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            rezerveyap oda = new rezerveyap();
	            oda.setId(rs.getInt("id"));
	            oda.setOdanumara(rs.getInt("odanumarası"));
	            oda.setOdatipi(rs.getString("odatipi"));
	            oda.setFiyat(rs.getString("fiyat"));
	            odalar.add(oda);}
	        hoteltb.setRowCount(0);
	        for (rezerveyap oda : odalar) {
	            Object[] row = { oda.getId(), oda.getOdanumara(), oda.getOdatipi(), oda.getFiyat() };
	            hoteltb.addRow(row);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e.getMessage()); }}

    public int rezervasyonYap(int id,int odaId,String ad,String soyad, String girisTarihi, String cikisTarihi, String telefonNo) {
  
    	String sql = "INSERT INTO public.rezervasyonlar (rezervasyonlarid,odaid, ad, soyad, giristarihi, cikistarihi, telno) "
    			+ "VALUES ("+id+"," + odaId + ", '" + ad + "', '" + soyad + "', '" + girisTarihi + "', '" + cikisTarihi + "', "
    					+ "'" + telefonNo + "')";

    	int i = 0;
		try {
			i = st.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return i;
	}
    
    public void odaDurumunuGuncelle(int odaId) {
        String sql = "UPDATE public.\"Odalar\" SET durum = 'dolu' WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, odaId);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "Oda durumu güncellenemedi.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Oda durumu güncellenirken hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
