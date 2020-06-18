import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class GameRunner
{
	/** Dimensions of frame */
	private static int FRAME_LESS = 10;
    
   public static void main(String[] args) 
   {
		JFrame frame = new JFrame();
		frame.setSize(getScreenSize());
		frame.setUndecorated(true);
		frame.setTitle("Zombified");
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		TitlePage title = new TitlePage();
		frame.add(title);
		frame.setVisible(true);	   
   }	
   
   public static Dimension getScreenSize()
   {
	   return Toolkit.getDefaultToolkit().getScreenSize();
   }
   

}
