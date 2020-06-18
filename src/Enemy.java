import java.util.*;
import java.awt.*;
import javax.swing.*;

public interface Enemy 
{
    public Image getImage();
    
    public int getWidth();
    	
    public int getHeight();
    	
    public int getX();
    
    public int getDamage();
    
    public int getY();
    
    public boolean hit();
    
    public void moveBack();
    
    public void changeDirection();
   
    public void move();
    
    public boolean isHit(Bullet bullet);
    
    public void updateHealth(int damage);
    
    public int getHealth();
    
    
}