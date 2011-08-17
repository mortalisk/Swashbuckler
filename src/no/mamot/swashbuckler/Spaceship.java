package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Updateable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;

public class Spaceship extends GameEntity implements GameObject, Drawable,
Updateable {

	private float bodyRadius = 0;
	private Image image = null;
	private boolean flying;
	private boolean spinning;
	private double theta;
	private float size = 1.0f;

	private boolean levelEnded;
	//This is a small hack from Morten to avoid a lot of news
	private Vector2f posisionAdjustVector;
	private float radius; // a radius used for spinning when swashbuckler enters, not to be confused with bodyRadius


	public Spaceship() throws SlickException {
		this("/data/Swashbuckler/Spaceship.png", "Spaceship1", 10.0f, 800.0f,
				157.0f);
		this.posisionAdjustVector = new Vector2f(0, 0);//This should be the only new for this vector
	}

	public Spaceship(String imageFile, String entityName, float bodyRadius,
			float x, float y) throws SlickException {

		if (imageFile != null) {
			image = new Image(imageFile);
		}
		this.bodyRadius = bodyRadius;
		this.radius = 0;

		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(
				bodyRadius);
		body = new StaticBody(entityName, physCircle);
		body.setEnabled(true);
	}

	@Override
	public void draw(Graphics g) {
		
		if (levelEnded) {
			g.drawString("Refocus!", player.getPosition().getX() - (1024 / 2)
					+ 400,  player.getPosition().getY() - (768 / 2) +400);
		}
		
		getImage().draw(body.getPosition().getX() - 10,
				body.getPosition().getY() - bodyRadius * 1.5f, size);
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
		if (flying) {
			posisionAdjustVector.set(10, -10);
			body.adjustPosition(posisionAdjustVector);
			player.setPosition(body.getPosition().getX(), body.getPosition().getY());
			size += 0.1;
			if (size >= 20) {
				endLevel();
			}
		}else if (spinning){
			posisionAdjustVector.set((float)Math.cos(theta) * radius,(float) Math.sin(theta) * radius);
			body.adjustPosition(posisionAdjustVector);
			
			theta += 1 /Math.PI;
			radius += 0.1;
			if(radius > 20){
				flying = true;
				spinning = false;
			}
			player.setPosition(body.getPosition().getX(), body.getPosition().getY());
		}
		
	}
	
	private void endLevel() {
		levelEnded = true;
		 spinning = false;
		 flying = false;
		System.out.println("END OF LINE");
	    playerLevel.setFinished(true);
	   
	}

	@Override
	public void collisionOccured(CollisionEvent event, GameObject other,
			World world) {
		if (other == player) {
			playerLevel.getDrawableList().remove(player);
			flyAway();
		}
	}

	private void flyAway() {
		spinning = true;
	}

}
