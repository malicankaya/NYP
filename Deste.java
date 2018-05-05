import java.util.ArrayList;


public class Deste {
	ArrayList<playcards> desteolustu = new ArrayList<playcards>();
	int i;
	Deste(){
		for(i=1;i<14;i++){
			playcards gecici=new playcards("maca",i,i);
			desteolustu.add(gecici);
			
		}
		for(i=14;i<27;i++){
			playcards gecici=new playcards("sinek",i-13,i);
			desteolustu.add(gecici);
		}
		for(i=27;i<40;i++){
			playcards gecici=new playcards("karo",i-26,i);
			desteolustu.add(gecici);
		}
		for(i=40;i<53;i++){
			playcards gecici=new playcards("kupa",i-39,i);
			desteolustu.add(gecici);
		}
		
	}
}
