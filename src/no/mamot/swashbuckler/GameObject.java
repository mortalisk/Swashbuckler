package no.mamot.swashbuckler;

import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

	private Shape shape;
	
	public boolean detectCollision(GameObject collidingWith) {
		Shape other = collidingWith.getShape();
		if (this.getShape().intersects(other)) {
			//System.out.println("collition with " + other.toString());
			return true;
		}
		
		return false;
	}
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
}
