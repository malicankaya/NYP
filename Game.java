import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;


public class Game {
	ArrayList<players> oyuncular = new ArrayList<players>();
	players p = new players();
	int i,j,x,y;
	ArrayList<playcards> yerkartlari=new ArrayList<playcards>();
	Jspecs oyunpenceresi = new Jspecs();
	Deste kartlar = new Deste();
	BufferedImage image;
	String resimkonum;
	
	void oyunabasla(int masadegeri){
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
		ResimCizdirme imgdraw = new ResimCizdirme();
		ArrayList<JPanel> cizilmiskartlar = new ArrayList<JPanel>();
		int cizilmiskartsayac=0;
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
		
	}
	
}
