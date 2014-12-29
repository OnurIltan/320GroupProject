package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Help extends KeyAdapter {


	Game game;
	public Help(Game game) {
		this.game = game;

	}

	public void render(Graphics g){


		g.drawString("The logic behind the game is pretty simple,", 40,35);
		g.drawString("you should just catch eggs that are falling from" , 40,55);
		g.drawString("the top of the screen.When you are collecting eggs,", 40, 75);
		g.drawString("you should think twice to collect red boxes since,", 40, 95);
		g.drawString("they can help you to pass level by increasing your", 40, 115);
		g.drawString("lives,score and speed, but also they can be a treat for you",5,135);
		g.drawString("because,as well as they can increase, they can decrease" , 5, 155);
		g.drawString("your lives,score and speed too.So, you should be real ", 5 , 175);
		g.drawString("quick to decide.", 5, 195);
		g.drawString("Please press enter to back menu.", 120, 220);

	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			
			


		}

	}
	
	
	
	
}
