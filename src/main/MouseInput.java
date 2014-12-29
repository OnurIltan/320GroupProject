package main;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		//public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 90,165 ,100 ,50);
		//public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 90,265 ,100 ,50);
		//public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 90,365 ,100 ,50);

		int mx = e.getX();
		int my = e.getY();

		if(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH/2 + 220){
			if(my >= 150 && my<= 200 ){
				Game.state = Game.STATE.GAME;
			}
		}
		if(mx>=Game.WIDTH / 2 + 120 && mx<= Game.WIDTH/2 + 220){
			if(my >= 350 && my<= 400 ){
				System.exit(1);
			}
		}

		if(mx>=Game.WIDTH / 2 + 120 && mx<= Game.WIDTH/2 + 220){
			if(my >= 250 && my<= 300 ){

				Game.state = Game.STATE.HELP;

			}
		}
		if(mx>=550 && mx<= 610){
			if(my >= 400 && my<= 465 ){
				Menu.soundClicked = !Menu.soundClicked;
				if(Menu.soundClicked){
					Game.audio.stopMusic();
				}

				


			}
		}


	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
