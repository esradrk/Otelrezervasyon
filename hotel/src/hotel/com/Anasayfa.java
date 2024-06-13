package hotel.com;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Anasayfa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "1234";
    private Connection con = null;
    private Statement st = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Anasayfa frame = new Anasayfa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Anasayfa() {
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 426);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
     
		
		JButton btnNewButton_2 = new JButton("Rezervasyonlar");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rezervasyonlarim rez=new rezervasyonlarim();
				rez.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(631, 81, 143, 44);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Odalar");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				odalar oda=new odalar();
				oda.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(631, 159, 143, 50);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_2 = new JButton("Personeller");
		btnNewButton_2_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				personeller per=new personeller();
				per.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1_2.setBounds(631, 235, 143, 44);
		contentPane.add(btnNewButton_2_1_2);
		
		
		JButton btnNewButton_2_1_3_1 = new JButton("Faturalar");
		btnNewButton_2_1_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				efatura e2=new efatura();
				e2.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1_3_1.setBounds(631, 314, 143, 44);
		contentPane.add(btnNewButton_2_1_3_1);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				girisekrani grs=new girisekrani();
				grs.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Anasayfa.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(739, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ayarlar ayar=new ayarlar();
				ayar.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Anasayfa.class.getResource("/resim/AYAR.JPG")));
		btnNewButton.setBounds(646, 11, 65, 64);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 611, 365);
		contentPane.add(panel);

		int doluOdalarCount = getRoomCountByStatus("dolu");
		int bosOdalarCount = getRoomCountByStatus("boş");

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(doluOdalarCount, "Dolu Odalar", "");
		dataset.addValue(bosOdalarCount, "Boş Odalar", "");

		JFreeChart chart = ChartFactory.createBarChart(
		        "Odaların Durumu", 
		        "", 
		        "Oda Sayısı", 
		        dataset
		);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, panel.getWidth(), panel.getHeight()); 
		panel.add(chartPanel);
		chartPanel.setLayout(null);
	}
	
    private int getRoomCountByStatus(String durum) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM public.\"Odalar\" WHERE durum='" + durum + "';";
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e.getMessage());
        }
        return count;
    }
}
