package no.mamot.swashbuckler;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 * Non-interactive objects. I.e. a object that does not move.
 */
public final class GameObstacle implements GameObject {

	private Shape polygon;

	public GameObstacle(float[] arg0) {
		polygon = new Polygon(arg0);
	}

	public void draw() {
		ShapeRenderer.draw(this.getShape());
	}

	@Override
	public Shape getShape() {
		return polygon;
	}

}
