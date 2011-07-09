package no.mamot.engine;

import net.phys2d.math.ROVector2f;

public interface Updateable {
	// For classes that need to be updated regularely
	void update(int delta);
	
	boolean inRange(ROVector2f playerPosition, float radius);
}
