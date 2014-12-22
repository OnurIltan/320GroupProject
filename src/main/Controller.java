package main;

import java.awt.Graphics;
import java.util.LinkedList;

import entities.EntityBrick;
import entities.EntityBullet;

public class Controller {

	private LinkedList<EntityBullet> ebullet = new LinkedList<EntityBullet>();  

	private LinkedList<EntityBrick> ebrick = new LinkedList<EntityBrick>();  
	Game game;
	EntityBullet entBullet;
	EntityBrick entBrick;

	public Controller(Game game) {
		this.game = game;
		

	}
	public void createMap(){
		for(int i = 0 ; i<200 ; i+=30){
			addEntity(new Brick(i,100,game,this));
		}
	}

	public void tick(){
		for(int i = 0 ; i<ebullet.size() ; i++){

			entBullet = ebullet.get(i);
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
	public void addEntity(EntityBrick block){
		ebrick.add(block);
	}
	public void removeEntity(EntityBrick block){
		ebrick.remove(block);
	}
	public LinkedList<EntityBullet> getEbullet() {
		return ebullet;
	}
	public LinkedList<EntityBrick> getEbrick() {
		return ebrick;
	}

}
