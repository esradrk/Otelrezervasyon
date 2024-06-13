package hotel.com;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class musterifatura extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private DefaultTableModel hoteltb;
    private DefaultTableModel hoteltb2;
    private Connection con = null;
    private Statement st = null;
    private JTable table2;
    private JTextField txttoplam;
    private JTable table;
    private int toplamHizmetFiyati = 0;
    private int toplamOdaFiyati = 0;
    private String odemeTarihi = LocalDate.now().toString();
    private int rezervasyonId;
    private JTextField txtkart;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    musterifatura frame = new musterifatura();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public musterifatura() {
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 717, 428);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 73, 518, 98);
        contentPane.add(scrollPane);

        table2 = new JTable();
    	table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] selectedRowsTable2 = table2.getSelectedRows();
				  int result = JOptionPane.showConfirmDialog(null, "Seçmek istediğinizden emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			            for (int rowIndex : selectedRowsTable2) {
				            Object value = table2.getValueAt(rowIndex, 8);
				            if (value instanceof Integer) {
				                int fiyat = (Integer) value;
				                toplamOdaFiyati += fiyat;
				            } else {
				            	
				            }
				        }

				        int toplamBorc = toplamHizmetFiyati + toplamOdaFiyati;
				        txttoplam.setText(String.valueOf(toplamBorc));
			        	
			        }
		    
			}
		});
        scrollPane.setViewportView(table2);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 228, 159, 98);
        contentPane.add(scrollPane_1);

        table = new JTable();
    	table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  int[] selectedRowsTable1 = table.getSelectedRows();
				  int result = JOptionPane.showConfirmDialog(null, "Ek hizmetten yararlanmak istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			        	 for (int rowIndex : selectedRowsTable1) {
					            int fiyat = Integer.parseInt(table.getValueAt(rowIndex, 1).toString());
					            toplamHizmetFiyati += fiyat;
					        }
					        int toplamBorc = toplamHizmetFiyati + toplamOdaFiyati;
					        txttoplam.setText(String.valueOf(toplamBorc));
			        }
			       
				
			}
		});
        scrollPane_1.setViewportView(table);

        JButton btnNewButton_1_1 = new JButton("Rezervasyon Yap");
        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rezervassyonyap rezerveyap = new rezervassyonyap();
                rezerveyap.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1_1.setBounds(536, 94, 143, 37);
        contentPane.add(btnNewButton_1_1);

        JButton btnNewButton_2 = new JButton("Rezervasyonlarım");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		musterirezervasyon msp=new musterirezervasyon();
        		   msp.setVisible(true);
                   dispose();
        	}
        });
        btnNewButton_2.setBounds(536, 195, 143, 37);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_2_1_3_1 = new JButton("E-Fatura");
        btnNewButton_2_1_3_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		musterifatura fa=new musterifatura();
     		   fa.setVisible(true);
                dispose();
        	}
        });
        btnNewButton_2_1_3_1.setBounds(536, 286, 143, 37);
        contentPane.add(btnNewButton_2_1_3_1);

        JButton btnNewButton_4 = new JButton("");
        btnNewButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musterianasayfa ana = new musterianasayfa();
                ana.setVisible(true);
                dispose();
            }
        });
        btnNewButton_4.setIcon(new ImageIcon(musterifatura.class.getResource("/resim/ana.JPG")));
        btnNewButton_4.setBounds(586, 11, 48, 43);
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
        btnNewButton_3.setIcon(new ImageIcon(musterifatura.class.getResource("/resim/23.JPG")));
        btnNewButton_3.setBounds(644, 11, 35, 35);
        contentPane.add(btnNewButton_3);

        JLabel lblNewLabel = new JLabel("FATURA");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 26, 87, 19);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Ek Hizmetler");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(10, 205, 131, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2_1 = new JLabel("Toplam Borç");
        lblNewLabel_2_1.setBounds(10, 359, 92, 19);
        contentPane.add(lblNewLabel_2_1);

        txttoplam = new JTextField();
        txttoplam.setColumns(10);
        txttoplam.setBounds(113, 358, 87, 20);
        contentPane.add(txttoplam);

        JButton btnNewButton = new JButton("Ödeme Yap");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
       
            	btnNewButton.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            			
            	    	int toplamBorc=Integer.parseInt(txttoplam.getText());
            	        String kartNo = txtkart.getText();
            	        if (kartNo.isEmpty()) {
            	            JOptionPane.showMessageDialog(null, "Lütfen kart numaranızı giriniz.");
            	            return;
            	        }
            	       odemeYap(rezervasyonId, kartNo, toplamBorc);
            	        setrezervelerToTableModel2();

            	    }
            	});
    

        btnNewButton.setBounds(262, 355, 109, 23);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("Kart No");
        lblNewLabel_2.setBounds(190, 229, 78, 14);
        contentPane.add(lblNewLabel_2);
        
        txtkart = new JTextField();
        txtkart.setBounds(251, 226, 86, 20);
        contentPane.add(txtkart);
        txtkart.setColumns(10);

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

    public ArrayList<fatura> getfaturalar() {
        ArrayList<fatura> faturalar = new ArrayList<fatura>();
        String sql = "SELECT r.ad, r.soyad, r.odeme_durum, r.giristarihi, r.cikistarihi, r.telno, o.\"odanumarası\", o.odatipi, o.fiyat "
                + "FROM public.rezervasyonlar AS r JOIN public.\"Odalar\" AS o ON r.odaid = o.id WHERE r.odeme_durum = 'ödenmedi';";

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                fatura fa = new fatura();
                fa.setAd(rs.getString(1));
                fa.setSoyad(rs.getString(2));
                fa.setOdemedurum(rs.getString(3));
                fa.setGiris(rs.getString(4));
                fa.setCikis(rs.getString(5));
                fa.setTelno(rs.getString(6));
                fa.setOdano(rs.getInt(7));
                fa.setOdatipi(rs.getString(8));
                fa.setFiyat(rs.getInt(9));
                         faturalar.add(fa);         }       } catch (SQLException e) {           
                        	 JOptionPane.showMessageDialog(null, e);}  return faturalar; }
    public void setrezervelerToTableModel2() {
        ArrayList<fatura> fatu = getfaturalar();
        hoteltb2 = new DefaultTableModel();
        hoteltb2.addColumn("Ad");
        hoteltb2.addColumn("Soyad");
        hoteltb2.addColumn("Ödeme Durumu"); 
        hoteltb2.addColumn("Giriş Tarihi");
        hoteltb2.addColumn("Çıkış Tarihi");
        hoteltb2.addColumn("Telefon No");
        hoteltb2.addColumn("Oda No");
        hoteltb2.addColumn("Oda Tipi");
        hoteltb2.addColumn("Fiyat");
     
        for (fatura f : fatu) {
            Object[] row = { f.getAd(), f.getSoyad(), f.getOdemedurum(), f.getGiris(), f.getCikis(), f.getTelno(), f.getOdano(),
                    f.getOdatipi(), f.getFiyat()};
            hoteltb2.addRow(row);
        }
        table2.setModel(hoteltb2);
    }

    public int odemeYap(int rezervasyonId, String kartNo, int toplamBorc) {
        int updatedRows = 0; // Başlangıçta güncellenen satır sayısını sıfırla
        String odemeSql = "UPDATE public.rezervasyonlar SET kartno = '" + kartNo + "', odeme_durum = 'ödendi', toplam_borc = " + toplamBorc + " WHERE rezervasyonlarid = " + rezervasyonId;
        try {
            updatedRows = st.executeUpdate(odemeSql);
            setrezervelerToTableModel2(); 
            JOptionPane.showMessageDialog(null, "Ödeme işlemi başarılı bir şekilde gerçekleştirildi.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return updatedRows;
    }

    
}
