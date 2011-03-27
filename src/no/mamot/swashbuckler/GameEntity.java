package no.mamot.swashbuckler;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
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

	private boolean goingLeft = false;
	private Circle circle;
	private Body body;
	private float bodyRadius = 0;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(-50000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(50000, 0);

	GameEntity(String imageFile, String entityName, float radius, float x, float y, Vector2f maxVelocity)
			throws SlickException {
		if (imageFile != null) {
			imageLeft = new Image(imageFile);
			imageRight = imageLeft.getFlippedCopy(true, false);		
		}			
		circle = new Circle(x, y, radius);
		bodyRadius = radius;
		
		//Box physBox = new Box(radius, radius*2);
		//body = new Body(entityName,physBox, 100);
		
		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(radius);
		body = new Body(entityName,physCircle, 1);		
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
		body.setIsResting(false);
		body.setMaxVelocity(maxVelocity.x, maxVelocity.y);
	}
	
	public final void draw() {
		//getImage().draw(body.getPosition().getX() - 10, body.getPosition().getY() - bodyRadius);
		ShapeRenderer.draw(this.getShape());
	}

	private Shape getShape() {
		circle.setX(body.getPosition().getX() - 16);
		circle.setY(body.getPosition().getY() - bodyRadius);
		return circle;
	}

	public final Image getImage() {
		if (goingLeft) {
			return imageLeft;
		} else {
			return imageRight;
		}		
	}

	public final void setImage(Image image) {
		this.imageLeft = image;
		this.imageRight = image.getFlippedCopy(true, false);
	}

	public Body getBody() {
		return body;
	}

	public void left(int delta) {
		goingLeft = true;
		body.addForce(leftForce);
	}

	public void right(int delta) {
		goingLeft = false;
		body.addForce(rightForce);
	}
	
	public void jump() {
		//only allow jumping if the body is currently touching something		
		if (body.isResting()) {
			body.addForce(jumpForce );
			body.setIsResting(false);
		}		
	}

	public void goTo(float x, float y) {
		body.setPosition(x, y);
	}
}
