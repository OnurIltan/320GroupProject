package main;

import java.util.LinkedList;

import entities.EntityBrick;
import entities.EntityBullet;

public class CollisionDetector {


	public static boolean Collision(EntityBullet bullet, LinkedList<EntityBrick> entbrick){

		for(int i = 0  ;i< entbrick.size() ; i++){
			if(bullet.getBounds().intersects(entbrick.get(i).getBounds())){
				return true;
			}
		}
		return false;

	}
	public static boolean Collision(EntityBrick brick, LinkedList<EntityBullet> entbullet){

		for(int i = 0  ;i< entbullet.size() ; i++){
			if(brick.getBounds().intersects(entbullet.get(i).getBounds())){
				return true;
			}
		}
		return false;

	}


}
