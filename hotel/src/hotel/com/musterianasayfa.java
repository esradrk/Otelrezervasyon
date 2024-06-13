package hotel.com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class musterianasayfa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					musterianasayfa frame = new musterianasayfa();
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
	public musterianasayfa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRezervasyonlarm = new JButton("REZERVASYONLARIM");
		btnRezervasyonlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				musterirezervasyon mst=new musterirezervasyon();
				mst.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				girisekrani grs=new girisekrani();
				grs.setVisible(true);
                dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(musterianasayfa.class.getResource("/resim/23.JPG")));
		btnNewButton_3.setBounds(580, 11, 35, 35);
		contentPane.add(btnNewButton_3);
		btnRezervasyonlarm.setOpaque(true);
		btnRezervasyonlarm.setForeground(Color.WHITE);
		btnRezervasyonlarm.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		btnRezervasyonlarm.setBackground(Color.GRAY);
		btnRezervasyonlarm.setBounds(148, 203, 311, 56);
		contentPane.add(btnRezervasyonlarm);
		
		JButton btnRezervasyonlar = new JButton("FATURALARIM");
		btnRezervasyonlar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				musterifatura fst=new musterifatura();
				fst.setVisible(true);
				dispose();
			}
		});
		btnRezervasyonlar.setOpaque(true);
		btnRezervasyonlar.setForeground(Color.WHITE);
		btnRezervasyonlar.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		btnRezervasyonlar.setBackground(Color.GRAY);
		btnRezervasyonlar.setBounds(148, 285, 311, 56);
		contentPane.add(btnRezervasyonlar);
		
		JButton btnNewButton = new JButton("REZERVASYON OLUŞTUR");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rezervassyonyap rezerve=new rezervassyonyap();
				 rezerve.setVisible(true);
				 dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(148, 121, 311, 56);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("OTELİMİZE HOŞGELDİNİZ");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_1.setBounds(111, 65, 424, 45);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(musterianasayfa.class.getResource("/resim/7a49a1666085ce3f9ee72297caac7270.jpg")));
		lblNewLabel.setBounds(0, 0, 628, 385);
		contentPane.add(lblNewLabel);
	}
}
