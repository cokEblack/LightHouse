package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Paddel extends GameObject {
public Paddel(int x,int y, ID id) {
	super(x,y,id);
	
	
}
	public void render(Graphics g) {
       
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,64,10);
        

    

}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,10);
		
	
	
	}
	
	public void tick() {
		x+=velX;
		//y+=velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-80);
		
	}
}
