package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game.STATE;
import entities.EntityBrick;
import entities.EntityBullet;


public class Player2 extends GameObject implements EntityBrick {



	private double velX = 0;
	private double velY = 0;
	Game game;
	Controller c;
	

	String direction = "right";
	private boolean isCollided=false;
	String collidedDirection="";

	private BufferedImage player2;

	public Player2(double x, double y, Game game,Controller c){
		super(x,y);
		this.game = game;
		this.c = c;
		Tank tank = new Tank(game.getTank());
		player2 = tank.grabImage();

	}

	public void tick(){
		x+= velX;
		y+= velY;
		if(x <= 0) x = 0;
		if(x >= 640-18) x = 640-18;
		if(y <= 0) y = 0;
		if(y >= 480-32) y = 480-32;
		existsBrickAt();


		for(int i = 0 ; i<game.ebullet.size(); i++){
			EntityBullet tempEnt = game.ebullet.get(i);
			if(CollisionDetector.tankCollision2(this, tempEnt) ){
				System.out.println("COLLISION ON TANK2");
				Controller.ebullet.remove(i);
				Game.healthGreen -= 5;
				if(Game.healthGreen == 0){
					Game.state = STATE.GAMEOVER;
				}
			}
		}
	}
	
	
	public void render(Graphics g){
		g.drawImage(player2, (int) x, (int) y, null);
	}
	public void existsBrickAt(){
		for(int i = 0 ; i<game.ebrick.size(); i++){
			EntityBrick tempEnt = game.ebrick.get(i);
			if(CollisionDetector.brickwithTank2(this, tempEnt)){
				isCollided=true;
				collidedDirection=direction;
			}
		}
	
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
