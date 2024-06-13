package hotel.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class rezervasyonlarim extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String user = "postgres";
    private String password = "1234";
	private DefaultTableModel hoteltb;
	private JTable hoteltable;

	private Connection con = null;
	private Statement st = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rezervasyonlarim frame = new rezervasyonlarim();
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
	public rezervasyonlarim() {
		   try {
		         con = DriverManager.getConnection(url, user, password);
		            st = con.createStatement();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		setForeground(new Color(255, 128, 0));
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 436);
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
		btnNewButton_2_1.setBounds(536, 146, 143, 43);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2 = new JButton("Rezervasyonlar");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rezervasyonlarim rez = new rezervasyonlarim();
				rez.setVisible(true);
	                dispose(); 
			}
		});
		btnNewButton_2.setBounds(536, 85, 143, 37);
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
		btnNewButton_2_1_2.setBounds(536, 217, 143, 43);
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
		btnNewButton_2_1_3_1.setBounds(536, 286, 143, 37);
		contentPane.add(btnNewButton_2_1_3_1);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Anasayfa ana=new Anasayfa();
				ana.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(rezervasyonlarim.class.getResource("/resim/ana.JPG")));
		btnNewButton_4.setBounds(518, 11, 47, 49);
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
		btnNewButton_3.setIcon(new ImageIcon(rezervasyonlarim.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(644, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		
		
		
		hoteltable = new JTable();
		JScrollPane scrollPane = new JScrollPane(hoteltable);
		scrollPane.setBounds(10, 88, 516, 235);
		contentPane.add(scrollPane);
		JButton btnNewButton = new JButton("Güncelle");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guncellerezervasyon();
			    }
				
				
			
		});
		btnNewButton.setBounds(151, 334, 89, 38);
		contentPane.add(btnNewButton);
		hoteltb = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return true; 
		    }
		};
		
		
		
		
		
		JButton btnSil = new JButton("Sil");
		btnSil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = hoteltable.getSelectedRow();
		        if (selectedRow >= 0) {
		            int rezervasyonId = (int) hoteltable.getValueAt(selectedRow, 0); 
		            if (JOptionPane.showConfirmDialog(null, 
		            		"Bu rezervasyonu silmek istediğinize emin misiniz?", "Uyarı", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		                silRezervasyon(rezervasyonId);
		                ((DefaultTableModel) hoteltable.getModel()).removeRow(selectedRow);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen silmek istediğiniz rezervasyonu seçin.");
		        }
				
				
				
				
			}
		});
		btnSil.setBounds(279, 334, 89, 38);
		contentPane.add(btnSil);
		
		JLabel lblNewLabel = new JLabel("REZERVASYONLAR");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 42, 143, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ayarlar ayar=new ayarlar();
				ayar.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(rezervasyonlarim.class.getResource("/resim/AYAR.JPG")));
		btnNewButton_1.setBounds(569, 10, 65, 64);
		contentPane.add(btnNewButton_1);
		setrezervelerToTableModel();
	}
	public ArrayList<rezervasyon> getrezervasyonlarr(){
		ArrayList<rezervasyon> rezervasyonlar=new ArrayList<rezervasyon>();
		String sql = "SELECT rezervasyonlarid, odaid, ad, soyad, giristarihi, cikistarihi, telno FROM public.rezervasyonlar;";
		
		try {
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
						rezervasyon rezerve=new rezervasyon();
						rezerve.setRezervayonlarid(rs.getInt(1));;
						rezerve.setOdaid(rs.getInt(2));
						rezerve.setAd(rs.getString(3));
						rezerve.setSoyad(rs.getString(4));
						rezerve.setGiristarihi(rs.getString(5));
						rezerve.setCikistarihi(rs.getString(6));
						rezerve.setTelno(rs.getString(7));
						rezervasyonlar.add(rezerve);
					}
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				return rezervasyonlar;
	}
	
	public void setrezervelerToTableModel() {
		ArrayList<rezervasyon> rezervasyonlar = getrezervasyonlarr();
		hoteltable.removeAll();
		hoteltb = new DefaultTableModel();
		hoteltb.addColumn("Id");
		hoteltb.addColumn("Oda Id");
		hoteltb.addColumn("Ad");
		hoteltb.addColumn("Soyad");
		hoteltb.addColumn("Giriş Tarihi");
		hoteltb.addColumn("Çıkış Tarihi");
		hoteltb.addColumn("Telefon No");

		for (rezervasyon rezerve : rezervasyonlar) {
			Object[] row = { rezerve.getRezervayonlarid(), rezerve.getOdaid(), rezerve.getAd(),rezerve.getSoyad(),rezerve.getGiristarihi(),rezerve.getCikistarihi(),rezerve.getTelno() };
			hoteltb.addRow(row);
		}
		hoteltable.setModel(hoteltb);}
	
	public void silRezervasyon(int rezervasyonId) {
	    String sql = "DELETE FROM public.rezervasyonlar WHERE rezervasyonlarid = " + rezervasyonId;
	    try {
	        st.executeUpdate(sql);
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Silme işlemi sırasında bir hata oluştu: " + e.getMessage());
	    }
	}
	public void guncellerezervasyon() {
		
		 int rowCount = hoteltable.getRowCount();
	        for (int i = 0; i < rowCount; i++) {
	            int rezervasyonId = (int) hoteltable.getValueAt(i, 0);
	            int odaId = (int) hoteltable.getValueAt(i, 1);
	            String ad = (String) hoteltable.getValueAt(i, 2);
	            String soyad = (String) hoteltable.getValueAt(i, 3);
	            String girisTarihi = (String) hoteltable.getValueAt(i, 4);
	            String cikisTarihi = (String) hoteltable.getValueAt(i, 5);
	            String telNo = (String) hoteltable.getValueAt(i, 6);

	            String sql = "UPDATE public.rezervasyonlar SET odaid=?, ad=?,"
	            		+ " soyad=?, giristarihi=?, cikistarihi=?, telno=? WHERE rezervasyonlarid=?";
	            try (PreparedStatement pst = con.prepareStatement(sql)) {
	                pst.setInt(1, odaId);
	                pst.setString(2, ad);
	                pst.setString(3, soyad);
	                pst.setString(4, girisTarihi);
	                pst.setString(5, cikisTarihi);
	                pst.setString(6, telNo);
	                pst.setInt(7, rezervasyonId);
	                pst.executeUpdate();
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(null, "Güncelleme sırasında bir hata oluştu: " + ex.getMessage());
	            }
	        }
	        JOptionPane.showMessageDialog(null, "Tüm veriler güncellendi!");
		
		
		
	}
}
