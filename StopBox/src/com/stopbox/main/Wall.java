package com.stopbox.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Wall extends GameObject {

	private Handler handler;
	private Player player;
	
	private int height;
	private int x;
	
	private Random r = new Random();
	
	private int rc = r.nextInt(255 + 1);
	private int gc = r.nextInt(255 + 1);
	private int bc = r.nextInt(255 + 1);
	
	private Color randomcolor = new Color(rc, gc, bc);
	
	private Rectangle scorebounds;
	
	public Wall(int x, int y, ID id, int height, Player player, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.height = height;
		this.handler = handler;
		this.player = player;

	}

	@Override
	public void tick() {
		y += velY;
		x += velX;
		
		if(GameSB.state == GameSB.STATE.Dead) {
			handler.removeObject(this);
			player.setY(GameSB.HEIGHT/2 - 50);
			this.setVelX(0);
		}
		
		if(x < 0) {
			handler.removeObject(this);
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLACK);
		
		scorebounds = new Rectangle(x + 20, 0, 1, 480);
		g2d.draw(scorebounds);
		
		g.setColor(randomcolor);
		
		g.fillRect(x, 0, 20, height);
		g.fillRect(x, height + 110, 20, 380);
		
	}
	

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, height + 110, 20, 380);
		
	}
	@Override
	public Rectangle getBounds1() {
		return new Rectangle(x, 0, 20, height);
	}
	
	@Override
	public Rectangle getBounds2() {
			return new Rectangle(this.x + 20, 0, 5, 480);
	} 
	
	@Override
	public Rectangle getBounds3() {
		return new Rectangle(this.x + 30, 0, 15, 480);
	}
	
}
