package com.stopbox.main;
//Notes:
//


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

public class GameSB extends Canvas implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -2421751466973514721L;

	private BufferedImage spritesheet = null;
	private BufferedImage background = null;
	
	public static final int WIDTH = 640, HEIGHT = 480;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Player playerc;
	private Wall wall;
	private Timer timer;
	private Menu menu;
	private Help help;
	
	public static BufferedImage player;
	
	public static int SCORE = 0;
	public int timesLogged = 0;
	public static int highSCORE = 0;
	
	public static Random r = new Random();	
	
	//Finished Screen
	FinishedScreen fsc;
	//
	
	public void init() {
		handler = new Handler();
		playerc = new Player(WIDTH / 2 - 50, HEIGHT/2 - 50, ID.Player, handler);
		timer = new Timer(10, 10, ID.Timer);
		
		menu = new Menu();
		help = new Help();
		
		requestFocus();
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
			try {

				spritesheet = loader.loadImage("/sprite_sheet.png");

			} catch (IOException e) {
			e.printStackTrace();
			}
			
		SpriteSheet ss = new SpriteSheet(spritesheet);
		player = ss.grabImage(6, 1, 16, 16);
		
		for(int i = 0; i < 1000; i++) {
			wall = new Wall(500 + (i * 300), 0, ID.Wall, r.nextInt(381), playerc, handler);
			handler.addObject(wall);
		}
		
		handler.addObject(playerc);
		
		handler.addObject(timer);
		
		fsc = new FinishedScreen();

	}
	
	public enum STATE {
		Menu,
		Game,
		Dead,
		Help
	};
	
	static STATE state = STATE.Menu;
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		init();
		//fw.main(null);
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		
		if(SCORE > 990) {
			handler.addObject(wall);
		}
		if(SCORE > highSCORE) {
			highSCORE = SCORE;
		} else {
			
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g.create();
		
		//Graphics here v
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.drawImage(background, 0, 0, this);
		
		if(state == STATE.Game) { 
			handler.render(g);
			g.setColor(Color.WHITE);

		} else if(state == STATE.Dead) {
			fsc.render(g);
		} else if(state == STATE.Menu) {
			menu.render(g);
		} else if(state == STATE.Help) {
			help.render(g);
		}
		
		
		
		//Graphics here ^
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("StopBox");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		GameSB gameSB = new GameSB();
		frame.add(gameSB);
		frame.setVisible(true);
		gameSB.start();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(state == STATE.Dead) {
			if(e.getX() >= FinishedScreen.restartbutton.getX() && e.getX() <= FinishedScreen.restartbutton.getX() + 200
			&& e.getY() > FinishedScreen.restartbutton.getY() && e.getY() < FinishedScreen.restartbutton.getY() + 50) {
				restartGame();
				
			}
		} else if(state == STATE.Menu) {
			if(e.getX() >= menu.startbutton.getX() && e.getX() <= menu.startbutton.getX() + 200
			&& e.getY() >= menu.startbutton.getY() && e.getY() <= menu.startbutton.getY() + 50) {
				if(timesLogged > 0) {
				restartGame();
				}
				timesLogged++;
				state = STATE.Game;
			} else if(e.getX() >= menu.helpbutton.getX() && e.getX() <= menu.helpbutton.getX() + 200
			&& e.getY() >= menu.helpbutton.getY() && e.getY() <= menu.helpbutton.getY() + 50) {
				state = STATE.Help;
			} else if(e.getX() >= menu.quitbutton.getX() && e.getX() <= menu.quitbutton.getX() + 200
			&& e.getY() >= menu.quitbutton.getY() && e.getY() <= menu.quitbutton.getY() + 50) {
				System.exit(1);
			}
		} 
		
		//Menu Button code
		if(state == STATE.Help || state == STATE.Dead) {
			if(e.getX() >= Help.menubutton.getX() && e.getX() <= Help.menubutton.getX() + 100
					&& e.getY() >= Help.menubutton.getY() && e.getY() <= Help.menubutton.getY() + 50) {
				state = STATE.Menu;
			}
		}
		//Menu Button code
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("In");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("Out");
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//System.out.println("On");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("Off");
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.println("Drag");
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("Move");
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public void restartGame() {
		handler.removeObject(wall);
		handler.removeObject(playerc);
		handler.removeObject(timer);
		
		SCORE = 0;
		Timer.TIMER = 0;
		
		for(int i = 0; i < 1000; i++) {
			wall = new Wall(500 + (i * 300), 0, ID.Wall, r.nextInt(381), playerc, handler);
			handler.addObject(wall);
			
		}
		

		handler.addObject(playerc);
		playerc.setVelY(0);
		handler.addObject(timer);
		
		state = STATE.Game;
	}
}
