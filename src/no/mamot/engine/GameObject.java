package no.mamot.engine;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.forcesource.ForceSource;

import org.newdawn.slick.geom.Shape;

/**
 * Represents all objects in a level.
 */
public interface GameObject{

	void addPhysics(World world);
	
	ROVector2f getPosition();
	
}