package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.EntityBullet;

public class Bullet extends GameObject implements EntityBullet {



	BufferedImage bullet;
	Controller c;
	Game game;
	private boolean turnRight ;
	private boolean turnLeft ;
	private boolean turnUp ;
	private boolean turnDown ;


	public Bullet(double x, double y, Game game,boolean turnRight,boolean turnLeft,boolean turnUp,boolean turnDown,Controller c){
		super(x,y);
		this.turnRight = turnRight;
		this.turnLeft = turnLeft;
		this.turnUp = turnUp;
		this.turnDown = turnDown;
		this.game = game;
		this.c = c;

		Tank tank = new Tank(game.getBullet());
		bullet = tank.grabImage();

	}
	public void tick(){


		if(turnRight)x += 3;
		if(turnLeft) x -= 3;
		if(turnUp)   y -= 3;
		if(turnDown) y += 3;
		if (y < -100 || y > 500 || x>700 || x<-360) {
			c.removeEntity(this);
		}
		for(int i = 0 ; i<game.ebullet.size(); i++){
			EntityBullet tempEnt = game.ebullet.get(i);
			if(CollisionDetector.CollisionEagle(tempEnt)){
				c.removeEntity(tempEnt);
				Game.healthCastle -= 30;
				if(Game.healthCastle == 0){
					Game.state = Game.STATE.GAMEOVER;
				}
			}
		}
		for(int i = 0 ; i<game.ebullet2.size(); i++){
			EntityBullet tempEnt1 = game.ebullet2.get(i);
			if(CollisionDetector.CollisionEagle(tempEnt1)){
				c.removeEntity(tempEnt1);
				
			}
		}
		
		




	}
	public void render(Graphics g){
		g.drawImage(bullet, (int)x, (int)y, null);

	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 18,12);
	}

}
