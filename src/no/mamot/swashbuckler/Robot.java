package no.mamot.swashbuckler;

import java.util.Random;

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
	private float strength = 0;
	private float attackSpeed = 0;
	private float attackTimer = 0;
	private float attackSkill = 0;
	
	private Swashbuckler player = null;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(-50000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(50000, 0);
	
	Random randomGenerator = new Random();

	Robot(String imageFile, String entityName, float radius, float x, float y, Vector2f maxVelocity, Swashbuckler player, float strength, float attackSpeed, float attackSkill)
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
		this.strength = strength;
		this.attackSpeed = attackSpeed;
		this.attackSkill = attackSkill;
	}
	
	public void left(int delta) {
		goingLeft = true;
		body.addForce(leftForce);
		
		//TODO: play walking sound???
	}

	public void right(int delta) {
		goingLeft = false;
		body.addForce(rightForce);
		
		//TODO: play walking sound???
	}
	
	public void jump() {
		//only allow jumping if the body is currently touching something		
		if (body.isResting()) {
			body.addForce(jumpForce);
			body.setIsResting(false);
			
			//TODO: play jump sound
		}		
	}
	
	@Override
	public void draw(Graphics g) {
		getImage().draw(body.getPosition().getX() - 10, body.getPosition().getY() - bodyRadius * 1.5f);
		//ShapeRenderer.draw(this.getShape());
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
			if (player.getPosition().getX() <= (this.getPosition().getX() - (bodyRadius + player.getPlayerRadius()) )) { //player is to the left of the robot
				left(delta);			
			} else if (player.getPosition().getX() >= (this.getPosition().getX() + (bodyRadius + player.getPlayerRadius()) )) { //player is to the right of the robot
				right(delta);
			} else if (body.getPosition().distance(player.getPosition()) <=  (bodyRadius + player.getPlayerRadius())) { //robot is close enough to attack
				int random = (randomGenerator.nextInt(100) + 1); //random number which is used to check against the attackSkill
				System.out.println("random: " + random + ", attackTimer: " + attackTimer);
				
				if (attackTimer <= 0) {
					if(random <= attackSkill) {
						player.removeHP(strength);
					}					
					
					attackTimer = 100 * delta; //reset attacktimer
				} else {
					attackTimer -= attackSpeed; //it is not the time to attack yet, decrease the timer
				}
			}
			
			if (player.getPosition().getY() <= this.getPosition().getY()) { //check if robot must jump to get to the player
				jump();			
			}
		}
	}
	
}
