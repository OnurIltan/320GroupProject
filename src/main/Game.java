package main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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
	public ImageLoader loader;
	//int artur = 0;

	private boolean turnRight = true;
	private boolean turnLeft = false;
	private boolean turnUp = false;
	private boolean turnDown = false;
	private boolean turnRight1 = true;
	private boolean turnLeft1 = false;
	private boolean turnUp1 = false;
	private boolean turnDown1 = false;
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
	private Controller c;
	//private Brick b;
	private Player1 p1;
	private Player2 p2;

	private Boolean isShooting = false;
	private Boolean isShooting1 = false;

	public LinkedList<EntityBullet> ebullet;
	public LinkedList<EntityBrick> ebrick;
	private Menu menu;


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
		GAME

	};

	public static STATE state = STATE.MENU;


	public void init(){

		icon = new ImageIcon("res/rightTank.jpg");
		greentnk = new ImageIcon("res/greenRight.png");
		ball = new ImageIcon("res/bullet.png");
		wall = new ImageIcon("res/Brick250.jpg");
		bullet = iconToBuffer(ball);
		tank = iconToBuffer(icon);
		brick = iconToBuffer(wall);
		greentank = iconToBuffer(greentnk);

		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		p1 = new Player1(200,200,this);
		p2 = new Player2(400,400,this);
		c= new Controller(this);
		c.createMap();
		ebullet = c.getEbullet();
		ebrick = c.getEbrick();
		menu = new Menu();

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
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
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
		if(state == STATE.GAME){
			g.clearRect (0, 0, getWidth(),getHeight());
			p1.render(g);
			p2.render(g);
			c.render(g);
			//b.render(g);
			g.drawImage(tank,(int)p1.getX(), (int)p1.getY() ,tank.getWidth() ,tank.getHeight(), this);
			g.drawImage(greentank,(int)p2.getX(), (int)p2.getY() ,greentank.getWidth() ,greentank.getHeight(), this);
		}
		if(state == STATE.MENU){
			menu.render(g);
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
	public void map(){
		for(int i = 0 ; i<120 ; i+=40){
			for(int j = 0 ; j<240; j+= 40){
				//c.addBrick(new Brick(i,j,this));
			}
		}

	}

	public void keyPressed(KeyEvent e)   {
		int key = e.getKeyCode();
		if(state == STATE.GAME){
			if (key == KeyEvent.VK_RIGHT)  	{
				p1.setvelX(1);
				p1.setvelY(0);
				turnRight = true;
				turnLeft = false;
				turnUp = false;
				turnDown = false;
				tank = iconToBuffer(rightTank);

				requestFocus();

			}
			else if (key == KeyEvent.VK_LEFT )  {
				p1.setvelX(-1);
				p1.setvelY(0);
				turnLeft = true;
				turnUp = false;
				turnDown = false;
				turnRight = false;
				icon = leftTank;
				tank = iconToBuffer(leftTank);

				requestFocus();

			}
			else if (key == KeyEvent.VK_DOWN )  {
				p1.setvelY(1);
				p1.setvelX(0);
				turnDown = true;
				turnLeft = false;
				turnUp = false;
				turnRight = false;
				tank = iconToBuffer(downTank);
				requestFocus();

			}
			else if (key == KeyEvent.VK_UP )  {
				p1.setvelY(-1);
				p1.setvelX(0);
				turnUp = true;
				turnDown = false;
				turnRight = false;
				turnLeft = false;
				tank = iconToBuffer(upTank);
				requestFocus();
			}
			else if(key == KeyEvent.VK_SPACE && !isShooting){
				isShooting = false;
				c.addEntity(new Bullet(p1.getX()+8,p1.getY()+8,this,turnRight,turnLeft,turnUp,turnDown,c));
             System.out.println(isShooting);

			}

			else if (key == KeyEvent.VK_D){
				p2.setvelX(1);
				p2.setvelY(0);
				turnRight1 = true;
				turnLeft1 = false;
				turnUp1 = false;
				turnDown1 = false;
				greentank = iconToBuffer(rightTank1);

				requestFocus();

			}
			else if (key == KeyEvent.VK_A )  {
				p2.setvelX(-1);
				p2.setvelY(0);
				turnLeft1 = true;
				turnUp1 = false;
				turnDown1 = false;
				turnRight1 = false;
				greentank = iconToBuffer(leftTank1);

				requestFocus();

			}
			else if (key == KeyEvent.VK_S )  {
				p2.setvelY(1);
				p2.setvelX(0);
				turnDown1 = true;
				turnLeft1 = false;
				turnUp1 = false;
				turnRight1 = false;
				greentank = iconToBuffer(downTank1);
				requestFocus();

			}
			else if (key == KeyEvent.VK_W )  {
				p2.setvelY(-1);
				p2.setvelX(0);
				turnUp1 = true;
				turnDown1 = false;
				turnRight1 = false;
				turnLeft1 = false;
				greentank = iconToBuffer(upTank1);
				requestFocus();
			}
			else if(key == KeyEvent.VK_Q && !isShooting1){
				isShooting1 = true;
				c.addEntity(new Bullet(p2.getX()+8,p2.getY()+8,this,turnRight1,turnLeft1,turnUp1,turnDown1,c));
				System.out.println(isShooting1);


			}
		}
	}

	public void keyReleased(KeyEvent e){

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT)  	{p1.setvelX(0);;}
		else if (key == KeyEvent.VK_LEFT)  {p1.setvelX(0);	}
		else if (key == KeyEvent.VK_DOWN)  {p1.setvelY(0);	}
		else if (key == KeyEvent.VK_UP)  {p1.setvelY(0);	}
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
