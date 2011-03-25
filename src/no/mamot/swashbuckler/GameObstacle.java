package no.mamot.swashbuckler;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public final class GameObstacle implements GameObject {

	private Shape polygon;

	public GameObstacle(float[] arg0) throws SlickException {
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
