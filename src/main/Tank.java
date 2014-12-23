package main;

import java.awt.image.BufferedImage;

public class Tank {
	
    private BufferedImage image;
	
	public Tank(BufferedImage image){
		this.image = image;
	}

	public BufferedImage grabImage(){
		
		return image;
	}
	
	
}
