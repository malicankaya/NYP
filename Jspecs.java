import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Jspecs extends JFrame {//frame'in bütün özellikleri
	
		public Jspecs(){
			
			super("JollyPoker");
			setLayout(new GridBagLayout());
			addWindowListener(new WindowHandler());
			setIconImage(new ImageIcon("D:\\Downloads\\indir.jpg").getImage());
			getContentPane().setBackground(Color.decode("#800000")); 
			setSize(1024,768); // minimize ekranýn boyutunu ayarlýyor.
			setExtendedState(JFrame.MAXIMIZED_BOTH); //tam ekran yapýyor
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		private class WindowHandler implements WindowListener{
			public void windowOpened(WindowEvent e){
				
			}
			public void windowClosing(WindowEvent e) {
				String evethayir[] = new String[2];
				evethayir[0] = "Evet";
				evethayir[1] = "Hayýr";
				int confirmed = JOptionPane.showOptionDialog(getContentPane(),
						"Çýkmak istediðinize emin misiniz","UYARI", 0,
						JOptionPane.INFORMATION_MESSAGE,null,evethayir,null);

				if (confirmed == JOptionPane.YES_OPTION)
					dispose();
				}
				

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
}
