import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class hands {
	private players p;
	private ArrayList<playcards> masabesli;
	
	int[] royalorstraightflush(){
		int[] dondur = new int[4]; //birincisi el deðeri, ikincisi en yüksek kartý(iki tane ayný el çýkarsa diye), diðeri baþka eller için.
		int i,j,tursayac=0,siralisayac=0;
		ArrayList<playcards> turler = new ArrayList<playcards>();
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		
		for(i=1;i<4;i++)
			if(masabesli.get(0)._tur.equals(masabesli.get(i)._tur))
				tursayac++;
		
		for(i=0;i<4;i++)
			if(masabesli.get(i)._degeri- masabesli.get(i+1)._degeri==1){
				siralidizi.add(masabesli.get(i)._degeri);
				siralisayac ++;
			}
		if(siralisayac == 5 && tursayac==4){
			siralidizi.add(masabesli.get(4)._degeri);
			Collections.sort(siralidizi);
			if(siralidizi.get(4)==13){ //sýralý ve en büyük kart AS ise
				dondur[0] = 10; //yerdeki 5 kart, royalflush
				return dondur;
			}
		}
		
		tursayac=0;
		siralisayac=0;
		turler.clear();
		siralidizi.clear();
		if (p.oyuncukart.get(0)._tur.equals(p.oyuncukart.get(1)._tur)){
			for(i=0;i<5;i++){
				if(p.oyuncukart.get(0)._tur.equals(masabesli.get(i))){
					playcards temp = masabesli.get(i);
					tursayac++;
					turler.add(temp);
				}
			}
			if(tursayac == 3){
				for(j=0;j<2;j++)
					siralidizi.add(p.oyuncukart.get(j)._degeri);
				for(i=0;i<3;i++){
					siralidizi.add(turler.get(i)._degeri);
				}
				Collections.sort(siralidizi);
				for(i=0;i<3;i++){
					if(siralidizi.get(i)-siralidizi.get(i+1)==1)
						siralisayac++;
				}
				if (siralisayac == 3){
					if(siralidizi.get(3)==13){
						dondur[0]=10;
						return dondur; //royalflush oldu
					}
					else{ //straight flush oldu
						dondur[0]=9;
						dondur[1]=siralidizi.get(3); //eðer 2 tane straight flush olursa, kazananý belirleyecek
						return dondur; 
					}
				}
			}
		}
		
		tursayac=0;
		siralisayac=0;
		turler.clear();
		siralidizi.clear();
		if (!(p.oyuncukart.get(0)._tur.equals(p.oyuncukart.get(0)._tur))){ //oyuncunun kartlarý birbirinden farklýysa
			for(j=0;j<2;j++){ // farklý olan iki kart için de türlerini deneyecek
				for(i=0;i<5;i++){
					if(p.oyuncukart.get(j)._tur.equals(masabesli.get(i)._tur)){
						playcards temp = masabesli.get(i);
						tursayac++;
						turler.add(temp);
					}
				}
				if(tursayac == 4){
					siralidizi.add(p.oyuncukart.get(j)._degeri);
					for(i=0;i<4;i++){
						siralidizi.add(turler.get(i)._degeri);
					}
					Collections.sort(siralidizi);
					for(i=0;i<4;i++){
						if(siralidizi.get(i)-siralidizi.get(i+1)==1)
							siralisayac++;
					}
					if (siralisayac == 4){
						if(siralidizi.get(3)==13){
							dondur[0]=10;
							return dondur; //royalflush oldu
						}
						else{ //straight flush oldu
							dondur[0]=9;
							dondur[1]=siralidizi.get(3); //eðer 2 tane straight flush olursa, kazananý belirleyecek
							return dondur; 
						}
					}
				}
			}
		}
		
		return dondur;
	}
	
	int[] fourofakind(){
		int[] dondur = new int[4];
		int i,j,k,sayac=0;
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		
		siralidizi.add(p.oyuncukart.get(0)._degeri);
		siralidizi.add(p.oyuncukart.get(1)._degeri);
		for(i=0;i<5;i++)
			siralidizi.add(masabesli.get(i)._degeri);
		Collections.sort(siralidizi);
		if(p.oyuncukart.get(0)==p.oyuncukart.get(1)){
			for(i=0;i<5;i++){
				if(p.oyuncukart.get(0)._degeri==masabesli.get(i)._degeri)
					sayac++;
			}
			if(sayac==2){
				dondur[0]=8;
				dondur[1]=p.oyuncukart.get(0)._degeri;
				for(k=7;k<0;k--){
					if(siralidizi.get(k)!=p.oyuncukart.get(0)._degeri){ //en büyük deðer 4lü olabilir.
						dondur[2] = siralidizi.get(k);
						break;
					}
				}
			}
			return dondur;
		}
		
		sayac=0;
		if(p.oyuncukart.get(0)!=p.oyuncukart.get(1)){
			for(j=0;j<2;j++){
				for(i=0;i<5;i++){
					if(p.oyuncukart.get(j)._degeri==masabesli.get(i)._degeri)
						sayac++;
				}
				if(sayac==3){
					dondur[0]=8;
					dondur[1]=p.oyuncukart.get(j)._degeri;
					for(k=7;k<0;k--){
						if(siralidizi.get(k)!=p.oyuncukart.get(j)._degeri){
							dondur[2] = siralidizi.get(k);
							break;
						}
					}
				}
			}
			return dondur;
		}
		return dondur;
	}

	int[] fullhouse(){
		int[] dondur = new int[4];
		int[] ikitane = new int[2]; //iki tane üçlü kart için açtýðýmýz dizi.
		int i,j,sayac=0,sayac2=0;
		ArrayList<playcards> butunkartlar = new ArrayList<playcards>();
		for(i=0;i<2;i++)
			butunkartlar.add(p.oyuncukart.get(i));
		for(i=0;i<5;i++)
			butunkartlar.add(p.oyuncukart.get(i));
		/* bütün kartlarý tek dizide topladýk, iki tane üçlü kart olabilir. AAA 5 KKK gibi. o zaman en iyi el AAAKK
		 olur. KKKAA bulursa yanlýþ olur. */
		 
		for(i=0;i<5;i++){
			for(j=i+1;j<7;j++)
				if(butunkartlar.get(i)._degeri == butunkartlar.get(j)._degeri)
					sayac++;
			if(sayac==3){
				ikitane[sayac2] = butunkartlar.get(i)._degeri;
				sayac2++;
			}
			sayac=0;
		}
		if(sayac2==2){
			Arrays.sort(ikitane);
			dondur[0]=7;
			dondur[1]=ikitane[1]; //üçlünün deðeri
			dondur[2]=ikitane[0]; //ikilinin deðeri
			return dondur;
		}
		
		if(sayac2==1){//sayaç bir ise yerde bir üçlü var(mesela 444), ve iki tane de baþka ayný kart varsa(55)fullhouse olur.
			sayac=sayac2=0;
			dondur[1]=ikitane[0]; //üçlünün deðeri
			for(i=0;i<7;i++)
				if(ikitane[0]==butunkartlar.get(i)._degeri)
					butunkartlar.remove(i);
			
			for(i=0;i<3;i++){
				for(j=i+1;j<4;j++)
					if(butunkartlar.get(i)._degeri == butunkartlar.get(j)._degeri)
						sayac++;
				if(sayac==2){
					ikitane[sayac2] = butunkartlar.get(i)._degeri;
					sayac2++;
				}
			}
			
			if(sayac2==2){
				Arrays.sort(ikitane);
				dondur[0]=7;
				dondur[2]=ikitane[1]; //ikilinin deðeri
				return dondur;
			}
			if(sayac2==1){
				dondur[0]=7;
				dondur[2]=ikitane[0]; //ikilinin deðeri
				return dondur;
			}
			
		}
		return dondur;
	}

	int[] flush(){
		int[] dondur = new int[4];
		int i,j,sayac=0;
		ArrayList<playcards> butunkartlar = new ArrayList<playcards>();
		ArrayList<Integer> flushbesli = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			butunkartlar.add(p.oyuncukart.get(i));
		for(i=0;i<5;i++)
			butunkartlar.add(p.oyuncukart.get(i));
		for(i=0;i<3;i++){
			for(j=i+1;j<7;j++)
				flushbesli.add(butunkartlar.get(i)._degeri);
				if(butunkartlar.get(i)._tur.equals(butunkartlar.get(j)._tur)){
					flushbesli.add(butunkartlar.get(j)._degeri);
					sayac++;
				}
			if(sayac>4){
				Collections.sort(flushbesli);
				dondur[0]=6;
				dondur[1]=flushbesli.get(sayac);
				return dondur;
			}
			flushbesli.clear();
			sayac=0;
		}
		return dondur;
	}

	int[] straight (){
		int[] dondur = new int[4];
		int i,siralisayac=0;
		ArrayList<Integer> butundegerler = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			butundegerler.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<5;i++)
			butundegerler.add(p.oyuncukart.get(i)._degeri);
		Collections.sort(butundegerler);
		
		for(i=0;i<6;i++){
			if((butundegerler.get(i)-butundegerler.get(i+1))==1){
				siralisayac++;
			}
			if(siralisayac==4){
				dondur[0]=5;
				if(i==3){
					if((butundegerler.get(i+1)-butundegerler.get(i+2))==1){
						if((butundegerler.get(i+2)-butundegerler.get(i+3))==1){
							dondur[1] = butundegerler.get(6);
							return dondur;
						}
						dondur[1] = butundegerler.get(5);
						return dondur;
					}
					dondur[1] = butundegerler.get(4);
					return dondur;
				}
				if(i==4){
					if((butundegerler.get(i+1)-butundegerler.get(i+2))==1){
						dondur[1] = butundegerler.get(6);
						return dondur;
					}
				}
				dondur[1] = butundegerler.get(4);
				return dondur;
			}
			else{
				butundegerler.remove(i);
				siralisayac=0;
			}
		}
		return dondur;
	}

	int[] threeofakind(){
		int[] dondur = new int[4];
		int deger = 0,i,j,sayac=0;
		boolean tf=false;
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			siralidizi.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<5;i++)
			siralidizi.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<5;i++){
			for(j=i+1;j<7;j++)
				if(siralidizi.get(i) == siralidizi.get(j))
					sayac++;
			if(sayac==3){
				deger = siralidizi.get(i);
				tf=true;
				break;
			}
			sayac=0;
		}
		Collections.sort(siralidizi);
		
		if(tf){
			dondur[0]=4;
			dondur[1]=deger; //üçlünün deðeri
			for(i=0;i<7;i++){
				if(siralidizi.get(i)!= deger){
					dondur[2]=siralidizi.get(i); //en büyüðün deðeri
					break;
				}
			}
			return dondur;
		}
		
		return dondur;
	}

	int[] oneortwopair (){
		int[] dondur = new int[4];
		int i,j,sayac=0,persayisi=0;
		int[] ikiliper = new int[2];
		ArrayList<Integer> pairdizi = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			pairdizi.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<5;i++)
			pairdizi.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<6;i++){
			for(j=i+1;j<7;j++){
				if(pairdizi.get(i)==pairdizi.get(j)){
					sayac++;
					break;
				}
			}
			if(sayac==2){
				ikiliper[persayisi]= pairdizi.get(i);
				persayisi++;
			}
			if(persayisi==2)
				break;
		}
		Collections.sort(pairdizi);
		if(persayisi==2){
			dondur[0]=3;
			Arrays.sort(ikiliper);
			dondur[1] = ikiliper[1];
			dondur[2] = ikiliper[0];
			for(i=6;i<=0;i--){
				if(pairdizi.get(i)!=ikiliper[0] && pairdizi.get(i)!=ikiliper[1]){
					dondur[3] = pairdizi.get(i);
					return dondur;
				}
			}
		}
		if(persayisi==1){
			dondur[0]=2;
			dondur[1] = ikiliper[0];
			for(i=6;i<=0;i--){
				if(pairdizi.get(i)!=ikiliper[0]){
					dondur[2] = pairdizi.get(i);
					return dondur;
				}
			}
		}
		return dondur;
	}

	int[] highcard(){
		int[] dondur = new int[4];
		int i;
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			siralidizi.add(p.oyuncukart.get(i)._degeri);
		for(i=0;i<5;i++)
			siralidizi.add(p.oyuncukart.get(i)._degeri);
		Collections.sort(siralidizi);
		dondur[0]=1;
		dondur[1]=siralidizi.get(6);
		return dondur;
	}
	
	int[] kontrolEt(players p,ArrayList<playcards> masabesli){
		int [] eldegerleri = new int[4];
		this.p = p;
		this.masabesli = masabesli;
		eldegerleri = royalorstraightflush();
		if(eldegerleri[0]==10 || eldegerleri[0]==9)
			return eldegerleri;
		eldegerleri = fourofakind();
		if(eldegerleri[0]==8)
			return eldegerleri;
		eldegerleri = fullhouse();
		if(eldegerleri[0]==7)
			return eldegerleri;
		eldegerleri = flush();
		if(eldegerleri[0]==6)
			return eldegerleri;
		eldegerleri = straight();
		if(eldegerleri[0]==5)
			return eldegerleri;
		eldegerleri = threeofakind();
		if(eldegerleri[0]==4)
			return eldegerleri;
		eldegerleri = oneortwopair();
		if(eldegerleri[0]==3 || eldegerleri[0]==2)
			return eldegerleri;
		eldegerleri = highcard();
		if(eldegerleri[0]==1)
			return eldegerleri;
		return eldegerleri;
	}
}
