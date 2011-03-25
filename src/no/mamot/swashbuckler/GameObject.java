package no.mamot.swashbuckler;

import org.newdawn.slick.geom.Shape;

/**
 * Represents all objects in a level.
 */
public interface GameObject {

	Shape getShape();

	void draw();
}