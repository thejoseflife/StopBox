package com.stopbox.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {

	public static Rectangle menubutton = new Rectangle(10, GameSB.HEIGHT - 90, 100, 50);
	
	private String text1 = "Use 'Space', 'Up', and 'Down' keys to start game. When the bar";
	private String text2 = "in the top left corner fills up completely, you grow a bit larger,";
	private String text3 = "making it a bit harder to go through the obstacles.";
	
	private String text4 = "StopBox, coded by Josef Sajonz in Java, is an infinite arcade game,";
	private String text5 = "with the simple concept of dodging obstacles by stopping your cube";
	private String text6 = "(Player) so you can move up and down, but without taking too long.";
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
			
		Font Eras = new Font("Eras Bold ITC", Font.BOLD, 100);
		Font sEras = new Font("Eras Bold ITC", Font.PLAIN, 30);
		Font arial = new Font("arial", Font.BOLD, 30);
		Font a2 = new Font("arial", Font.BOLD, 20);
		Font textarial = new Font("arial", Font.PLAIN, 20);
		
		g.setColor(Color.RED);
		g.setFont(Eras);
		g.drawString("Stop", 60, 100);
		g.setColor(Color.GREEN);
		g.drawString("Box!", GameSB.WIDTH/2 - 10, 100);
		g2d.setColor(Color.WHITE);
		g.setFont(a2);
		g.drawString("by Josef Sajonz", GameSB.WIDTH/2 + 90, 120);
		g.setFont(arial);
		g.drawString("Menu", menubutton.x + 12, menubutton.y + 35);
		g2d.draw(menubutton);
		g.setFont(textarial);
		g.drawString(text1, 20, 150);
		g.drawString(text2, 20, 175);
		g.drawString(text3, 20, 200);
		g2d.setFont(sEras);
		g.drawString("About StopBox", 200, 240);
		g.setFont(textarial);
		g.drawString(text4, 20, 270);
		g.drawString(text5, 20, 295);
		g.drawString(text6, 20, 320);
		
	}
	
}
