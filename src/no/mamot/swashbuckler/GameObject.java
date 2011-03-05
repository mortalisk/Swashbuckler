package no.mamot.swashbuckler;

import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

	private Shape shape;
	public abstract boolean detectCollision(GameObject collidingWith);
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
}
