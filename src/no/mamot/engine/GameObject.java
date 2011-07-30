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

	void addPhysics(World world);

	ROVector2f getPosition();

	void collisionOccured(CollisionEvent event);

}