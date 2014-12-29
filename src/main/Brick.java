package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.EntityBrick;
import entities.EntityBullet;

public class Brick extends GameObject implements EntityBrick {

	private BufferedImage brick;
	Game game;
	Controller c;
	static boolean priority = false;


	public Brick(double x, double y ,Game game,Controller c){
		super(x,y);
		this.game = game;
		this.c = c;
		Tank tank = new Tank(game.getBrick());
		brick = tank.grabImage();
	}

	public void tick(){

		for(int i = 0 ; i<game.ebullet.size(); i++){
			EntityBullet tempEnt = game.ebullet.get(i);
			if(CollisionDetector.Collision(this, tempEnt)){
				c.removeEntity(this);
				c.removeEntity(tempEnt);
				System.out.println("COLLISION ON BRICK by player1");
				Game.brickEffect = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/brick destroy.wav");

			}
		}for(int i = 0 ; i<game.ebullet2.size(); i++){
			EntityBullet tempEnt = game.ebullet2.get(i);
			if(CollisionDetector.Collision(this, tempEnt)){
				c.removeEntity(this);
				c.removeEntity2(tempEnt);
				System.out.println("COLLISION ON BRICK by player2");
				Game.brickEffect = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/brick destroy.wav");

			}
		}
		
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 24, 21);
	}


	public void render(Graphics g){
		g.drawImage(brick, (int)x, (int)y, null);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}


}
