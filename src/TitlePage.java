import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TitlePage extends JPanel
{
   private int width;
   private int height;
   private static final int LWIDTH = 600;
   private static final int LHEIGHT = 800;
   private static final double LOGOX = 25.0 / 96;
   private static final int LOGOSCALE = 6;
   private static final int FONTSCALE = 31;
   private static final double TEXTONE = 3.0 / 11;
   private static final double TEXTTWO = 7.0 /8;
   private static final double TEXTTHREE = 5;
   private static final double TEXTFOUR  = 3.0 / 4;
   private static final double TEXTFIVE = 7.0 / 22;
   private static final double TEXTSIX = 5.0 /8;	
   private static final double TEXTSEVEN  = 12.0 / 33;
   
   public TitlePage()
   {
	   width = GameRunner.getScreenSize().width;
	   height = GameRunner.getScreenSize().height;
	   addKeyListener(new TAdapter());
	   setFocusable(true);
	   setLayout(null);
	   setBounds(0, 0, width, height);
	   JButton button = new JButton();
	   button.setBounds(0, 0, width, height);
	   button.setOpaque(false);
	   button.setContentAreaFilled(false);
	   button.setBorderPainted(false);
	   button.addActionListener(new ButtonListener());
	   add(button);
   }
   
   @Override
   public void paintComponent(Graphics g) 
   {
		Graphics2D gr2 = (Graphics2D) g;
		gr2.setColor(Color.GREEN);
		Image bg = new ImageIcon("src/img/bg.jpg").getImage();
		gr2.drawImage(bg, 0, 0, width, height, this);
		Image scaled = new ImageIcon("src/img/logo.png").getImage();
		gr2.drawImage(scaled, (int) (width * LOGOX), height / LOGOSCALE, width /2, height / LOGOSCALE, this);
		gr2.setFont(new Font("Courier", Font.BOLD, width / FONTSCALE));
		gr2.setColor(Color.WHITE);
		gr2.drawString("CLICK ANYWHERE TO START", (int) (width * TEXTONE),
				(int) (height * TEXTTWO));
		gr2.drawString("PRESS ENTER TO SEE LEADERBOARD",
				(int) (width / TEXTTHREE), (int) (height * TEXTFOUR));
		gr2.drawString("ARROW KEYS TO MOVE", (int) (width * TEXTFIVE), (int) (height * TEXTSIX));
		gr2.drawString("SPACE TO SHOOT", (int) (width * TEXTSEVEN), height / 2);
	}
   
   class ButtonListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   JFrame frame = new JFrame();
		   frame.setSize(GameRunner.getScreenSize());
		   frame.setUndecorated(true);
		   frame.setTitle("Zombified");
		   frame.setLocation(0, 0);
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setResizable(false);
		   Game screen = new Game();
		   frame.add(screen);
		   frame.setVisible(true);  
	   }
   }
   
   class TAdapter extends KeyAdapter
   {
	   @Override
	   public void keyPressed(KeyEvent e)
	   {
		   if (e.getKeyCode() == KeyEvent.VK_ENTER)
		   {
			   JFrame frame = new JFrame();
			   frame.setSize(LWIDTH, LHEIGHT);
			   frame.setTitle("Leaderboard");
			   frame.setLocation(GameRunner.getScreenSize().width / 2 - LWIDTH / 2,
					   GameRunner.getScreenSize().height / 2 - LHEIGHT / 2);
			   frame.setResizable(false);
			   frame.setFocusable(true);
			   Leaderboard scores = new Leaderboard();
			   frame.add(scores);
			   frame.setVisible(true);
		   }
	   }

   }
}
	   
   


  

    
