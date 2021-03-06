import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class panelspecs extends JPanel {//giri� panelinin b�t�n �zellikleri

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
		jtext1 = new JTextField("Kullan�c� Ad�",15);
		jtext1.setColumns(20);
		jtext1.setPreferredSize(new Dimension(0,25));
		jtext1.setToolTipText("Kullan�c� ad�n�z� girin");
		add(jtext1,c);
		jpw1 = new JPasswordField("�ifre",15);
		jpw1.setColumns(20);
		jpw1.setPreferredSize(new Dimension(0,25));
		jpw1.setToolTipText("�ifrenizi girin");
		c.gridy = 1;
		add(jpw1,c);
		giris = new JButton("Giri�");
		giris.setPreferredSize(new Dimension(150,20));
		c.insets = new Insets(0,0,3,0);
		c.fill = GridBagConstraints.CENTER;
		c.gridy = 2;
		add(giris,c);
		uyeol = new JButton("�ye Ol");
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
			Boolean kullanicikontrol=false;
			Boolean sifrekontrol=false;
			if(arg0.getSource() == giris){
				String k_adi = jtext1.getText();
				String parola = jpw1.getText();
				try{
					Connection baglan = (Connection) DBConnection.baglanti();
					PreparedStatement statement = (PreparedStatement) baglan.prepareStatement("SELECT k_adi FROM `uyeler`");
					ResultSet result = statement.executeQuery();
					while(result.next()){
						if(k_adi.equals(result.getString("k_adi"))){
							kullanicikontrol=true;
							break;
						}
					}
					statement = (PreparedStatement) baglan.prepareStatement("SELECT parola FROM `uyeler`");
					result = statement.executeQuery();
					while(result.next())
						if(parola.equals(result.getString("parola"))){
							sifrekontrol=true;
							break;
						}
				
				}catch(Exception e){}
				if(!kullanicikontrol || !sifrekontrol){
					JOptionPane.showMessageDialog(null, "Kullan�c� bulunamad�.","HATA",0);
				}
				if(kullanicikontrol && sifrekontrol){
					MasaSecimi masasecildi = new MasaSecimi();
					masasecildi.masayisec();
				}
				/*MasaSecimi masasecildi = new MasaSecimi();
				masasecildi.masayisec();*/
			}
			
			
			if(arg0.getSource() == uyeol){
				cluyeol kayit = new cluyeol();
				kayit.uyekaydi();
			}
		}
	}
}
