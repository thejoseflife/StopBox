package com.stopbox.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.stopbox.main.GameSB.STATE;

public class Player extends GameObject {

	private Handler handler;
	@SuppressWarnings("unused")
	private FW fw;

	int height = 64;
	int width = 64;
	
	private boolean isCrossing = true;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		fw = new FW();
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(GameSB.state == STATE.Game) {
		collision();
			if(y + 10 == 0 || y >= GameSB.HEIGHT - 80) {
				GameSB.state = STATE.Dead;
			}
		}
		
		if(Timer.TIMER == 139) {
			this.x -= 5;
			this.y -= 5;
			width += 10;
			height += 10;
			
			Timer.TIMER = 0;
		}
		
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.id == ID.Wall) {
				if(getBounds().intersects(tempObject.getBounds()) || getBounds().intersects(tempObject.getBounds1())) {
					FW.main(null);
					GameSB.state = STATE.Dead;
				} if(getBounds1().intersects(tempObject.getBounds2()) && isCrossing) {
					Timer.TIMER = 0;
					GameSB.SCORE++;
					isCrossing = false;
				} else if(getBounds1().intersects(tempObject.getBounds3())) {
					isCrossing = true;
				}
				
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		Font arial = new Font("arial", Font.BOLD, 35);
		
		g.setFont(arial);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(GameSB.SCORE), GameSB.WIDTH/2 - 27, 100);
		
		g.drawImage(GameSB.player, x, y, width, height, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
		
	}

	@Override
	public Rectangle getBounds1() {
				return new Rectangle(this.x + 20, 0, 5, 480);
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
