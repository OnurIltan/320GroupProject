package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;






import main.Game.STATE;
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
		if(x>=290 && x<= 380){
			if(y >= 410 && y<= 480 ){
				Game.state = STATE.GAMEOVER;
			}
		}
		/*
		if(Brick.priority == true){
			c.removeEntity(this);
			System.out.println("COLLISION ON BULLET");
			Brick.priority = false;
			}
		 */

		/*	
		if(CollisionDetector.Collision(this, game.ebrick)){

			if(Brick.priority == true){
			//c.removeEntity(this);
			//System.out.println("COLLISION ON BULLET");
			//priority = false;
			}

		}
		 */
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
