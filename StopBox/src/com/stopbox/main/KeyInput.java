package com.stopbox.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	GameSB gameSB;

	private Handler handler;
	private GameSB game;
	
	public KeyInput(Handler handler, GameSB game) {
		this.handler = handler;
		this.game = game;
		
	}
	
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		
		if(GameSB.state == GameSB.STATE.Game) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject TempObject = handler.object.get(i);
				if(TempObject.id == ID.Player) {
					if(key == KeyEvent.VK_UP) {
					
						Timer.increaseTime(2);
					
						TempObject.setVelY(-8);

					} else if(key == KeyEvent.VK_DOWN) {
					
						Timer.increaseTime(2);
					
						TempObject.setVelY(8);
				
					}
				} else if(TempObject.id == ID.Wall) {
					if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
						TempObject.setVelX(0);
						
					} else if(key == KeyEvent.VK_SPACE) {
						TempObject.setVelX(-10);
					}
				}
			}
		} if(GameSB.state == GameSB.STATE.Dead) {
			if(key == KeyEvent.VK_R) {
				game.restartGame();
			}
		}
	}
	
	public void keyReleased(KeyEvent ke) {
		int key = ke.getKeyCode();
		
		if(GameSB.state == GameSB.STATE.Game) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject TempObject = handler.object.get(i);
				if(TempObject.getID() == ID.Player) {
					if(key == KeyEvent.VK_UP) {
					TempObject.setVelY(0);
					Timer.increaseTime(0);
				
					} else if(key == KeyEvent.VK_DOWN) {
						TempObject.setVelY(0);
						Timer.increaseTime(0);
					
					}
				} else if(TempObject.id == ID.Wall) {
					if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
						if(TempObject.id == ID.Wall) {
							if(GameSB.SCORE >= 20) {
								TempObject.setVelX(-12);
							} else if(GameSB.SCORE >= 50) {
								TempObject.setVelX(-15);
							} else if(GameSB.SCORE >= 100) {
								TempObject.setVelX(-20);
							} else {
								TempObject.setVelX(-10);
							}
							
						}
					}
				}
			} 
		}
	}
	
}

