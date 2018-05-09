import java.util.ArrayList;


public class proje1 {
	
	
	public static void main(String[] args) {
		String s = "sinek";
		String k = "karo";
		/*Jspecs frame = new Jspecs();
		frame.add(new panelspecs());
	    frame.setVisible(true);*/
		ArrayList<playcards> besli = new ArrayList<playcards>();
		players p = new players();
		playcards kart  = new playcards(s, 2, 12);
		p.oyuncukart.add(kart);
		kart  = new playcards(k, 4, 13);
		p.oyuncukart.add(kart);
		kart  = new playcards(k, 6, 7);
		besli.add(kart);
		kart  = new playcards(s, 8, 78);
		besli.add(kart);
		kart  = new playcards(s, 10, 7);
		besli.add(kart);
		kart  = new playcards(s, 12, 78);
		besli.add(kart);
		kart  = new playcards(s, 13, 78);
		besli.add(kart);
		
		
		hands eller = new hands();
		eller.kontrolEt(p, besli);
	}
}