package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120,150 ,100 ,50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120,250 ,100 ,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120,350 ,100 ,50);

	
	public void render(Graphics g){
		
		Graphics2D g2d =  (Graphics2D) g;
		Font fnt = new Font("Arial",Font.BOLD,50);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString("CASTLE DEFENCE", Game.WIDTH/2-50,100);
		Font fnt1 = new Font("Arial",Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Play" ,Game.WIDTH-20,180);
		g.drawString("Help" ,Game.WIDTH-20,280);
		g.drawString("Quit" ,Game.WIDTH-20,380);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		
	}

}
