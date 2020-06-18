import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Game extends JComponent implements ActionListener
{
   Player player;
   private static final int ZDELAY = 3000;
   private static final int SCOREM = 500;
   private static final int UPDATE = 50;
   private static final int BOSSUPDATE = 200;
   private static final int SCORES = 10;
   private static final int XONE = 5;
   private static final int XTWO = 11;
   private static final int XTHREE  = 100;
   private static final int XFOUR = 6;
   private static final int XFIVE = 7;
   private static final int Y = 50;
   private static final int YTWO = 20;
   private static final int YTHREE = 50;
   private static final int WIDTH = 12;
   private static final int HEIGHT = 32;
   private static final int FONTSCALE  = 60;
   private static final int FONTTWO = 32;
   private static final int HEALTHBAR = 8;
   private Timer timer;
   private Timer zTimer;
   private int bossNumber;
   private List<Enemy> zombies;
   
   public Game() 
   {
	   	bossNumber = 1;
   		loadGame();
   }
    
   private void loadGame()
   {
	   int width = GameRunner.getScreenSize().width;
	   int height = GameRunner.getScreenSize().height;
	   zombies = new ArrayList<>();
	   addKeyListener(new TAdapter());
	   setFocusable(true);
	   player = new Player (GameRunner.getScreenSize().width/2,
			   GameRunner.getScreenSize().height/2);
	   timer = new Timer(0, this);
	   timer.start();
	   zTimer = new Timer(ZDELAY, new TimerActionListener());
	   zTimer.start();
	   JButton end = new JButton("End Game");
	   end.setBounds(width * XONE / XTWO, height / Y, width * 1 / WIDTH, height / HEIGHT);
	   end.setFont(new Font("Courier", Font.BOLD, width / FONTSCALE));
	   end.setBorder(null);
       end.setBorderPainted(false);
       end.setContentAreaFilled(false);
       end.setOpaque(false);
       end.addActionListener(new ButtonListener());
	   add(end);
	
   }
	@Override
	public void paintComponent(Graphics g) 
	{
		int width = GameRunner.getScreenSize().width;
		int height = GameRunner.getScreenSize().height;
		Graphics2D gr2 = (Graphics2D) g;
		List<Bullet> bullets = player.getBullets();
		Image map = new ImageIcon("src/img/terrain.jpg").getImage();
        gr2.drawImage(map, 0, 0, width, height, this);
		gr2.drawImage(player.getImage(), player.getX(), player.getY(),
				player.getWidth(), player.getHeight(), this);
		for(Enemy enm : zombies)
		{
			gr2.drawImage(enm.getImage(), enm.getX(), enm.getY(),
					enm.getWidth(), enm.getHeight(), this);
		}
		gr2.setFont(new Font("Courier", Font.BOLD, width / FONTTWO));
		gr2.setColor(Color.BLACK);
		gr2.drawString(Integer.toString(player.getScore()), width / XTHREE, height / YTWO);
		gr2.setColor(Color.GREEN);
		int healthScale = (width * 1 / HEALTHBAR) / player.HEALTH;
		gr2.fillRect(width * XFOUR / XFIVE, height/YTHREE,
				player.getHealth() * healthScale, height / HEIGHT);
		for (Bullet bul : bullets)
		{
			gr2.drawImage(bul.getImage(), bul.getX(), bul.getY(),
					bul.getSize(), bul.getSize(), this);
		}	
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		player.move();
		int numBosses = 0;
		for (int i = 0; i < zombies.size(); i++)
		{
			Enemy en = zombies.get(i);
			List<Bullet> bullets = player.getBullets();
			if (en.hit())
			{
				en.moveBack();
				player.updateHealth(en.getDamage());
			}
			for(int j = 0; j < bullets.size(); j++)
			{			
				if(en.isHit(bullets.get(j)))
				{
					en.updateHealth(player.getDamage());
					en.moveBack();
					if (en.getHealth() <= 0)
					{
						if (en instanceof Boss)
							player.updateScore(BOSSUPDATE);
						
						player.updateScore(UPDATE);
						zombies.remove(i);
						i--;
					}
					bullets.remove(j);
					j--;
				}
			}
			en.move();
		}
		runBullets();
		if (player.getScore() / SCOREM == bossNumber)
		{
			zombies.add(new Boss((int)(Math.random() * GameRunner.getScreenSize().width), 0,
					player, Boss.SPEED, 0));
			bossNumber++;
		}
		if (player.getHealth() <= 0)
			endGame();
		repaint();
	}
	
	public void runBullets()
	{
		List<Bullet> bullets = player.getBullets();
		
		for (int i = 0; i < bullets.size(); i++)
		{
			Bullet bullet = bullets.get(i);
			
			if (bullet.isLive())
			{
				bullet.move();
			}
			else 
			{
				bullets.remove(i);
				i--;
			}
		}
	}
	
	public void endGame()
	{
		timer.stop();
		zTimer.stop();
		int finalScore = player.getScore();
		Leaderboard scores = new Leaderboard();
		int index = scores.onLeaderboard(finalScore);
		if (index != - 1)
		{
			String name = JOptionPane.showInputDialog("New high score! Enter your name.");
			if (name != null && name.length() > 0)
				scores.addScore(index, finalScore, name);
		}
		JOptionPane.showMessageDialog(null, "Game Over", "Game Over",
				JOptionPane.OK_OPTION);
		System.exit(0);
	}
	
	class TAdapter extends KeyAdapter {
		@Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
           
        }
    }
   
	class TimerActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			int xy = (int) (Math.random() * 2);
			if (xy == 0)
				zombies.add(new Zombie((int)(Math.random() * GameRunner.getScreenSize().width), 0,
						player, Zombie.SPEED, 0));
			else
				zombies.add(new Zombie(0,(int)(Math.random() * GameRunner.getScreenSize().height),
						player, Zombie.SPEED, 0));
		}
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			endGame();
			
		}
	}

}