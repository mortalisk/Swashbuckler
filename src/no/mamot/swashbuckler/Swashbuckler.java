package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Graphics;
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
public final class Swashbuckler implements GameObject, Drawable {

	private boolean goingLeft = false;
	private Circle circle;
	private Body body;
	private float bodyRadius = 0;
	private float viewRadius = 0;
	private float playerScore = 0;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(-50000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(50000, 0);

	Swashbuckler(String imageFile, String entityName, float radius, float viewRadius, float x, float y, Vector2f maxVelocity)
			throws SlickException {
		if (imageFile != null) {
			imageLeft = new Image(imageFile);
			imageRight = imageLeft.getFlippedCopy(true, false);		
		}
		circle = new Circle(x, y, radius);
		bodyRadius = radius;
		
		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(radius);
		body = new Body(entityName,physCircle, 100);		
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
		body.setIsResting(false);
		body.setMaxVelocity(maxVelocity.x, maxVelocity.y);
		
		this.viewRadius = viewRadius;
	}
	
	public final void draw(Graphics g) {
		getImage().draw(body.getPosition().getX() - 10, body.getPosition().getY() - bodyRadius* 1.5f);
		ShapeRenderer.draw(this.getShape());
	}

	private Shape getShape() {
		circle.setX(body.getPosition().getX() - bodyRadius);
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
			body.addForce(jumpForce);
			body.setIsResting(false);
		}		
	}

	public void goTo(float x, float y) {
		body.setPosition(x, y);
	}
	
	public float getViewRadius() {
		return this.viewRadius;
	}
	
	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}

	@Override
	public void addPhysics(World world) {
		world.add(body);
	}
	
	public float getScore() {
		return playerScore;
	}
	
	public void addScore(float score) {
		playerScore += score;
	}
}