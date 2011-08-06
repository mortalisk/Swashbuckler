package no.mamot.swashbuckler;

import java.util.Random;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Updateable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

public final class Rhinobot extends GameEntity implements GameObject, Drawable,
		Updateable {

	private boolean goingLeft = false;
	private float bodyRadius = 0;
	private float viewRadius = 0;
	private float strength = 0;
	private float attackSpeed = 0;
	private float attackTimer = 0;
	private float attackSkill = 0;
	
	private Sound jump = null;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(
			0, -1500000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(
			-40000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(
			40000, 0);

	Random randomGenerator = new Random();

	public Rhinobot() throws SlickException {
		this("/data/Robots/Rhinobot.png", "Rhinobot", 17.0f, 300.0f, 370.0f,
				180.0f, new org.newdawn.slick.geom.Vector2f(400, 500), 1.0f,
				25.0f, 75.0f);
	}

	Rhinobot(String imageFile, String entityName, float radius, float viewRadius,
			float x, float y, Vector2f maxVelocity, float strength,
			float attackSpeed, float attackSkill) throws SlickException {
		if (imageFile != null) {
			imageLeft = new Image(imageFile);
			imageRight = imageLeft.getFlippedCopy(true, false);
		}
		bodyRadius = radius;
		
		net.phys2d.raw.shapes.Circle physCircle = new net.phys2d.raw.shapes.Circle(
				radius);
		body = new Body(entityName, physCircle, 100);
		body.setRotatable(false);
		body.setFriction(0.5f);
		body.setPosition(x, y);
		body.setIsResting(false);
		body.setMaxVelocity(maxVelocity.x, maxVelocity.y);

		this.strength = strength;
		this.attackSpeed = attackSpeed;
		this.attackSkill = attackSkill;
		this.viewRadius = viewRadius;

		jump = new Sound("data/Sound/Robot_Jump.wav");
	}

	public void left(int delta) {
		goingLeft = true;
		body.addForce(leftForce);

		// TODO: play walking sound???
	}

	public void right(int delta) {
		goingLeft = false;
		body.addForce(rightForce);

		// TODO: play walking sound???
	}

	public void jump() {
		// only allow jumping if the body is currently touching something
		if (body.isResting()) {
			body.addForce(jumpForce);
			body.setIsResting(false);

			// TODO: play jump sound
			// jump.play();
		}
	}

	@Override
	public void draw(Graphics g) {
		getImage().draw(body.getPosition().getX() - 20,
				body.getPosition().getY() - bodyRadius * 1.5f);
		//ShapeRenderer.draw(this.getShape());
	}

	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}

	public final Image getImage() {
		if (goingLeft) {
			return imageLeft;
		} else {
			return imageRight;
		}
	}
	
	@Override
	public void update(int delta) {
		// TODO: initiate AI stuff here???
		if (body.getPosition().distance(player.getPosition()) <= viewRadius) {
			if (player.getPosition().getX() <= (this.getPosition().getX() - (bodyRadius + player
					.getPlayerRadius()))) { // player is to the left of the
											// robot
				left(delta);
			} else if (player.getPosition().getX() >= (this.getPosition()
					.getX() + (bodyRadius + player.getPlayerRadius()))) { // player
																			// is
																			// to
																			// the
																			// right
																			// of
																			// the
																			// robot
				right(delta);
			} else if (body.getPosition().distance(player.getPosition()) <= (bodyRadius + player
					.getPlayerRadius())) { // robot is close enough to attack
				int random = (randomGenerator.nextInt(100) + 1); // random
																	// number
																	// which is
																	// used to
																	// check
																	// against
																	// the
																	// attackSkill
				System.out.println("random: " + random + ", attackTimer: "
						+ attackTimer);

				if (attackTimer <= 0) {
					if (random <= attackSkill) {
						player.doDamage((int)strength);
					}

					attackTimer = 100 * delta; // reset attacktimer
				} else {
					attackTimer -= attackSpeed; // it is not the time to attack
												// yet, decrease the timer
				}
			}

			if (player.getPosition().getY() <= this.getPosition().getY()) { // check
																			// if
																			// robot
																			// must
																			// jump
																			// to
																			// get
																			// to
																			// the
																			// player
				jump();
			}
		}
	}

}
