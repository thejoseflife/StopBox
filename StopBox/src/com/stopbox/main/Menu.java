package com.stopbox.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle startbutton = new Rectangle(GameSB.WIDTH/2 - 100, GameSB.HEIGHT / 2 - 80, 200, 50);
	public Rectangle helpbutton = new Rectangle(GameSB.WIDTH/2 - 100, GameSB.HEIGHT / 2 + 20, 200, 50);
	public Rectangle quitbutton = new Rectangle(GameSB.WIDTH/2 - 100, GameSB.HEIGHT / 2 + 120, 200, 50);

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		Font Eras = new Font("Eras Bold ITC", Font.BOLD, 100);
		Font arial = new Font("arial", Font.BOLD, 30);
		Font a2 = new Font("arial", Font.BOLD, 20);
		
		g.setColor(Color.RED);
		g.setFont(Eras);
		g.drawString("Stop", 60, 100);
		g.setColor(Color.GREEN);
		g.drawString("Box!", GameSB.WIDTH/2 - 10, 100);
		g2d.setColor(Color.WHITE);
		g.setFont(a2);
		g.drawString("by Josef Sajonz", GameSB.WIDTH/2 + 90, 120);
		g.setFont(arial);
		g.drawString("Start", startbutton.x + 63, startbutton.y + 35);
		g.drawString("Help", helpbutton.x + 65, helpbutton.y + 35);
		g.drawString("Quit", quitbutton.x + 65, quitbutton.y + 35);
		g2d.draw(startbutton);
		g2d.draw(helpbutton);
		g2d.draw(quitbutton);
		
	}
	
}
