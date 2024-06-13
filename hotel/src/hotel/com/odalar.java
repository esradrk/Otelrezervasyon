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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class odalar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String url = "jdbc:postgresql://localhost:5432/postgres";
	private String user = "postgres";
    private String password = "1234";
	private Connection con = null;
	private Statement st = null;
	private JTextField txtno;
	private JTextField txtfiyat;
	private JTable odatable;
	private DefaultTableModel odadb;
	private int ODAİD=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					odalar frame = new odalar();
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
	public odalar() {
		   try {
		         con = DriverManager.getConnection(url, user, password);
		            st = con.createStatement();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 411);
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
				
				Anasayfa ana=new Anasayfa();
				ana.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(odalar.class.getResource("/resim/ana.JPG")));
		btnNewButton_4.setBounds(512, 9, 47, 49);
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
		btnNewButton_3.setIcon(new ImageIcon(odalar.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(644, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 63, 371, 263);
		contentPane.add(scrollPane);
		
		odatable = new JTable();
		odatable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  int i = odatable.getSelectedRow();
					int odaidsi = Integer.parseInt("" + odadb.getValueAt(i, 0));

					ODAİD = odaidsi;
				
					txtno.setText("" + odadb.getValueAt(i, 1));
					String odaTipi = (String)odadb.getValueAt(i, 2);
					txtfiyat.setText("" + odadb.getValueAt(i, 3));
				
			}
		});
		scrollPane.setViewportView(odatable);
		
		JLabel lblNewLabel = new JLabel("ODALAR");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 42, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Oda No");
		lblNewLabel_1.setBounds(10, 98, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtno = new JTextField();
		txtno.setBounds(53, 95, 86, 20);
		contentPane.add(txtno);
		txtno.setColumns(10);
		
		JComboBox combo = new JComboBox();
		combo.setModel(new DefaultComboBoxModel(new String[] {"Tek", "Çift"}));
		combo.setBounds(84, 163, 55, 22);
		contentPane.add(combo);
		
		JLabel lblNewLabel_2 = new JLabel("Oda Tipi");
		lblNewLabel_2.setBounds(10, 167, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fiyat");
		lblNewLabel_2_1.setBounds(10, 129, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		txtfiyat = new JTextField();
		txtfiyat.setColumns(10);
		txtfiyat.setBounds(53, 126, 86, 20);
		contentPane.add(txtfiyat);
		
		JButton btnNewButton = new JButton("Oda Ekle");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				    String durumm = "boş";
			        String odanoStr = txtno.getText().trim();
			        String fiyat = txtfiyat.getText().trim();
			        String secim = combo.getSelectedItem().toString();
			        
			        
			        if (odanoStr.isEmpty() || fiyat.isEmpty() || secim.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
			            return;
			        }
	        try {
			            int odano = Integer.parseInt(odanoStr); 
			            int result = odalarrrr(odano, secim, fiyat, durumm); 
			            if (result > 0) {
			                JOptionPane.showMessageDialog(null, "Oda başarıyla eklendi!");
			                setrezervelerToTableModel(); 
			                txtfiyat.setText(""); 
					        txtno.setText(""); 
			            } else {
			                JOptionPane.showMessageDialog(null, "Oda eklenirken bir hata oluştu.");
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir oda numarası girin!");
			        }
				
			}
		});
		btnNewButton.setBounds(30, 219, 89, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1_2 = new JButton("Güncelle");
		btnNewButton_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String odanoStr = txtno.getText().trim();
			        String fiyat = txtfiyat.getText().trim();
			        String secim = combo.getSelectedItem().toString();
				if (ODAİD<1) {
					JOptionPane.showMessageDialog(null, "Tablodan Oda Seçiniz.");
				}
				
				 int odano = Integer.parseInt(odanoStr); 
			int adet=odalarguncel(ODAİD, odano, fiyat, secim);
				JOptionPane.showMessageDialog(null,adet+" Kayıt güncellendi");
				setrezervelerToTableModel();
				ODAİD=0;
				txtfiyat.setText(""); 
		        txtno.setText(""); 
			}
		});
		btnNewButton_1_2.setBounds(233, 337, 189, 23);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				ayarlar ayar=new ayarlar();
				ayar.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(odalar.class.getResource("/resim/AYAR.JPG")));
		btnNewButton_1.setBounds(569, 3, 65, 64);
		contentPane.add(btnNewButton_1);
		setrezervelerToTableModel() ;
		
	}
	public ArrayList<oda>getodalar(){
		ArrayList<oda> odalar=new ArrayList<oda>();
		String sql="SELECT id, \"odanumarası\", odatipi, fiyat, durum\r\n"
				+ "	FROM public.\"Odalar\";";
		try {
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				oda o=new oda();
				o.setId(rs.getInt(1));
				o.setOdano(rs.getInt(2));
				o.setOdatipi(rs.getString(3));		
				o.setFiyat(rs.getString(4));
				o.setDurum(rs.getString(5));

				odalar.add(o);
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return odalar;

		
		
	}
	
	public void setrezervelerToTableModel() {
		ArrayList<oda> odaaalar = getodalar();
		odatable.removeAll();
		odadb = new DefaultTableModel();
		odadb.addColumn("Id");
		odadb.addColumn("Oda Numarası");
		odadb.addColumn("Oda Tipi");
		odadb.addColumn("Fiyat");
		odadb.addColumn("Durum");
		for (oda odaa : odaaalar) {
			Object[] row = { odaa.getId(), odaa.getOdano(), odaa.getOdatipi(),odaa.getFiyat(),odaa.getDurum() };
			odadb.addRow(row);
		}
		odatable.setModel(odadb);}
	
	
	public int odalarrrr(int odano, String odatipi, String fiyat, String durum) {
		 try {
		        String kontrol = "SELECT COUNT(*) FROM public.\"Odalar\" WHERE \"odanumarası\" = " + odano;
		        ResultSet resultSet = st.executeQuery(kontrol);
		        resultSet.next();
		        int count = resultSet.getInt(1);

		        if (count > 0) {
		            JOptionPane.showMessageDialog(null, "Bu oda numarası zaten kullanılıyor!");
		            return 0;
		        } else {
		            String insertQuery = "INSERT INTO public.\"Odalar\"(id, \"odanumarası\","
		            		+ " odatipi, fiyat, durum) VALUES (nextval('odalar_id_seq')," + odano + ", '" + odatipi + "'"
		            				+ ", '" + fiyat + "','" + durum + "')";
		            return st.executeUpdate(insertQuery);
		        }
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, e);
		        return 0;
		    }
	}
	
	public int odalarguncel(int odaid,int odano,String fiyat,String odatipi)
	{
		String sql="UPDATE public.\"Odalar\" SET \"odanumarası\"="+odano+",  fiyat='"+fiyat+"',odatipi='"+odatipi+"' WHERE id="+odaid+";";
	int i=0;
	try {
		i=st.executeUpdate(sql);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}
	return i;
	}


}
