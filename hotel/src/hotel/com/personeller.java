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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;


public class personeller extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	   private String url = "jdbc:postgresql://localhost:5432/postgres";
	    private String user = "postgres";
	    private String password = "1234";
	    private Connection con = null;
	    private Statement st = null;
	    private JTable table;
	    private JTextField txttel;
	    private JTextField txtmaas;
	    private JTextField txtsicilno;
	    private JTextField txttc;
	    private JTextField txtgiris;
	    private JTextField txtdogum;
	    private JTextField txtsoyad;
        private DefaultTableModel dfpt;
        
        private int selectint=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					personeller frame = new personeller();
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
	public personeller() {
		   try {
		         con = DriverManager.getConnection(url, user, password);
		            st = con.createStatement();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 419);
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
		btnNewButton_2_1.setBounds(676, 139, 143, 44);
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
		btnNewButton_2.setBounds(676, 68, 143, 37);
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
		btnNewButton_2_1_2.setBounds(676, 218, 143, 44);
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
		btnNewButton_2_1_3_1.setBounds(676, 291, 143, 44);
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
		btnNewButton_4.setIcon(new ImageIcon(personeller.class.getResource("/resim/ana.JPG")));
		btnNewButton_4.setBounds(637, 11, 52, 46);
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
		btnNewButton_3.setIcon(new ImageIcon(personeller.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(774, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 57, 660, 126);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sicil No");
		lblNewLabel.setBounds(444, 11, 50, 14);
		panel.add(lblNewLabel);
		
		txttel = new JTextField();
		txttel.setBounds(200, 8, 72, 20);
		panel.add(txttel);
		txttel.setColumns(10);
		
		JLabel lblAdSoyad = new JLabel("Ad ");
		lblAdSoyad.setBounds(10, 11, 40, 14);
		panel.add(lblAdSoyad);
		
		JTextField txtad = new JTextField();
		txtad.setColumns(10);
		txtad.setBounds(51, 8, 72, 20);
		panel.add(txtad);
		
		JLabel lblnvan = new JLabel("Tc");
		lblnvan.setBounds(290, 49, 40, 14);
		panel.add(lblnvan);
		
		JLabel lblDepartman = new JLabel("Departman");
		lblDepartman.setBounds(10, 96, 67, 14);
		panel.add(lblDepartman);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Resepsiyonist", "Temizlikçi", "Aşçı", "Garson", "Ön Büro Müdürü", "Muhasebe Müdürü", "Personel Müdürü", "Güvenlik", "Komi"}));
		comboBox.setBounds(87, 92, 129, 22);
		panel.add(comboBox);
		
		txtmaas = new JTextField();
		txtmaas.setColumns(10);
		txtmaas.setBounds(362, 8, 72, 20);
		panel.add(txtmaas);
		
		JLabel lblTelefonNo = new JLabel("Telefon No");
		lblTelefonNo.setBounds(132, 11, 79, 14);
		panel.add(lblTelefonNo);
		
		txtsicilno = new JTextField();
		txtsicilno.setColumns(10);
		txtsicilno.setBounds(545, 8, 72, 20);
		panel.add(txtsicilno);
		
		JButton btnNewButton_1 = new JButton("Personel Ekle");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        String departman = comboBox.getSelectedItem().toString();
		        String cikis = "yok";
		        String durum = "aktif";
		        if (txtad.getText().isEmpty() || txtsoyad.getText().isEmpty() || 
		        		txttc.getText().isEmpty() || txttel.getText().isEmpty() || txtdogum.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(contentPane, "Lütfen tüm alanları doldurunuz.", "Eksik Bilgi", JOptionPane.WARNING_MESSAGE);
		        } else {
		            int adet = personelekle(
		                txtad.getText().trim(),
		                txtsoyad.getText().trim(),
		                departman,
		                txtmaas.getText().trim(),
		                txttc.getText().trim(),
		                txttel.getText().trim(),
		                cikis,
		                durum,
		                txtsicilno.getText().trim(),
		                txtgiris.getText().trim(),
		                txtdogum.getText().trim()
		            );

		            if (adet > 0) {
		                JOptionPane.showMessageDialog(null, adet + " kayıt eklendi.");
		                setpersoneltablo();
		            }
		        }

				
			}
		});
		btnNewButton_1.setBounds(312, 92, 122, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi");
		lblDoumTarihi.setBounds(444, 49, 77, 14);
		panel.add(lblDoumTarihi);
		
		txttc = new JTextField();
		txttc.setColumns(10);
		txttc.setBounds(362, 46, 72, 20);
		panel.add(txttc);
		
		JLabel lblGiriTarihi = new JLabel("Giriş Tarihi");
		lblGiriTarihi.setBounds(133, 49, 72, 14);
		panel.add(lblGiriTarihi);
		
		txtgiris = new JTextField();
		txtgiris.setColumns(10);
		txtgiris.setBounds(200, 46, 72, 20);
		panel.add(txtgiris);
		
		JLabel lblkTarihi_1_1 = new JLabel("Maaş");
		lblkTarihi_1_1.setBounds(293, 11, 50, 14);
		panel.add(lblkTarihi_1_1);
		
		txtdogum = new JTextField();
		txtdogum.setColumns(10);
		txtdogum.setBounds(545, 46, 72, 20);
		panel.add(txtdogum);
		
		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setBounds(10, 49, 56, 14);
		panel.add(lblSoyad);
		
		txtsoyad = new JTextField();
		txtsoyad.setColumns(10);
		txtsoyad.setBounds(51, 46, 72, 20);
		panel.add(txtsoyad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 232, 660, 140);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   int tablosec = table.getSelectedRow();
			        if (tablosec >= 0) {
			            int personelid = Integer.parseInt(dfpt.getValueAt(tablosec, 0).toString());
			            selectint = personelid; 
			        } else {
			            JOptionPane.showMessageDialog(null, "Lütfen listeden bir personel seçiniz.", "Seçim Yapılmadı", JOptionPane.WARNING_MESSAGE);
			        }
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Maaş Ödeme");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				maashesabi maas=new maashesabi();
				maas.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setBounds(10, 25, 163, 23);
		contentPane.add(btnNewButton);
		
		JButton btnknVer = new JButton("Çıkışını Ver");
		btnknVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if (selectint > 0) {
			            int response = JOptionPane.showConfirmDialog(null, "Çıkışını vermek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			            if (response == JOptionPane.YES_OPTION) {
			                String durum = "pasif";
			                String cikis = java.time.LocalDate.now().toString();
			                int sonuc = istencikar(selectint, durum, cikis);
			                if (sonuc > 0) {
			                    JOptionPane.showMessageDialog(null, "Personel çıkışı başarıyla verildi.");
			                    setpersoneltablo();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Personel çıkışı verilemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Lütfen listeden bir personel seçiniz.", "Seçim Yapılmadı", JOptionPane.WARNING_MESSAGE);
			      
			    }
				
			}
		});
		btnknVer.setBounds(410, 25, 143, 23);
		contentPane.add(btnknVer);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Tüm Çalışanlar", "Aktif Çalışanlar", "Pasif Çalışanlar"}));
		comboBox_1.setBounds(188, 198, 121, 22);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_1_2 = new JButton("Ara");
		btnNewButton_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String personelsecim=comboBox_1.getSelectedItem().toString();
				filtrele(personelsecim);
				
				
			}
		});
		btnNewButton_1_2.setBounds(339, 198, 113, 23);
		contentPane.add(btnNewButton_1_2);
		
		JLabel lblNewLabel_1 = new JLabel("Personeller");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(6, 0, 136, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ayarlar ayar=new ayarlar();
				ayar.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(personeller.class.getResource("/resim/AYAR.JPG")));
		btnNewButton_5.setBounds(699, 0, 65, 64);
		contentPane.add(btnNewButton_5);
		setpersoneltablo();
	}
	

	public ArrayList<personel> getpersonel(){
		ArrayList<personel> personeller=new ArrayList<personel>();
		String sql = "SELECT * FROM public.personeller;";
				try {
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
						personel perso=new personel();
						perso.setId(rs.getInt(1));
					    perso.setAd(rs.getString(2));
					    perso.setSoyad(rs.getString(3));
					    perso.setTc(rs.getString(4));
					    perso.setTelno(rs.getString(5));
					    perso.setDogumtarihi(rs.getString(6));
					    perso.setSicilno(rs.getString(7));
					    perso.setGiristarihi(rs.getString(8));
					    perso.setCikistarihi(rs.getString(9));
					    perso.setMaas(rs.getString(10));
					    perso.setDepartman(rs.getString(11));
					    perso.setDurum(rs.getString(12));
						personeller.add(perso);
					}
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				return personeller;
	}
	
	
	public void setpersoneltablo() {
		ArrayList<personel> persinel = getpersonel();
		table.removeAll();
		dfpt = new DefaultTableModel();
	    dfpt.addColumn("ID"); 
		dfpt.addColumn("Ad");
		dfpt.addColumn("Soyad");
		dfpt.addColumn("Departman");
		dfpt.addColumn("Maaş");
		dfpt.addColumn("Tc");
		dfpt.addColumn("Telefon No");
		dfpt.addColumn("Çıkış Tarihi");
		dfpt.addColumn("Durum");
		dfpt.addColumn("Sicil No");
		dfpt.addColumn("Giriş Tarihi");
		dfpt.addColumn("Doğum Tarihi");


		for (personel p : persinel) {
			Object[] row = { p.getId(),p.getAd(),p.getSoyad(),p.getTc(),p.getTelno(),p.getDogumtarihi(),p.getSicilno(),p.getGiristarihi(),p.getCikistarihi(),p.getMaas(),p.getDepartman(),p.getDurum()};
			dfpt.addRow(row);
		}
		table.setModel(dfpt);
		}
	
	
	public int personelekle(String ad, String soyad, String departman, String maas, String tc, 
			String telno, String cikis, String durum, String sicilno, String giris, String dttarihi) {
	    String sql = "INSERT INTO public.personeller(ad, soyad, departman, maas, tc, telefon, cikis_tarihi, "
	    		+ "durum, sicilno, giristarihi, dogumtarih) VALUES "
	               + "('" + ad + "','" + soyad + "','" + departman + "','" + maas + "','" + tc + "','" + telno + "',"
	               		+ "'" + cikis + "','" + durum + "','" + sicilno + "','" + giris + "','" + dttarihi + "');";
	    int i = 0;
	    try {
	        i = st.executeUpdate(sql);
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	    return i;
	}
	
	public int istencikar(int id,String durum,String cikis) {
		String sql="UPDATE public.personeller SET cikis_tarihi='"+cikis+"', durum='"+durum+"'WHERE personel_id="+id+";";
		int i=0;
		try {
			i=st.executeUpdate(sql);
		} catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, e);

		}
		return i;
	}
	public void filtrele(String personelsecim) {
	    ArrayList<personel> personeller = new ArrayList<>();
	    String sql = "";

	    if (personelsecim.equals("Tüm Çalışanlar")) {
	        sql = "SELECT * FROM public.personeller";
	    } else if (personelsecim.equals("Aktif Çalışanlar")) {
	        sql = "SELECT * FROM public.personeller WHERE durum='aktif'";
	    } else if (personelsecim.equals("Pasif Çalışanlar")) {
	        sql = "SELECT * FROM public.personeller WHERE durum='pasif'";
	    } try {
	        ResultSet rs = st.executeQuery(sql);  while (rs.next()) {
	            personel perso = new personel();
	            perso.setId(rs.getInt("personel_id"));
	            perso.setAd(rs.getString("ad"));
	            perso.setSoyad(rs.getString("soyad"));
	            perso.setTc(rs.getString("tc"));
	            perso.setTelno(rs.getString("telefon"));
	            perso.setDogumtarihi(rs.getString("dogumtarih"));
	            perso.setSicilno(rs.getString("sicilno"));
	            perso.setGiristarihi(rs.getString("giristarihi"));
	            perso.setCikistarihi(rs.getString("cikis_tarihi"));
	            perso.setMaas(rs.getString("maas"));
	            perso.setDepartman(rs.getString("departman"));
	            perso.setDurum(rs.getString("durum"));
	            personeller.add(perso);
	        }

	        dfpt.setRowCount(0);
	        for (personel p : personeller) {
	            Object[] row = {
	                p.getId(), p.getAd(), p.getSoyad(), p.getDepartman(), p.getMaas(), p.getTc(), p.getTelno(),
	                p.getCikistarihi(), p.getDurum(), p.getSicilno(), p.getGiristarihi(), p.getDogumtarihi()
	            };
	            dfpt.addRow(row);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e.getMessage());
	    }
	}


}
