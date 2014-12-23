package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.EntityBrick;

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
	
	
		if(CollisionDetector.Collision(this, game.ebullet)){
			c.removeEntity(this);
			priority = true;
			System.out.println("COLLISION ON BRICK");
		}
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 38, 18);
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
