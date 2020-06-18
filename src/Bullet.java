import java.awt.geom.Ellipse2D;
import java.util.*;
import java.awt.Image;
import java.awt.List;

import javax.swing.ImageIcon;

public class Bullet
{
	private static final int MAX_DISTANCE = 800;
	private static final int SCALE = 128;
	
	private int x;
	private int y;
	private int size;
	private int distanceMoved;
	private String direction;
	private int SPEED = 10;
	private Image sprite;
	
   public Bullet(int x, int y, String direction) 
   {
	   this.direction = direction;
	   this.x = x;
	   this.y = y;
	   size = GameRunner.getScreenSize().width / SCALE;
	   loadImage();
   }
   
   public int getSize()
   {
	   return size;
   }
   
   public int getX()
   {
	return x;
   }

   public int getY()
   {
   	return y;
   }
   
   public Image getImage()
   {
	   return sprite;
   }
   
   public boolean isLive()
   {
	   if(distanceMoved == MAX_DISTANCE)
		   return false;
	   return true;
   }
   
   private void loadImage()
   {
	   sprite = new ImageIcon("src/img/bullet.png").getImage();
   }
    
   public void move()
   {
	   	if (isLive())
	   	{
	   
	   		if (direction.equals("up"))
	   		{
	   			y -= SPEED;
	   		}
	   	
	   		if (direction.equals("down"))
	   		{
	   			y += SPEED;
	   		}
	   	
	   		if (direction.equals("right"))
	   		{
	   			x += SPEED;
	   		}
	   		
	   		if (direction.equals("left"))
	   		{
	   			x -= SPEED;
	   		}	
	   		
	   		distanceMoved += SPEED;
	   	}		
   } 
    
    
}	