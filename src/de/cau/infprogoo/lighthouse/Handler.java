package de.cau.infprogoo.lighthouse;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class Handler {
List<GameObject> object = new CopyOnWriteArrayList<GameObject>();

public void tick() {
	for(Iterator<GameObject> goIterator = object.iterator(); goIterator.hasNext();){
		GameObject go = goIterator.next();
		go.tick();
		
	}
}

public void render(Graphics g) {
	for(GameObject go : object) {
		go.render(g);
}
}

public void addObject(GameObject object) {
	this.object.add(object);
	
}

public void removeObject(GameObject object) {
	this.object.remove(object);
	
}


	


	

}
