package de.cau.infprogoo.lighthouse;

import java.awt.*;
import java.util.Iterator;

import javax.swing.JFrame;

public class Bricks extends GameObject {
GameObject ball;
 Handler handler;
 public Bricks(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}


public void render ( Graphics g) {

	 g.setColor(new Color((int)y, 120, 120));
	 g.fillRect((int)x,(int) y, 32, 8);
	collision();
	
	}
 


public void tick() {
	collision();
	
	//this.setMarked(false);
	
}

private void collision() {
	for (Iterator<GameObject> iterator = handler.object.iterator(); iterator.hasNext();) {
	    GameObject go = iterator.next();
	    boolean collision = getBounds().intersects(go.getBounds());
	    if (go.getId() == ID.Ball && collision) {
	        // Remove the current element from the iterator and the list.
	    	go.setVelY(go.getVelY()*(-1) );
	        handler.removeObject(this);
}}}



public Rectangle getBounds() {
	
	return new Rectangle((int)x,(int)y,32,8);
}
 
}
