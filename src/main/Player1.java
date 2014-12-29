package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game.STATE;
import entities.EntityBrick;
import entities.EntityBullet;


public class Player1 extends GameObject implements EntityBrick {



	private double velX = 0;
	private double velY = 0;
	Game game;
	Controller c;
	String direction = "right";
	private boolean isCollided=false;
	String collidedDirection="";



	private BufferedImage player1;

	public Player1(double x, double y, Game game, Controller c){

		super(x,y);
		this.game = game;
		this.c = c;
		Tank tank = new Tank(game.getTank());
		player1 = tank.grabImage();

	}

	public void tick(){
		x+= velX;
		y+= velY;
		if(x <= 0) x = 0;
		if(x >= 640-18) x = 640-18;
		if(y <= 0) y = 0;
		if(y >= 480-32) y = 480-32;
		existsBrickAt();
		
		
		for(int i = 0 ; i<game.ebullet2.size(); i++){
			EntityBullet tempEnt = game.ebullet2.get(i);
			if(CollisionDetector.tankCollision(this, tempEnt)){
				System.out.println("COLLISION ON TANK1");
				Controller.ebullet2.remove(i);
				Game.healthYellow -= 5;
				if(Game.healthYellow == 0){
					Game.state = STATE.GAMEOVER;
				}

			}
		}
	}

	public void existsBrickAt(){
		for(int i = 0 ; i<game.ebrick.size(); i++){
			EntityBrick tempEnt = game.ebrick.get(i);
			if(CollisionDetector.brickwithTank(this, tempEnt)){
				isCollided=true;
				collidedDirection=direction;
				
			}
		}
		
	}

	public void render(Graphics g){
		g.drawImage(player1, (int) x, (int) y, null);
	}

	public double getX(){
		return x;

	}
	public double getY(){
		return y;

	}

	public void setX(double x){
		this.x = x;

	}
	public void setY(double y){
		this.y = y; 

	}
	public void setvelX(double velX){
		this.velX = velX;

	}
	public void setvelY(double velY){
		this.velY = velY; 

	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 31);
	}
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}

	public String getCollidedDirection() {
		return collidedDirection;
	}

	public void setCollidedDirection(String collidedDirection) {
		this.collidedDirection = collidedDirection;
	}


}
