package no.mamot.engine;

import net.phys2d.raw.Body;
import net.phys2d.raw.forcesource.ForceSource;

import org.newdawn.slick.geom.Shape;

/**
 * Represents all objects in a level.
 */
public interface GameObject{
	
	Body getBody();
	
}