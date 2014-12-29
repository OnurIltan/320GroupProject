package main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.EntityBrick;
import entities.EntityBullet;

public class Game extends Canvas implements Runnable {


	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Castle Defence";
	private boolean running = false;
	private Thread thread;
	static private BufferedImage tank = null;
	static private BufferedImage greentank = null;
	static private BufferedImage bullet = null;
	static private BufferedImage brick = null;
	static private BufferedImage castle = null;

	public ImageLoader loader;

	private boolean turnRight = true;
	private boolean turnLeft = false;
	private boolean turnUp = false;
	private boolean turnDown = false;
	private boolean turnRight1 = true;
	private boolean turnLeft1 = false;
	private boolean turnUp1 = false;
	private boolean turnDown1 = false;
	public boolean gameOverEnterPressed = false;
	public boolean helpEnterPressed = false;
	private int bulletDelay1 = 0;
	private int bulletDelay2 = 0;


	ImageIcon icon ;
	ImageIcon greentnk ;
	ImageIcon ball;
	ImageIcon wall;
	ImageIcon upTank = new ImageIcon("res/upTank.jpg");
	ImageIcon downTank = new ImageIcon("res/downTank.jpg");
	ImageIcon leftTank = new ImageIcon("res/leftTank.jpg"); 
	ImageIcon rightTank = new ImageIcon("res/rightTank.jpg");
	ImageIcon upTank1 = new ImageIcon("res/greenUp.png");
	ImageIcon downTank1 = new ImageIcon("res/greenDown.png");
	ImageIcon leftTank1 = new ImageIcon("res/greenLeft.png"); 
	ImageIcon rightTank1 = new ImageIcon("res/greenRight.png");  
	ImageIcon eagle = new ImageIcon("res/eagle1.jpg");  

	private Controller c;
	//private Brick b;
	static  Player1 p1;
	static  Player2 p2;

	private Boolean isShooting = false;
	private Boolean isShooting1 = false;

	public LinkedList<EntityBullet> ebullet;
	public LinkedList<EntityBullet> ebullet2;
	public LinkedList<EntityBrick> ebrick;
	private Menu menu;
	static int healthYellow = 25;
	static int healthGreen = 25;
	static AudioManager audio;
	public AudioManager shootEffect;
	public AudioManager inGameMusic;
	static AudioManager brickEffect;
	public AudioManager gameOverMusic;

	int singtime = 0;



	public boolean getRight(){
		return turnRight;
	}
	public boolean getLeft(){
		return turnLeft;

	}
	public boolean getUp(){
		return turnUp;
	}
	public boolean getDown(){
		return turnDown;
	}

	public static enum STATE{
		MENU,
		HELP,
		GAME,
		GAMEOVER

	};

	public static STATE state = STATE.MENU;


	public void init(){

		icon = new ImageIcon("res/rightTank.jpg");
		greentnk = new ImageIcon("res/greenRight.png");
		ball = new ImageIcon("res/bullet.png");
		wall = new ImageIcon("res/brck.png");
		bullet = iconToBuffer(ball);
		tank = iconToBuffer(icon);
		brick = iconToBuffer(wall);
		greentank = iconToBuffer(greentnk);
		castle = iconToBuffer(eagle);

		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		p1 = new Player1(100,250,this,c);
		p2 = new Player2(410,400,this,c);
		c= new Controller(this);
		c.createMap();
		ebullet = c.getEbullet();
		ebullet2 = c.getEbullet2();
		ebrick = c.getEbrick();
		menu = new Menu();
		audio = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/main.wav");
		audio.startMusic();
		gameOverMusic = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/game over.wav");
		shootEffect = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/shoot.wav");

		requestFocus();

	}


