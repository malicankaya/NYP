import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



public class MasaSecimi { //ikinci bir actionhandler gerektiði için ayrý class içinde masa seçimini yaptýk. panelspecs içinde olmadý
	
	JButton iki_dort,bes_bin,bin_ikibin,besbin_onbin;
	Jspecs oyunalani = new Jspecs();
	
	void masayisec(){
		
		iki_dort = new JButton();
		bes_bin = new JButton();
		bin_ikibin = new JButton();
		besbin_onbin = new JButton();
		oyunalani.getContentPane().setBackground(Color.decode("#2E2E2E"));
		oyunalani.setVisible(true);
		Font yaziboyutu = new Font("Arial", Font.PLAIN, 30);
		iki_dort.setFont(yaziboyutu);
		bes_bin.setFont(yaziboyutu);
		bin_ikibin.setFont(yaziboyutu);
		besbin_onbin.setFont(yaziboyutu);
		iki_dort.setText("200$-400$");
		bes_bin.setText("500$-1000$");
		bin_ikibin.setText("1000$-2000$");
		besbin_onbin.setText("5000$-10000$");
		oyunalani.add(iki_dort);
		oyunalani.add(bes_bin);
		oyunalani.add(bin_ikibin);
		oyunalani.add(besbin_onbin);
		Dimension olcu = new Dimension(250,400);
		iki_dort.setPreferredSize(olcu);
		bes_bin.setPreferredSize(olcu);
		bin_ikibin.setPreferredSize(olcu);
		besbin_onbin.setPreferredSize(olcu);
		Tutucu hangimasa = new Tutucu();
		iki_dort.addActionListener(hangimasa);
		bes_bin.addActionListener(hangimasa);
		bin_ikibin.addActionListener(hangimasa);
		besbin_onbin.addActionListener(hangimasa);
	}
	private class Tutucu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			Game masasecildi = new Game();
			if (arg0.getSource()==iki_dort)
				masasecildi.oyunabasla(200);
			if (arg0.getSource()==bes_bin)
				masasecildi.oyunabasla(500);
			if (arg0.getSource()==bin_ikibin)
				masasecildi.oyunabasla(1000);
			if (arg0.getSource()==besbin_onbin)
				masasecildi.oyunabasla(5000);
			
		}
	}
}
