package main;



import entities.EntityBrick;
import entities.EntityBullet;

public class CollisionDetector {


	public static boolean Collision(EntityBullet bullet, EntityBrick entbrick){


		if(bullet.getBounds().intersects(entbrick.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean CollisionBullet(EntityBullet bullet, EntityBullet bullet1){

		if(bullet.getBounds().intersects(bullet1.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean Collision(EntityBrick brick, EntityBullet entbullet){


		if(brick.getBounds().intersects(entbullet.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean tankCollision(Player1 player, EntityBullet entbullet){

		if(player.getBounds().intersects(entbullet.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean tankCollision2(Player2 player, EntityBullet entbullet){

		if(player.getBounds().intersects(entbullet.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean brickwithTank(Player1 player, EntityBrick entbullet){

		if(player.getBounds().intersects(entbullet.getBounds())){
			return true;

		}
		return false;

	}
	public static boolean brickwithTank2(Player2 player, EntityBrick entbullet){

		if(player.getBounds().intersects(entbullet.getBounds())){
			return true;

		}
		return false;

	}




}
