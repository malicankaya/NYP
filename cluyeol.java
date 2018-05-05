import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class cluyeol { //�ye olma ekran� ve �ye i�lemleri i�in gerekenler

	private static JTextField jtext1;
	private static JPasswordField jpw1;
	private static JPasswordField jpw2;
	private static JButton uyeol;
	
	void uyekaydi(){
	JFrame frm = new JFrame();
	frm.getContentPane().setBackground(Color.decode("#800000"));
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	frm.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(5,5,5,5);
	c.gridx = 0;
	c.gridy = 0;
	jtext1 = new JTextField("Kullan�c� Ad�",15);
	jtext1.setColumns(20);
	jtext1.setPreferredSize(new Dimension(0,25));
	jtext1.setToolTipText("Kullan�c� ad�n�z� girin");
	frm.add(jtext1,c);
	jpw1 = new JPasswordField("�ifre",15);
	jpw1.setColumns(20);
	jpw1.setPreferredSize(new Dimension(0,25));
	jpw1.setToolTipText("�ifrenizi girin");
	c.gridy = 1;
	frm.add(jpw1,c);
	jpw2 = new JPasswordField("�ifre",15);
	jpw2.setColumns(20);
	jpw2.setPreferredSize(new Dimension(0,25));
	jpw2.setToolTipText("�ifrenizi tekrar girin");
	c.gridy = 2;
	frm.add(jpw2,c);
	c.fill = GridBagConstraints.CENTER;
	uyeol = new JButton("�ye Ol");
	uyeol.setPreferredSize(new Dimension(150,20));
	c.gridy = 3;
	frm.add(uyeol,c);
    int x = (dim.width)/2;
    int y = (dim.height)/4;
    frm.setLocation(x, y);
    frm.setSize((dim.width)/5, (dim.height)/3);
    frm.setVisible(true);
    etkinliktutucu tutucu = new etkinliktutucu();
	uyeol.addActionListener(tutucu);
	}
	
	private static class etkinliktutucu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String sifre1 = jpw1.getText();
			String sifre2 = jpw2.getText();
			if(!(sifre1.equals(sifre2)))
				JOptionPane.showMessageDialog(null, "�ki �ifre de ayn� olmal�d�r.","UYARI",0);
			else{
				String kullaniciadi=jtext1.getText();
				Boolean kadivarmi=false;
				try{
					Connection baglan = (Connection) DBConnection.baglanti();
					PreparedStatement statement = (PreparedStatement) baglan.prepareStatement("SELECT k_adi FROM `uyeler`");
					ResultSet result = statement.executeQuery();
					while(result.next()){
						if(kullaniciadi.equals(result.getString("k_adi"))){
							JOptionPane.showMessageDialog(null, "Bu kullan�c� ad� mevcuttur.","UYARI",0);
							kadivarmi=true;
						}
					}
				}catch(Exception e){}
				if(!kadivarmi){
					try {
						Connection baglan = (Connection) DBConnection.baglanti();
						//try ve catch i�inde istedi�i i�in yukarda bir adet tan�mlay�p iki yerde de kullanam�yoruz
						PreparedStatement statement = (PreparedStatement) baglan.prepareStatement("INSERT INTO uyeler (k_adi,parola) VALUES ('"+kullaniciadi+"','"+sifre1+"')");
						statement.executeUpdate();
						JOptionPane.showMessageDialog(null,"�ye kayd� al�nm��t�r.","TEBR�KLER",1);
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
	}
}