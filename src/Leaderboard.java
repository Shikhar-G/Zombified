import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComponent;

public class Leaderboard extends JComponent
{
	private List<Integer> scores;
	private List<String> names;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private static final int LENGTH = 5;
	private static final int NUMSCORES = 10;
	private static final int FONTSCALE  = 18;
	private static final int X = 6;
	private static final int XTWO = 20;
	private static final int XTHREE = 16;
	private static final int Y = 12;

        
   public Leaderboard()
   {
	   scores = new ArrayList<>();
	   names = new ArrayList<>();
	   File file = new File("src/scores.txt");
	   try
	   {
		   Scanner scan = new Scanner(file);
		   while (scan.hasNext())
		   {
			   scores.add(scan.nextInt());
			   names.add(scan.nextLine());
		   }
		   scan.close();
	   }
	   catch (FileNotFoundException e)
	   {
			e.printStackTrace();
	   }
	  
   }
   
   public int onLeaderboard(int score)
   {	   
	   int index = -1;
	   if (scores.size() < NUMSCORES)
		   index = scores.size();
	   for (int i = 0; i < scores.size(); i++)
	   {
		   int check = scores.get(i);
		   if (score > check)
		   {
			   index = i;
			   break;
		   }
	   }
	   return index;		   
   }
   
   public void addScore(int index, int score, String name)
   {
	 	scores.add(index, score);
	   	names.add(index, name);
	   	if (scores.size() > NUMSCORES)
	   	{
	   		scores.remove(scores.size() - 1);
	   		names.remove(names.size() - 1);
	   	}
		try 
		{
			PrintWriter write = new PrintWriter(new File("src/scores.txt"));
			for (int i = 0; i < scores.size(); i++)
			{
				write.println(scores.get(i) + " " + names.get(i));
			}
			write.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

   @Override
   public void paintComponent(Graphics g) 
   {
	   Graphics2D gr2 = (Graphics2D) g;
	   gr2.setColor(Color.BLACK);
	   gr2.fillRect(0, 0, WIDTH, HEIGHT);
	   gr2.setColor(Color.GREEN);
	   gr2.setFont(new Font("Courier", Font.BOLD, WIDTH / FONTSCALE));
	   gr2.drawString("HIGH SCORES", WIDTH * X / XTWO, HEIGHT / Y);
	   for (int i = 0; i < scores.size(); i++)
	   {
		   String name = names.get(i).trim();
		   if (name.length() > LENGTH)
			   name = name.substring(0, LENGTH + 1);
//		   String nameS = String.format("%s", i + 1, name);
		   String number = Integer.toString(scores.get(i));
		   gr2.drawString(name, WIDTH * 1 / XTHREE, HEIGHT * (i + 2) / Y);
		   gr2.drawString(number, WIDTH * 3 / 4, HEIGHT * (i + 2) / Y);
		   
	   }
   }
   

}
   

