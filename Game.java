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
	ArrayList<JLabel> oyuncuparalarlabel = new ArrayList<JLabel>();
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
		for(i=0;i<5;i++)
			oyuncuparalari[i] = masadegeri * 100;
		gor = new JButton("GÖR");
		pas = new JButton("PAS");
		cekil = new JButton("ÇEKÝL");
		masadegerilabel = new JLabel(""+masadegeri+"$/"+masadegeri*2+"$");
		potlabel = new JLabel(""+masadegeri*9+"$");
		Collections.shuffle(kartlar.desteolustu);
		for(i=0;i<5;i++){
			p.oyuncunumarasi = i+1;
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
		Font jlabelfont = new Font("Serif", Font.ITALIC, 21);
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
		for(i=0;i<5;i++){
			oyuncuparalarlabel.add(new JLabel());
			oyuncuparalarlabel.get(i).setFont(jlabelfont);
		}
		oyuncuparalarlabel.get(0).setBounds(new Rectangle(900,970,300,50));
		oyuncuparalarlabel.get(0).setForeground(Color.WHITE);
		oyunpenceresi.add(oyuncuparalarlabel.get(0));
		
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
		oyuncuparalarlabel.get(0).setText(""+oyuncuparalari[0]+"$");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gor){
			if(bahisdongusu == 0 && oyuncuparalari[0]>globalmasadegeri){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
	       		oyuncuparalari[0] -=globalmasadegeri;
	       		pot +=globalmasadegeri;
	       		potlabel.setText(""+pot+"$");
	       		oyuncuparalarlabel.get(0).setText(""+oyuncuparalari[0]+"$");
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
			else if(bahisdongusu < 3 && oyuncuparalari[0]>globalmasadegeri*2){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
				for(int i=0;i<5;i++){
					oyuncuparalari[i] -=globalmasadegeri*2;
					pot +=globalmasadegeri*2;
				}
				potlabel.setText(""+pot+"$");
				oyuncuparalarlabel.get(0).setText(""+oyuncuparalari[0]+"$");
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
			else if (bahisdongusu == 3 && oyuncuparalari[0]>globalmasadegeri*2){
				oyunpenceresi.setVisible(false);
				oyunpenceresi.setVisible(true);
				for(int i=0;i<5;i++){
					oyuncuparalari[i] -=globalmasadegeri*2;
					pot +=globalmasadegeri*2;
				}
				potlabel.setText(""+pot+"$");
				oyuncuparalarlabel.get(0).setText(""+oyuncuparalari[0]+"$");
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
			
				if(bahisdongusu == 4){
				int i,indis = 0,esitlikindis = 0;
				int j=-1;
				int [][] eldegeriarray = new int[5][4];
				hands findwinner = new hands();
				for(i=0;i<5;i++){
					eldegeriarray[i] = findwinner.kontrolEt(oyuncular.get(i), yerkartlari);
					findwinner = new hands();
				}	
				for(i=0;i<5;i++){
					if(eldegeriarray[i][0]>j){
						j=eldegeriarray[i][0];
						indis=i;
					}
				}
				for(i=0;i<5;i++){
					if(eldegeriarray[i][0]==j && i!=indis)
						esitlikindis = i;
				}
				//if(esitlikindis == 0 ){//el deðerleri eþitse, kartlara bakar
					String[] elisimleri ={"","1.)HighCard","2.)OnePair","3.)TwoPair",
										  "4.)ThreeofaKind","5.)Straight","6.)Flush",
										  "7.)FullHouse","8.)FourofaKind","9.)StraightFlush","10.)RoyalFlush"};
					String[] uyarimesaj = {"Oyuna Devam","Tamam"};
					
					String mesaj = "Kazanan oyuncu "+oyuncular.get(indis).oyuncunumarasi+
									". El büyüklüðü "+elisimleri[eldegeriarray[indis][0]]+"";
					
					int confirmed = JOptionPane.showOptionDialog(null,mesaj,"TEBRÝKLER", 0,
																JOptionPane.INFORMATION_MESSAGE,null,uyarimesaj,null);
					oyuncuparalari[indis]+=pot;
					pot=0;
					oyuncuparalarlabel.get(0).setText(""+oyuncuparalari[0]+"$");
					potlabel.setText(""+pot+"$");
					if(confirmed==JOptionPane.YES_OPTION){
						
					}
					
				//}
			}
			bahisdongusu++;
		}
		
		if(e.getSource() == pas){
			
		}
	}
			
}