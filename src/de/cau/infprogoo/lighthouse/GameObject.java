package de.cau.infprogoo.lighthouse;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected double x,y;
	protected ID id;
	protected double velX,velY;
	protected boolean marked;
	
	
	
	public GameObject(int x , int y , ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
	
}
