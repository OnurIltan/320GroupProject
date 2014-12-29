package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Menu {


	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120,150 ,100 ,50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120,250 ,100 ,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120,350 ,100 ,50);
	public Graphics2D g2d;
	static boolean soundClicked = false;

	public void render(Graphics g){

			g2d =  (Graphics2D) g;
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
			ImageIcon img = new ImageIcon("res/soundon.jpg");
			BufferedImage sound = Game.iconToBuffer(img);
			//g2d.drawImage(sound,550, 400 ,sound.getWidth() ,sound.getHeight(), null);
			if(soundClicked == true){
				ImageIcon img1 = new ImageIcon("res/soundoff.jpg");
				BufferedImage sound1 = Game.iconToBuffer(img1);
				g2d.drawImage(sound1,550, 400 ,sound.getWidth() ,sound.getHeight(), null);
			}if(soundClicked == false){
				g2d.drawImage(sound,550, 400 ,sound.getWidth() ,sound.getHeight(), null);
				Game.audio.startMusic();

		}



	}

}
