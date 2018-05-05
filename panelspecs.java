import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class panelspecs extends JPanel {//giriþ panelinin bütün özellikleri

	private JTextField jtext1;
	private JPasswordField jpw1;
	private JButton giris;
	private JButton uyeol;
	
	public panelspecs(){

		setPreferredSize(new Dimension(500,500));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		jtext1 = new JTextField("Kullanýcý Adý",15);
		jtext1.setColumns(20);
		jtext1.setPreferredSize(new Dimension(0,25));
		jtext1.setToolTipText("Kullanýcý adýnýzý girin");
		add(jtext1,c);
		jpw1 = new JPasswordField("Þifre",15);
		jpw1.setColumns(20);
		jpw1.setPreferredSize(new Dimension(0,25));
		jpw1.setToolTipText("Þifrenizi girin");
		c.gridy = 1;
		add(jpw1,c);
		giris = new JButton("Giriþ");
		giris.setPreferredSize(new Dimension(150,20));
		c.insets = new Insets(0,0,3,0);
		c.fill = GridBagConstraints.CENTER;
		c.gridy = 2;
		add(giris,c);
		uyeol = new JButton("Üye Ol");
		uyeol.setPreferredSize(new Dimension(150,20));
		c.gridy = 3;
		add(uyeol,c);
		setBackground(Color.decode("#800000"));
		ActionHandler handler = new ActionHandler();
		giris.addActionListener(handler);
		uyeol.addActionListener(handler);
	}
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == giris){
			String k_adi = jtext1.getText();
			String parola = jpw1.getText();
			//veri tabanýnda kullanýcý adý ve parolayý ara, bulursa devam et
				MasaSecimi masasecildi = new MasaSecimi();
				masasecildi.masayisec();
			}
			
			
			if(arg0.getSource() == uyeol){
				cluyeol kayit = new cluyeol();
				kayit.uyekaydi();
			}
		}
	}
}
