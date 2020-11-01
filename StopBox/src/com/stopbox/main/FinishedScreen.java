package com.stopbox.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FinishedScreen {

	static Rectangle restartbutton = new Rectangle(GameSB.WIDTH/2 - 100, GameSB.HEIGHT/2 + 30, 200, 50);
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		Font arial = new Font("arial", Font.BOLD, 35);
		Font smarial = new Font("arial", Font.BOLD, 25);
		Font smrarial = new Font("arial", Font.PLAIN, 20);
		Font rarial = new Font("arial", Font.BOLD, 30);
		
		g.setFont(arial);
		g.setColor(Color.WHITE);
		g.drawString("Yup, you died!", GameSB.WIDTH/2 - 120, GameSB.HEIGHT/2 - 100);
		g.setFont(smarial);
		g.drawString("Your final score was: " + GameSB.SCORE, GameSB.WIDTH/2 - 240, GameSB.HEIGHT/2 - 30);
		g.drawString("High Score: " + GameSB.highSCORE, GameSB.WIDTH/2 + 70, GameSB.HEIGHT/2 - 30);
		g.setFont(smrarial);
		g.drawString("Press 'R' or 'Restart' to Restart", GameSB.WIDTH/2 - 142, GameSB.HEIGHT/2 + 15);
		g.setFont(arial);
		g.drawString("Restart", restartbutton.x + 40, restartbutton.y + 35);
		g2d.draw(restartbutton);
		g.setFont(rarial);
		g2d.draw(Help.menubutton);
		g.drawString("Menu", Help.menubutton.x + 12, Help.menubutton.y + 35);

	}

}
