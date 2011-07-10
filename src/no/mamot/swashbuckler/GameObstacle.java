package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 * Non-interactive objects. I.e. a object that does not move.
 */
public final class GameObstacle implements GameObject, Drawable {

	private Shape polygon;
	private Body body;
	private Image texture;

	public GameObstacle(float[] arg0, String textureName) {
		polygon = new Polygon(arg0);
		ROVector2f[] physPoints = new ROVector2f[arg0.length / 2];
		for (int i = 0; i < physPoints.length; i++) {
			physPoints[i] = new Vector2f(arg0[i * 2], arg0[i * 2 + 1]);
		}
		net.phys2d.raw.shapes.Polygon physPolygon = new net.phys2d.raw.shapes.Polygon(
				physPoints);
		body = new StaticBody("WorldObstacle", physPolygon);
		body.setFriction(0.1f);

		try {
			texture = new Image("data/textures/" + textureName);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		// ShapeRenderer.draw(this.getShape());
		ShapeRenderer.texture(this.getShape(), texture);
	}

	private Shape getShape() {
		return polygon;
	}

	@Override
	public void addPhysics(World world) {
		world.add(body);
	}

	@Override
	public ROVector2f getPosition() {
		throw new UnsupportedOperationException(
				"GameObstacle has no posisiton per today");
	}

}
