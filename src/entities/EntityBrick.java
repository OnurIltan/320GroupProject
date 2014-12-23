package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityBrick {

	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	public double getX();
	public double getY();
}
