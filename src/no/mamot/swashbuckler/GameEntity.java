package no.mamot.swashbuckler;

import net.phys2d.raw.Body;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Interactive objects in the level. I.e. objects that move. (the player or an
 * enemy)
 */
public final class GameEntity implements GameObject, Drawable {

	private Circle circle;
	private Body body;

	Vector2f before = new Vector2f();
	private Image image = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(-20000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(20000, 0);

	GameEntity(String imageFile, float radius, float x, float y)
			throws SlickException {
		if (imageFile != null)
			image = new Image(imageFile);
		circle = new Circle(x, y, radius);
		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(radius);
		body = new Body(physCircle, 100);
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
	}
	

	public final void draw() {
		getImage().draw(body.getPosition().getX(), body.getPosition().getY());
		ShapeRenderer.draw(this.getShape());
	}

	@Override
	public Shape getShape() {
		circle.setX(body.getPosition().getX());
		circle.setY(body.getPosition().getY());
		return circle;
	}

	public final Image getImage() {
		return image;
	}

	public final void setImage(Image image) {
		this.image = image;
	}

	public Body getBody() {
		return body;
	}

	public void left(int delta) {
		body.addForce(leftForce);
	}

	public void right(int delta) {
		body.addForce(rightForce);
	}
	
	public void jump() {
		body.addForce(jumpForce );
	}

	public void goTo(float x, float y) {
		body.setPosition(x, y);
	}
}
