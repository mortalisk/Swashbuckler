package no.mamot.swashbuckler;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public final class GameObstacle extends GameObject {

	private Shape polygon;

	public GameObstacle(float[] arg0) throws SlickException {
		super(null, 0, 0);
		polygon = new Polygon(arg0);
	}

	public void draw() {
		ShapeRenderer.draw(this.getShape());
	}

	@Override
	public Shape getShape() {
		return polygon;
	}

	@Override
	public void setPosition(float x, float y) {
		polygon.setX(x);
		polygon.setY(y);

	}

}
