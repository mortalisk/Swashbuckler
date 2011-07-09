package no.mamot.swashbuckler;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Updateable;

public final class Robot implements GameObject, Drawable, Updateable {

	private boolean goingLeft = false;
	private Circle circle;
	private Body body;
	private float bodyRadius = 0;
	
	private Swashbuckler player = null;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(-50000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(50000, 0);

	Robot(String imageFile, String entityName, float radius, float x, float y, Vector2f maxVelocity, Swashbuckler player)
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
		
		this.player = player;
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
	
	@Override
	public void draw(Graphics g) {
		getImage().draw(body.getPosition().getX() - 10, body.getPosition().getY() - bodyRadius * 1.5f);
		ShapeRenderer.draw(this.getShape());
	}

	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}

	@Override
	public void addPhysics(World world) {
		world.add(body);
	}

	public final Image getImage() {
		if (goingLeft) {
			return imageLeft;
		} else {
			return imageRight;
		}		
	}
	
	private Shape getShape() {
		circle.setX(body.getPosition().getX() - bodyRadius);
		circle.setY(body.getPosition().getY() - bodyRadius);
		return circle;
	}

	@Override
	public void update(int delta) {
		//TODO: initiate AI stuff here???
		if (body.getPosition().distance(player.getPosition()) <= player.getViewRadius()) {		
			if (player.getPosition().getX() <= this.getPosition().getX()) {
				left(delta);			
			} else {
				right(delta);
			}
			
			if (player.getPosition().getY() <= this.getPosition().getY()) {
				jump();			
			}
		}					
	}
	
}
