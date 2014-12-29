package main;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.EntityBrick;
import entities.EntityBullet;

public class Controller {

	static LinkedList<EntityBullet> ebullet = new LinkedList<EntityBullet>();
	static LinkedList<EntityBullet> ebullet2 = new LinkedList<EntityBullet>(); 

	private LinkedList<EntityBrick> ebrick = new LinkedList<EntityBrick>();  
	Game game;
	EntityBullet entBullet;
	EntityBrick entBrick;
	Player1 p1;
	Player2 p2;
	
	public Controller(Game game, Player1 p1, Player2 p2) {
		this.p1=p1;
		this.p2=p2;
		this.game = game;
		

	}
	
	public void createMap(){
		/*
		for(int i = 0 ; i<200 ; i+=30){
			addEntity(new Brick(i,100,game,this));
		}
		*/
		for(int i = 50 ; i<570 ; i+=30){
			addEntity(new Brick(i,100,game,this));
		}
		
		for(int i = 170 ; i<450 ; i+=30){
			addEntity(new Brick(i,150,game,this));
		}
		
		for(int i = 50 ; i<570 ; i+=30){
			addEntity(new Brick(i,200,game,this));
		}
		
		for(int i = 170 ; i<450 ; i+=30){
			addEntity(new Brick(i,250,game,this));
		}
		
		for(int i = 50 ; i<570 ; i+=30){
			addEntity(new Brick(i,300,game,this));
		}
		
		for(int i = 170 ; i<450 ; i+=30){
			addEntity(new Brick(i,350,game,this));
		}
		
		for(int i = 170 ; i<450 ; i+=30){
			addEntity(new Brick(i,350,game,this));
		}
		
		for(int i = 170 ; i<450 ; i+=30){
			addEntity(new Brick(i,350,game,this));
		}
		
		for(int i = 460 ; i>400 ; i=i-15){
			addEntity(new Brick(250,i,game,this));
		}
		for(int i = 460 ; i>400 ; i=i-15){
			addEntity(new Brick(350,i,game,this));
		}
		for(int i = 255 ; i<350 ; i+=30){
			addEntity(new Brick(i,400,game,this));
		
	
		
	}
	}

	public void tick(){
		for(int i = 0 ; i<ebullet.size() ; i++){

			entBullet = ebullet.get(i);
			entBullet.tick();
			

		}
		for(int i = 0 ; i<ebullet2.size() ; i++){

			entBullet = ebullet2.get(i);
			entBullet.tick();
			

		}
		for(int i = 0 ; i<ebrick.size() ; i++){

			entBrick = ebrick.get(i);
			entBrick.tick();

		}
		p1.existsBrickAt();
		p2.existsBrickAt();

	}
	public void render(Graphics g){
		for(int i = 0 ; i<ebullet.size() ; i++){
			entBullet = ebullet.get(i);
			entBullet.render(g);
		}
		for(int i = 0 ; i<ebullet2.size() ; i++){
			entBullet = ebullet2.get(i);
			entBullet.render(g);
		}
		for(int i = 0 ; i<ebrick.size() ; i++){
			entBrick = ebrick.get(i);
			entBrick.render(g);

		}
	}


	public void addEntity(EntityBullet block){
		ebullet.add(block);
	}
	public void removeEntity(EntityBullet block){
		ebullet.remove(block);
	}
	public void addEntity2(EntityBullet block){
		ebullet2.add(block);
	}
	public void removeEntity2(EntityBullet block){
		ebullet2.remove(block);
	}
	public void addEntity(EntityBrick block){
		ebrick.add(block);
	}
	public void removeEntity(EntityBrick block){
		ebrick.remove(block);
	}
	public LinkedList<EntityBullet> getEbullet() {
		return ebullet;
	}
	public LinkedList<EntityBullet> getEbullet2() {
		return ebullet2;
	}
	public LinkedList<EntityBrick> getEbrick() {
		return ebrick;
	}

}
