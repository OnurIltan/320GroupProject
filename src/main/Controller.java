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

	public Controller(Game game) {
		this.game = game;


	}
	public void createMap(){

		//upper left
		for(int i = 42 ; i< 210 ; i += 21){
			addEntity(new Brick(50,i,game,this));

		}
		for(int i = 42 ; i< 210 ; i += 21){
			addEntity(new Brick(120,i,game,this));

		}
		
		//middle up
		for(int i = 42 ; i< 148 ; i += 21){
			addEntity(new Brick(250,i,game,this));

		}
		for(int i = 42 ; i< 148 ; i += 21){
			addEntity(new Brick(350,i,game,this));

		}
		//upper right
		for(int i = 42 ; i< 210 ; i += 21){
			addEntity(new Brick(470,i,game,this));

		}
		for(int i = 42 ; i< 210 ; i += 21){
			addEntity(new Brick(540,i,game,this));

		}
		
		
		
		//middle
		for(int i = 150 ; i<234 ; i += 21){
			addEntity(new Brick(i,260,game,this));

		}
		for(int i = 420 ; i<504 ; i += 21){
			addEntity(new Brick(i,260,game,this));

		}
		for(int i = 0 ; i< 63 ; i += 21){
			addEntity(new Brick(i,260,game,this));

		}
		for(int i =585 ; i< 648 ; i += 21){
			addEntity(new Brick(i,260,game,this));

		}
		for(int i = 230 ; i< 315 ; i += 21){
			addEntity(new Brick(280,i,game,this));

		}
		for(int i = 230 ; i< 315 ; i += 21){
			addEntity(new Brick(360,i,game,this));

		}
		
		for(int i = 300 ; i< 343 ; i += 21){
			addEntity(new Brick(i,250,game,this));

		}
		//left block
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(50,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(74,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(98,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(180,i,game,this));

		}

		// right block

		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(524,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(548,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(572,i,game,this));

		}
		for(int i = 330 ; i< 435 ; i += 21){
			addEntity(new Brick(450,i,game,this));

		}
		
		
		
		//

		// eagle defenders
		for(int i = 380 ; i< 479 ; i += 21){
			addEntity(new Brick(265,i,game,this));

		}
		for(int i = 380 ; i< 479 ; i += 21){
			addEntity(new Brick(378,i,game,this));

		}
		for(int i = 280 ; i< 379 ; i += 21){
			addEntity(new Brick(i,380,game,this));

		}
		// end of eagle defenders


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