	@Override
	public void run() {


		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		long timer = System.currentTimeMillis();

		while(running){
			bulletDelay1++;
			bulletDelay2++;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				singtime++;

				delta--;
			}
			render();
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(updates + " ticks, frames " + frames);
			}
		}

		stop();

	}

	private void tick(){

		if(state == STATE.GAME){
			p1.tick();
			p2.tick();
			c.tick();

		}
	}
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		////


		if(state == STATE.GAMEOVER){
			gameOverMusic.startMusic();

			g.clearRect (0, 0, getWidth(),getHeight());
			Font fnt1 = new Font("Arial",Font.BOLD,30);
			g.setColor(Color.WHITE);
			g.setFont(fnt1);
			g.drawString("GAME OVER", 260, 250);
			Font fnt2 = new Font("Arial",Font.BOLD,15);
			g.setFont(fnt2);
			g.drawString("Press enter to go back to main menu", 390, 450);
			if(gameOverEnterPressed == true){
				g.clearRect (0, 0, getWidth(),getHeight());
				repaint();
				gameOverEnterPressed = false;

			}

			//state = STATE.MENU;

		}
		if(state == STATE.HELP){
			g.clearRect (0, 0, getWidth(),getHeight());
			Font fnt1 = new Font("Arial",Font.BOLD,18);
			g.setColor(Color.WHITE);
			g.setFont(fnt1);
			g.drawString("Player1 Controller(Yellow Tank)", 20,35);
			g.drawString("up arrow to move up " , 20,55);
			g.drawString("down arrow to move down" ,20,75);
			g.drawString("right arrow to move right", 20, 95);
			g.drawString("left arrow to move left" , 20,115);
			g.drawString("SPACE to shoot",20,135);
			g.drawString("Player2 Controller(Green Tank)", 350,35);
			g.drawString("W to move up " , 350,55);
			g.drawString("S to move down" , 350,75);
			g.drawString("D to move right", 350, 95);
			g.drawString("A to move left" , 350,115);
			g.drawString("Q to shoot",350,135);
			g.drawString("Press enter to go back to main menu",300,400);



		}
		if(state == STATE.GAME  ){
			g.clearRect (0, 0, getWidth(),getHeight());
			p1.render(g);
			p2.render(g);
			c.render(g);
			//b.render(g);

			g.drawImage(castle, 290, 400, castle.getWidth() ,castle.getHeight(), null);

			g.drawImage(tank,(int)p1.getX(), (int)p1.getY() ,tank.getWidth() ,tank.getHeight(), this);
			g.drawImage(greentank,(int)p2.getX(), (int)p2.getY() ,greentank.getWidth() ,greentank.getHeight(), this);
			//firstttank health bar
			g.setColor(Color.red);
			g.fillRect((int)p1.getX()-6, (int)p1.getY()+3, 6, 25);

			g.setColor(Color.green);
			g.fillRect((int)p1.getX()-6, (int)p1.getY()+3, 6, healthYellow);

			g.setColor(Color.white);
			g.drawRect((int)p1.getX()-6, (int)p1.getY()+3, 5, 25);
			//secondtankhealthbar

			g.setColor(Color.red);
			g.fillRect((int)p2.getX()-6, (int)p2.getY()+3, 6, 25);

			g.setColor(Color.green);
			g.fillRect((int)p2.getX()-6, (int)p2.getY()+3, 6, healthGreen);

			g.setColor(Color.white);
			g.drawRect((int)p2.getX()-6, (int)p2.getY()+3, 5, 25);


		}
		if(state == STATE.MENU){
			menu.render(g);
			if(helpEnterPressed == true){
				g.clearRect (0, 0, getWidth(),getHeight());
				helpEnterPressed = false;
			}


		}
		////
		g.dispose();
		bs.show();
	}

	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public static void main(String args[]){
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		game.start();

	}
	public BufferedImage getTank(){
		return tank;
	}
	public BufferedImage getBullet(){
		return bullet;
	}
	public BufferedImage getBrick(){
		return brick;
	}




	public void keyPressed(KeyEvent e)   {

		int key = e.getKeyCode();

		if(state == STATE.GAMEOVER){
			if(key == KeyEvent.VK_ENTER ){
				gameOverEnterPressed = true;
				state = STATE.MENU;

			}
		}
		if(state == STATE.HELP){
			if(key == KeyEvent.VK_ENTER ){
				helpEnterPressed = true;
				state = STATE.MENU;

			}
		}

		if(state == STATE.GAME){

			if (key == KeyEvent.VK_RIGHT  ){
				if(p1.isCollided()==false || !p1.getCollidedDirection().equals("right")) {
					p1.setDirection("right");
					p1.setvelX(1);
					p1.setvelY(0);
					turnRight = true;
					turnLeft = false;
					turnUp = false;
					turnDown = false;
					tank = iconToBuffer(rightTank);
					p1.setCollided(false);
					requestFocus();
				}
				else{
					p1.setvelX(0);
					p1.setvelY(0);
				}


			}
			else if (key == KeyEvent.VK_LEFT  ){
				if(p1.isCollided()==false || !p1.getCollidedDirection().equals("left")) {
					p1.setDirection("left");
					p1.setvelX(-1);
					p1.setvelY(0);
					turnLeft = true;
					turnUp = false;
					turnDown = false;
					turnRight = false;
					icon = leftTank;
					tank = iconToBuffer(leftTank);
					p1.setCollided(false);
					requestFocus();}
				else{
					p1.setvelX(0);
					p1.setvelY(0);
				}


			}
			else if (key == KeyEvent.VK_DOWN ){
				if(p1.isCollided()==false || !p1.getCollidedDirection().equals("down")) {
					p1.setDirection("down");
					p1.setvelY(1);
					p1.setvelX(0);
					turnDown = true;
					turnLeft = false;
					turnUp = false;
					turnRight = false;
					tank = iconToBuffer(downTank);
					p1.setCollided(false);
					requestFocus();
				}else{
					p1.setvelX(0);
					p1.setvelY(0);
				}



			}
			else if (key == KeyEvent.VK_UP  ){
				if(p1.isCollided()==false || !p1.getCollidedDirection().equals("up")) {
					p1.setDirection("up");
					p1.setvelY(-1);
					p1.setvelX(0);
					turnUp = true;
					turnDown = false;
					turnRight = false;
					turnLeft = false;
					tank = iconToBuffer(upTank);
					p1.setCollided(false);
					requestFocus();
				}else{
					p1.setvelX(0);
					p1.setvelY(0);
				}
			}
			else if(key == KeyEvent.VK_SPACE && !isShooting &&bulletDelay1 >1000){
				isShooting = true;
				bulletDelay1 = 0;
				shootEffect = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/shoot.wav");
				c.addEntity(new Bullet(p1.getX()+8,p1.getY()+8,this,turnRight,turnLeft,turnUp,turnDown,c));
				shootEffect.startMusic();

			}

			else if (key == KeyEvent.VK_D ){
				if(p2.isCollided()==false || !p2.getCollidedDirection().equals("right")) {
					p2.setDirection("right");
					p2.setvelX(1);
					p2.setvelY(0);
					turnRight1 = true;
					turnLeft1 = false;
					turnUp1 = false;
					turnDown1 = false;
					greentank = iconToBuffer(rightTank1);
					p2.setCollided(false);
					requestFocus();
				}else{
					p2.setvelX(0);
					p2.setvelY(0);
				}

			}
			else if (key == KeyEvent.VK_A )  {
				if(p2.isCollided()==false || !p2.getCollidedDirection().equals("left")) {

					p2.setDirection("left");
					p2.setvelX(-1);
					p2.setvelY(0);
					turnLeft1 = true;
					turnUp1 = false;
					turnDown1 = false;
					turnRight1 = false;
					greentank = iconToBuffer(leftTank1);
					p2.setCollided(false);
					requestFocus();
				}else{
					p2.setvelX(0);
					p2.setvelY(0);
				}

			}
			else if (key == KeyEvent.VK_S )  {
				if(p2.isCollided()==false || !p2.getCollidedDirection().equals("down")) {

					p2.setDirection("down");
					p2.setvelY(1);
					p2.setvelX(0);
					turnDown1 = true;
					turnLeft1 = false;
					turnUp1 = false;
					turnRight1 = false;
					greentank = iconToBuffer(downTank1);
					p2.setCollided(false);

					requestFocus();
				}else{
					p2.setvelX(0);
					p2.setvelY(0);
				}

			}
			else if (key == KeyEvent.VK_W )  {
				if(p2.isCollided()==false || !p2.getCollidedDirection().equals("up")) {

					p2.setDirection("up");
					p2.setvelY(-1);
					p2.setvelX(0);
					turnUp1 = true;
					turnDown1 = false;
					turnRight1 = false;
					turnLeft1 = false;
					greentank = iconToBuffer(upTank1);
					p2.setCollided(false);
					requestFocus();
				}else{
					p2.setvelX(0);
					p2.setvelY(0);
				}
			}
			else if(key == KeyEvent.VK_Q && !isShooting1 && bulletDelay2 >1000){

				isShooting1 = true;
				bulletDelay2 = 0;
				shootEffect = new AudioManager("C:\\Users/Asus/Desktop/eclipse/workspace/CastleDefence/res/shoot.wav");
				c.addEntity2(new Bullet(p2.getX()+8,p2.getY()+8,this,turnRight1,turnLeft1,turnUp1,turnDown1,c));
				shootEffect.startMusic();


			}
		}
	}

	public void keyReleased(KeyEvent e){

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT)  	{p1.setvelX(0);;}
		else if (key == KeyEvent.VK_LEFT)  {p1.setvelX(0);   	}
		else if (key == KeyEvent.VK_DOWN)  {p1.setvelY(0);  	}
		else if (key == KeyEvent.VK_UP)  {p1.setvelY(0); }
		else if (key == KeyEvent.VK_SPACE){isShooting = false;}

		if (key == KeyEvent.VK_D)  	{p2.setvelX(0);;}
		else if (key == KeyEvent.VK_A)  {p2.setvelX(0);	}
		else if (key == KeyEvent.VK_S)  {p2.setvelY(0);	}
		else if (key == KeyEvent.VK_W)  {p2.setvelY(0);	}
		else if (key == KeyEvent.VK_Q){isShooting1 = false;}

	}

	public static BufferedImage iconToBuffer(ImageIcon icon){
		Image source = icon.getImage();
		int w = source.getWidth(null);
		int h = source.getHeight(null);
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)image.getGraphics();
		g2d.drawImage(source,0,0,null);
		g2d.dispose();
		return image;  
	} 







}
