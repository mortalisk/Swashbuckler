package no.mamot.engine;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;

/**
 * Represents all objects in a level.
 */
public interface GameObject {
	
	Body getBody();

	ROVector2f getPosition();
	
	void setWorld(World world);

	void collisionOccured(CollisionEvent event, GameObject other, World world);

}