package no.mamot.swashbuckler;

import org.newdawn.slick.geom.Shape;

public interface GameObject {

	Shape getShape();

	void draw();
}