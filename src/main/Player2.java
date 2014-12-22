package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.EntityBrick;


public class Player2 extends GameObject implements EntityBrick {

	

	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player2;

	public Player2(double x, double y, Game g){
		super(x,y);
		Tank tank = new Tank(g.getTank());
		player2 = tank.grabImage();
		
	}
	
	public void tick(){
		x+= velX;
		y+= velY;
		if(x <= 0) x = 0;
		if(x >= 640-18) x = 640-18;
		if(y <= 0) y = 0;
		if(y >= 480-32) y = 480-32;
				
	}
	public void render(Graphics g){
		g.drawImage(player2, (int) x, (int) y, null);
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
		return new Rectangle((int)x, (int)y, 38, 18);
	}


}
