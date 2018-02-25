package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class Ball extends GameObject {

	private Handler handler;
	private GameObject paddel;

	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 3;
		for (GameObject go : handler.object) {
			if (go.getId() == ID.Paddel)
				paddel = go;
		}

	}

	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillOval((int)x,(int) y, 12, 12);

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 12, 12);
	}

	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y > Game.HEIGHT - 32)
			velY *= -1;
		if (x <= 0 || x > Game.WIDTH - 24)
			velX *= -1;

		collision();

	}

	private void collision() {
		if (paddel.getBounds().intersects(this.getBounds())) {
			bounce();
		}
	}
	private void bounce() {
		
			int  paddleLPos = (int)paddel.getBounds ().getMinX ();
			int  ballLPos = (int)getBounds ().getMinX ();
			int  first = paddleLPos + 8;
			int  second = paddleLPos + 16;
			int  third = paddleLPos + 24;
			int  fourth = paddleLPos + 32;
			if (ballLPos  < first) {
			setVelX (-1);
			setVelY (getVelY()*-1);
			}
			if (ballLPos  >= first &&  ballLPos  < second) {
			setVelX (-1);
			setVelY(-1 * getVelY ());
			}
			if (ballLPos  >= second  &&  ballLPos  < third) {
			setVelX (0);
			setVelY (getVelY()*-1);
			}
			if (ballLPos  >= third &&  ballLPos  < fourth) {
			setVelX (1);
			setVelY(-1 * getVelY ());
			}
			if (ballLPos  > fourth) {
			setVelX (1);
			setVelY (getVelY()*-1);
			}
			}
		
	}













