package de.cau.infprogoo.lighthouse;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	public KeyInput(Handler handler) {
		this.handler =handler;
	}
	
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		
		for(GameObject go: handler.object) {
			if(go.getId() == ID.Paddel) {
				if(key == KeyEvent.VK_LEFT) go.setVelX(-5);
				if(key == KeyEvent.VK_RIGHT) go.setVelX(5);
			}
		}
		
		
		
	}
	
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		
		for(GameObject go: handler.object) {
			if(go.getId() == ID.Paddel) {
				if(key == KeyEvent.VK_LEFT) go.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) go.setVelX(0);
			}
		}
	}

}
