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

public class Tourmaline implements GameObject, Drawable, Updateable {

	private Circle circle;
	private Body body;
	private float bodyRadius = 0;
	private float value = 0;
	
	private Swashbuckler player = null;
	
	private Image image = null;
	
	public Tourmaline(String imageFile, String entityName, float radius, float x, float y, float value, Swashbuckler player)
			throws SlickException {
		
		if (imageFile != null) {
			image = new Image(imageFile);	
		}
		circle = new Circle(x, y, radius);
		bodyRadius = radius;
		
		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(radius);
		body = new Body(entityName,physCircle, 100);		
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
		body.setIsResting(false);
		
		this.player = player;
		
		this.value = value;
	}
	
	
	@Override
	public void update(int delta) {
		if ((body.getPosition().distance(player.getPosition())) <= bodyRadius) {		
			//add tourmaline to player and remove tourmaline from the world
			image = null;
			player.addScore(value);
		}	
	}

	@Override
	public void draw(Graphics g) {
		try {
			getImage().draw(body.getPosition().getX() - 10, body.getPosition().getY() - bodyRadius * 1.5f);
			ShapeRenderer.draw(this.getShape());
		} catch (Exception ex) {} finally {}
	}

	@Override
	public void addPhysics(World world) {
		//world.add(body);
	}

	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}
	
	public final Image getImage() {
			return image;	
	}
	
	private Shape getShape() {
		circle.setX(body.getPosition().getX() - bodyRadius);
		circle.setY(body.getPosition().getY() - bodyRadius);
		return circle;
	}

}