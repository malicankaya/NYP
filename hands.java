import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class hands {
	private players p;
	private ArrayList<playcards> masabesli;
	
	int[] royalorstraightflush(){
		int[] dondur = new int[4]; //birincisi el deðeri, ikincisi en yüksek kartý(iki tane ayný el çýkarsa diye), diðeri baþka eller için.
		int i,j,tursayac=0,siralisayac=0;
		ArrayList<playcards> tumkartlar = new ArrayList<playcards>();
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		ArrayList<Integer> straightsiralidizi = new ArrayList<Integer>();
		for(i=0;i<2;i++)
			tumkartlar.add(p.oyuncukart.get(i));
		for(i=0;i<5;i++)
			tumkartlar.add(masabesli.get(i));
		for(i=0;i<3;i++){
			siralidizi.add(tumkartlar.get(i)._sayisi);
			for(j=i+1;j<7;j++)
				if(tumkartlar.get(i)._tur.equals(tumkartlar.get(j)._tur)){
					tursayac++;
					siralidizi.add(tumkartlar.get(j)._degeri);
				}
			if(i==0 && tursayac>=4)
				break;
			if(i==1 && tursayac>=4)
				break;
			if(i==1 && tursayac>=4)
				break;
			tursayac=0;
			siralidizi.clear();
		}
		
		if(tursayac>=4){
			Collections.sort(siralidizi);
			straightsiralidizi.add(siralidizi.get(0));
			for(i=0;i<siralidizi.size()-1;i++){
				if(siralidizi.get(i)- siralidizi.get(i+1)==-1){ 
					straightsiralidizi.add(i+1);
					siralisayac ++;
				}
				else if(siralisayac <4){
					straightsiralidizi.clear();
					siralisayac=0;
					straightsiralidizi.add(siralidizi.get(i+1));
				}
				else//siralisayac 4 oldu ve if'e girmediyse artýk sýralý elimizi aldýk demektir.
					break;
			}
				
			if(siralisayac >=4){
				if(straightsiralidizi.get(straightsiralidizi.size()-1)==13){ //sýralý ve en büyük kart AS ise
					dondur[0] = 10;
					return dondur;
				}
				else{
					dondur[0]=9;//straight flush oldu
					dondur[1]=straightsiralidizi.get(straightsiralidizi.size()-1);
					return dondur;
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
		for(i=0;i<4;i++){
			for(j=i+1;j<7;j++)
				if(siralidizi.get(i) == siralidizi.get(j)){
					sayac++;
				}
				if(sayac ==3){
					dondur[0]=8;
					for(k=6;k<-1;i++)
						if(siralidizi.get(i)!=siralidizi.get(k)){
							dondur[1]=siralidizi.get(k);
							return dondur;
						}
				}
				else
					sayac=0;
		}
		return dondur;
	}

	int[] fullhouse(){
		int[] dondur = new int[4];
		int i,j,k = 0,sayac=0;
		ArrayList<Integer> siralidizi = new ArrayList<Integer>();
		
		siralidizi.add(p.oyuncukart.get(0)._degeri);
		siralidizi.add(p.oyuncukart.get(1)._degeri);
		for(i=0;i<5;i++)
			siralidizi.add(masabesli.get(i)._degeri);
		Collections.sort(siralidizi);
		for(i=0;i<4;i++){
			for(j=i+1;j<7;j++){
				if(sayac ==2){
					k=siralidizi.get(i);
					break;
				}
				if(siralidizi.get(i) == siralidizi.get(j))
					sayac++;
			}
			if(sayac==2)
				break;
		}
		sayac=0;
		for(i=0;i<4;i++){
			for(j=i+1;j<7;j++){
				if(sayac ==1 && k !=siralidizi.get(i)){
					dondur[0]=7;
					dondur[1]=k;
					dondur[2]=siralidizi.get(i);
					return dondur;
				}
				if(siralidizi.get(i) == siralidizi.get(j))
					sayac++;
				else
					sayac=0;
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
			butunkartlar.add(masabesli.get(i));
		for(i=0;i<3;i++){
			for(j=i+1;j<7;j++){
				flushbesli.add(butunkartlar.get(i)._degeri);
				if(butunkartlar.get(i)._tur.equals(butunkartlar.get(j)._tur)){
					flushbesli.add(butunkartlar.get(j)._degeri);
					sayac++;
				}
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
			butundegerler.add(masabesli.get(i)._degeri);
		
		Collections.sort(butundegerler);
		
		for(i=0;i<butundegerler.size()-1;i++){
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
			siralidizi.add(masabesli.get(i)._degeri);
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
			pairdizi.add(masabesli.get(i)._degeri);
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
			siralidizi.add(masabesli.get(i)._degeri);
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
		else{
			eldegerleri[0] = -1;
		}
		return eldegerleri;
	}
}
