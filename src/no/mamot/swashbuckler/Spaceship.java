package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Spaceship extends GameEntity {

	private float bodyRadius = 0;
	private Image image = null;

	public Spaceship() throws SlickException {
		this("/data/Swashbuckler/Spaceship.png", "Spaceship1", 20.0f, 800.0f,
				157.0f);
	}

	public Spaceship(String imageFile, String entityName, float radius,
			float x, float y) throws SlickException {

		if (imageFile != null) {
			image = new Image(imageFile);
		}
		bodyRadius = radius;

		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(
				radius);
		body = new Body(entityName, physCircle, 100);
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
		body.setIsResting(true);
	}

	@Override
	public void draw(Graphics g) {
		getImage().draw(body.getPosition().getX() - 10,
				body.getPosition().getY() - bodyRadius * 1.5f);
	}

	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}

	public final Image getImage() {
		return image;
	}

	@Override
	public void update(int delta) {

	}

}
