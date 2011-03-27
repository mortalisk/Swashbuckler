package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 * Non-interactive objects. I.e. a object that does not move.
 */
public final class GameObstacle implements GameObject, Drawable {

	private Shape polygon;
	private Body body;

	public GameObstacle(float[] arg0) {
		polygon = new Polygon(arg0);
		ROVector2f[] physPoints = new ROVector2f[arg0.length/2];
		for (int i = 0; i < physPoints.length; i++) {
			physPoints[i] = new Vector2f(arg0[i*2], arg0[i*2+1]);
		}
		net.phys2d.raw.shapes.Polygon physPolygon = new net.phys2d.raw.shapes.Polygon(physPoints);
		body = new StaticBody("WorldObstacle",physPolygon);
		body.setFriction(0.1f);
	}

	public void draw() {
		ShapeRenderer.draw(this.getShape());
	}

	private Shape getShape() {
		return polygon;
	}

	@Override
	public Body getBody() {
		return body;
	}

}
