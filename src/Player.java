import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class Player 
{
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image sprite;
	private int health;
	private int damage;
	private String fileName;
	private int score;
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean spaceReleased;
	private String lastDirection;
	private List<Bullet> bullets;

	private static final int SPEED = GameRunner.getScreenSize().width / 360;
	private static final int WIDTH = 24;
	public static final int HEALTH= 100;
	private static final int DAMAGE = 50;
	
   public Player(int x, int y) 
   {
	   score = 0;
	   this.x = x;
	   this.y = y;
	   damage = DAMAGE;
	   health = HEALTH;
	   bullets = new ArrayList<>();
	   loadImage("src/img/player.gif");
	   lastDirection = "right";
   }
   
   private void loadImage(String fileName)
   {
	   sprite = new ImageIcon(fileName).getImage();
	   width = height = GameRunner.getScreenSize().width / WIDTH;
   }

   public void shoot()
   {
	   	if (lastDirection == "left")
	   		bullets.add(new Bullet(getX(), getY() + height * 1 / 5, getDirection()));
	   	if (lastDirection == "right")
	   		bullets.add(new Bullet(getX() + width, getY() + height * 2 / 3, getDirection()));
	   	if (lastDirection == "up")
	   		bullets.add(new Bullet(getX() + width * 2 / 3, getY(), getDirection()));
	   	if (lastDirection == "down")
	   		bullets.add(new Bullet(getX() + width * 1/ 5, getY() + height, getDirection()));
   }
   
   public List<Bullet> getBullets()
   {
	   return bullets;
   }

   public int getDamage()
   {
	   return damage;
   }
   
   public int getX() 
   {
	   return x;
   }

   public int getY() 
   {
	   return y;
   }

   public int getWidth() 
   {
	   return width;
   }

   public int getHeight() 
   {
	   return height;
   }
   
   public Image getImage()
   {
	   return sprite;
   }
   
   public void move()
   {
	   if(dx != 0 && (dx + x + width) < GameRunner.getScreenSize().width && x + dx > 0)
		  x += dx;
	   else if (dy + y + height < GameRunner.getScreenSize().height && y + dy > 0)
		  y += dy;
   }
   
   public int getScore()
   {
	   return score;
   }
   
   public int getHealth()
   {
	   return health;
   }
   
   public void updateHealth(int change)
   {
	   health -= change;
   }
   
   public String getDirection()
   {
	   if (up)
		   return "up";
	   else if (down)
		   return "down";
	   else if (right)
		   return "right";
	   else if (left)
		   return "left";
	   return lastDirection;
   }
   
   public void updateScore(int change)
   {
	   score += change;
   }
   
   public void keyPressed(KeyEvent e)
   {
	   int key = e.getKeyCode();
	   
	   if (key == KeyEvent.VK_UP)
	   {
		   up = true;
		   lastDirection = "up";
		   dy = -SPEED;
		   loadImage("src/img/playerup.gif");
	   }
	   
	   if (key == KeyEvent.VK_DOWN)
	   {
		   down = true;
		   lastDirection = "down";
		   dy = SPEED;
		   loadImage("src/img/playerdown.gif");
	   }
			
	   if (key == KeyEvent.VK_RIGHT)
	   {
		   	right = true;
		   	lastDirection = "right";
	   		dx = SPEED; 
			loadImage("src/img/player.gif");
	   }
	   
	   if (key == KeyEvent.VK_LEFT)
	   {
		   	left = true;
		   	lastDirection = "left";
	   		dx = -SPEED;
	   		loadImage("src/img/playerleft.gif");
	   }
	   if (key == KeyEvent.VK_SPACE && spaceReleased)
	   {
			spaceReleased = false;
			shoot();
	   }
   }
   
   public void keyReleased(KeyEvent e)
   {
	   int key = e.getKeyCode();
	   
	   if(key == KeyEvent.VK_UP)
	   {
		   up = false;
		   dy = 0;
	   }
	   
	   if(key == KeyEvent.VK_DOWN)
	   {
		   down = false;
		   dy = 0;
	   }
	   
	   if(key == KeyEvent.VK_LEFT)
	   {
		   left = false;
		   dx = 0;
	   }

	   if(key == KeyEvent.VK_RIGHT)
	   {
		   right = false;
		   dx = 0;
	   }
	   if (key == KeyEvent.VK_SPACE)
	   {
			spaceReleased = true;
	   }
   }
    
    
}