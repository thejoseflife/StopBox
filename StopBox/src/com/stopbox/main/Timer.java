package com.stopbox.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Timer extends GameObject {
	
	public static int TIMER = 0;
	private int x;
	private int y;
	private static int timein = 0;
	
	public Timer(int x, int y, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
	}

	@Override
	public void tick() {
		TIMER = GameSB.clamp(TIMER, 0, 139);
		TIMER += timein;
		
	}
	
	public static void increaseTime(int input) {
		timein = input;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x + 1, y + 1, TIMER, 29);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 140, 30);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Rectangle getBounds1() {
		return null;
	}

	@Override
	public Rectangle getBounds2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBounds3() {
		// TODO Auto-generated method stub
		return null;
	}

}
