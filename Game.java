import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game implements ActionListener {
	ArrayList<players> oyuncular = new ArrayList<players>();
	players p = new players();
	int i,j,x,y;
	ArrayList<playcards> yerkartlari=new ArrayList<playcards>();
	Jspecs oyunpenceresi = new Jspecs();
	Deste kartlar = new Deste();
	BufferedImage image;
	String resimkonum;
	JLabel masadegerilabel;
	JLabel potlabel;
	JButton gor;
	JButton pas;
	JButton cekil;
	int cizilmiskartsayac;
	int bahisdongusu = 0 ;
	int[] oyuncuparalari;
	int pot;
	int globalmasadegeri;
	ResimCizdirme imgdraw;
	ArrayList<JPanel> cizilmiskartlar;
	void oyunabasla(int masadegeri){
		oyuncuparalari = new int[5];
		globalmasadegeri = masadegeri;
		for(i=0;i<5;i++){
			oyuncuparalari[i] = masadegeri * 100;
		}
		gor = new JButton("GÖR");
		pas = new JButton("PAS");
		cekil = new JButton("ÇEKÝL");
		masadegerilabel = new JLabel(""+masadegeri+"$/"+masadegeri*2+"$");
		potlabel = new JLabel(""+masadegeri*9+"$");
		Collections.shuffle(kartlar.desteolustu);
		for(i=0;i<5;i++){
			p.oyuncunumarasi = i;
			for(j=0;j<2;j++){
				p.oyuncukart.add(kartlar.desteolustu.get(i));
				kartlar.desteolustu.remove(i);
			}
			oyuncular.add(p);
			p = new players();
		}
		
		for(i=0;i<5;i++){
			yerkartlari.add(kartlar.desteolustu.get(i));
			kartlar.desteolustu.remove(i);
		}
		oyunpenceresi.getContentPane().setLayout(null);
		oyunpenceresi.setVisible(true);
		masadegerilabel.setFont(new Font("Serif", Font.ITALIC, 25));
		masadegerilabel.setForeground(Color.WHITE);
		oyunpenceresi.add(masadegerilabel);
		masadegerilabel.setBounds(new Rectangle(885,330,300,50));
		potlabel.setFont(new Font("Serif", Font.ITALIC, 25));
		potlabel.setForeground(Color.WHITE);
		oyunpenceresi.add(potlabel);
		potlabel.setBounds(new Rectangle(915,230,300,50));
		gor.setBounds(new Rectangle(1200,950,60,25));
		pas.setBounds(new Rectangle(1260,950,60,25));
		cekil.setBounds(new Rectangle(1320,950,70,25));
		oyunpenceresi.add(pas);
		oyunpenceresi.add(cekil);
		oyunpenceresi.add(gor);
		imgdraw = new ResimCizdirme();
		JPanel carddealer = new JPanel();
		carddealer = imgdraw.Cizdir("C:\\Users\\Mali\\Desktop\\Deck\\carddealer.png");
		carddealer.setBounds(new Rectangle(815,20,270,200));
		oyunpenceresi.add(carddealer);
		imgdraw = new ResimCizdirme();
		cizilmiskartlar = new ArrayList<JPanel>();
		cizilmiskartsayac=0;
		x=822;
		for(i=0;i<2;i++){
			resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\"+oyuncular.get(0).oyuncukart.get(i)._sayisi+".png";
			cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
			cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,800,119,175));
			oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
			imgdraw = new ResimCizdirme();
			x+=120;
		}
		x=50;
		y=600;
		for(i=0;i<2;i++){
			for(j=0;j<2;j++){
				resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\back.png";
				cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
				cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
				oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
				imgdraw = new ResimCizdirme();
				x+=120;
			}
			x=1600;
		}
		x=250;
		y=50;
		for(i=0;i<2;i++){
			for(j=0;j<2;j++){
				resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\back.png";
				cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
				cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
				oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
				imgdraw = new ResimCizdirme();
				x+=120;
			}
			x=1400;
		}
		y=380;
		x=650;
		for(i=0;i<5;i++){
			resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\back.png";
			cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
			cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
			oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
			imgdraw = new ResimCizdirme();
			x+=120;
		}
		oyuncuparalari[0] = oyuncuparalari[0] - masadegeri;
		pot +=masadegeri;
		for(i=1;i<5;i++){
			oyuncuparalari[i] -= masadegeri *2;
			pot +=masadegeri*2;
		}
		gor.addActionListener(this);
		pas.addActionListener(this);
		cekil.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gor){
			if(bahisdongusu == 0){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
	       		oyuncuparalari[0] -=globalmasadegeri;
	       		pot +=globalmasadegeri;
	       		potlabel.setText(""+pot+"$");
	       		y=380;
	       		x=650;
	       		for(i=10;i<13;i++){//masadaki ilk 3 kartýn indexi
	       			oyunpenceresi.remove(cizilmiskartlar.get(i));
	       			resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\"+yerkartlari.get(i-10)._sayisi+".png";
	       			cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
	       			cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
	       			oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
	       			imgdraw = new ResimCizdirme();
	       			x+=120;
	       		}
		}
			else if(bahisdongusu < 3){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
				for(int i=0;i<5;i++){
					oyuncuparalari[i] -=globalmasadegeri*2;
					pot +=globalmasadegeri*2;
				}
				potlabel.setText(""+pot+"$");
				oyunpenceresi.remove(cizilmiskartlar.get(12+bahisdongusu));
	   			resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\"+yerkartlari.get(2+bahisdongusu)._sayisi+".png";
	   			cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
	   			cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
	   			oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
	   			imgdraw = new ResimCizdirme();
	   			x+=120;
	   			oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
       	 	}
			else if (bahisdongusu == 3){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
				x=50;
				y=600;
				for(i=0;i<2;i++){
					for(j=0;j<2;j++){
						oyunpenceresi.remove(cizilmiskartlar.get(2+(i*2+j)));
						resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\"+oyuncular.get((i*3)+1).oyuncukart.get(j)._sayisi+".png";
						cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
						cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
						oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
						imgdraw = new ResimCizdirme();
						x+=120;
					}
					x=1600;
				}
				x=250;
				y=50;
				for(i=0;i<2;i++){
					for(j=0;j<2;j++){
						oyunpenceresi.remove(cizilmiskartlar.get(6+(i*2+j)));
						resimkonum = "C:\\Users\\Mali\\Desktop\\Deck\\"+oyuncular.get(i+2).oyuncukart.get(j)._sayisi+".png";
						cizilmiskartlar.add(imgdraw.Cizdir(resimkonum));
						cizilmiskartlar.get(cizilmiskartsayac).setBounds(new Rectangle(x,y,119,175));
						oyunpenceresi.add(cizilmiskartlar.get(cizilmiskartsayac++));
						imgdraw = new ResimCizdirme();
						x+=120;
					}
					x=1400;
				}
				bahisdongusu++;
			}
			
			else if(bahisdongusu == 4){
				int i,indis = 0,esitlikindis = 0;
				int j=-1;
				int [][] eldegeriarray = new int[5][4];
				hands findwinner = new hands();
				for(i=0;i<5;i++){
					eldegeriarray[i] = findwinner.kontrolEt(oyuncular.get(i), yerkartlari);
					findwinner = new hands();
				}
				for(i=0;i<5;i++){
					if(eldegeriarray[i][0] == j)
						esitlikindis = i;
					if(eldegeriarray[i][0]>j){
						j=eldegeriarray[i][0];
						indis=i;
					}
				}

				if(eldegeriarray[0][1] == -1 ){
					String mesaj = "Kazanan oyuncu"+oyuncular.get(indis).oyuncunumarasi+"";
					JOptionPane.showMessageDialog(null, mesaj,"TEBRÝKLER",0);
				}
					
			}
			bahisdongusu++;
		}
	}
			
}