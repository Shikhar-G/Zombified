import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Zombie implements Enemy
{
	
	private int dx;
	private int dy;
	private int x;
	private int y;
	private Image sprite;
	private Player player;
	private int round;
	private int width;
	private int height;
	private int health;
	private int damage;
	private String fileName;

	
	public static final int SPEED = GameRunner.getScreenSize().width / 1080;
	private static final int DAMAGE = 20;
	private static final int HEALTH = 50;
	private static final int SCOREM = 500;
	private static final int MOVEBACK = 100;
	private static final int MOVEDELAY = 25;
	private static final int ADJUST = 5;
	private static final int WIDTHSCALE = 24;


   public Zombie(int x, int y, Player player, int dx, int dy) 
   {
   		this.x = x;
   		this.y = y;
   		this.dx = dx;
   		this.dy = dy;
   		this.player = player;
   		damage = DAMAGE;
   		loadImage("zombie.gif");
   		health = (HEALTH + HEALTH * (player.getScore() / SCOREM));
   }
   private void loadImage(String fileName)
   {
	   sprite = new ImageIcon(fileName).getImage();
	   width = height = GameRunner.getScreenSize().width / WIDTHSCALE;
   }
   
   public int getDamage()
   {
	   return damage;
   }
   
   public int getWidth()
   {
   	return width;
   }
   public int getHeight()
   {
   	return height;
   }
   public int getX()
   {
   	return x;
   }
   public int getY()
   {
   	return y;
   }
   
   public void updateHealth(int damage)
   {
	   health -= damage;
   }
   
   public int getHealth()
   {
	   return health;
   }
   
   public boolean isHit(Bullet bullet)
   {
	   int bulXmin = bullet.getX();
	   int bulXmax = bullet.getX() + bullet.getSize();
	   int bulYmin = bullet.getY();
	   int bulYmax = bullet.getY() + bullet.getSize();
	   int zombXmin = x;
	   int zombXmax = x + width;
	   int zombYmin = y;
	   int zombYmax = y + height;
	   
	   if(((bulXmin >= zombXmin && bulXmin <= zombXmax) ||
			   (bulXmax >= zombXmin && bulXmax <= zombXmax))
			   && ((bulYmin >= zombYmin && bulYmin <= zombYmax) ||
					   (bulYmax >= zombYmin && bulYmax <= zombYmax)))
		   return true;
	   
	   return false;
   }
   
   public void moveBack()
   {
	   changeDirection();
	   int dbx = x - (MOVEBACK * dx);
	   int dby = y - (MOVEBACK * dy);
	   if (dbx > GameRunner.getScreenSize().width - width)
		   x = GameRunner.getScreenSize().width - 2 * width;
	   else if (dbx < 0)
		   x = 0 + width;
	   if (dby > GameRunner.getScreenSize().height - height)
		   y = GameRunner.getScreenSize().height - height;
	   else if (dby < 0)
		   y = 0;
	   else
	   {
		   x -= MOVEBACK * dx;
		   y -= MOVEBACK * dy;
	   }
   }
   public Image getImage()
   {
   	return sprite;
   }
   public void move()
   {
	   int dfx = Math.abs(x - player.getX());
	   int dfy = Math.abs(y - player.getY());
	   if ((Math.abs(dfx - dfy)) > SPEED * MOVEDELAY)
		   changeDirection();
	   x += dx;
	   y += dy;
   }
   
   public boolean hit()
   {
	   int playerRight = player.getX() - player.getWidth() * 1 / ADJUST + player.getWidth();
	   int playerY = player.getHeight() - player.getHeight() * 1 / ADJUST;
	   int playerLeft = player.getX() + player.getWidth() * 1 / ADJUST;
	   // check left
	   if (playerLeft - (x + width) <= 0 && (x + width) - playerLeft  <= width)
	   {
		   if (Math.abs(player.getY() - y) < playerY)
			   return true;
	   }
	   // check right
	   if (playerRight - x >= 0 && playerRight - x <= width)
	   {
		   if (Math.abs(player.getY() - y) < playerY)
			   return true;
	   }
	   return false;  
   }
   
   public void changeDirection()
   {
	   int dfx = x - player.getX();
	   int dfy = y - player.getY();
	   if (Math.abs(dfx) > Math.abs(dfy))
	   {
		   dy = 0;
		   if (dfx < 0)
		   {
			   dx = SPEED;
			   loadImage("src/img/zombie.gif");
		   }
		   else
		   {
			   dx = -SPEED;
			   loadImage("src/img/zombieleft.gif");
		   }
	   } 
	   else
	   {
		   dx = 0;
		   if(dfy < 0)
		   {
		   		dy = SPEED;
		   		loadImage("src/img/zombiedown.gif");
		   }
		   else
		   {
			   	dy = -SPEED;
		   		loadImage("src/img/zombieup.gif");
		   }
	   }
	   	
	  
   }
   
   
}