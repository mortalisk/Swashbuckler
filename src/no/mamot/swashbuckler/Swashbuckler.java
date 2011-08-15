package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Updateable;
import no.mamot.swashbuckler.weapon.WeaponAttack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

/**
 * Interactive objects in the level. I.e. objects that move. (the player or an
 * enemy)
 */
public final class Swashbuckler extends GameEntity implements GameObject,
		Drawable, Updateable {

	private boolean goingLeft = false;
	private float bodyRadius = 0;
	private float playerScore = 0;
	private WeaponAttack weapon = null;

	private Sound jump = null;

	Vector2f before = new Vector2f();
	private Image imageLeft = null;
	private Image imageRight = null;
	private net.phys2d.math.Vector2f jumpForce = new net.phys2d.math.Vector2f(
			0, -1750000);
	private net.phys2d.math.Vector2f leftForce = new net.phys2d.math.Vector2f(
			-50000, 0);
	private net.phys2d.math.Vector2f rightForce = new net.phys2d.math.Vector2f(
			50000, 0);
	private boolean canJump;

	public Swashbuckler(String imageFile, String entityName, float radius,
			float x, float y, Vector2f maxVelocity, float playerHP)
			throws SlickException {
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

		this.health = playerHP;

		jump = new Sound("data/Sound/Swashbuckler_Jump3.wav");
	}

	public final void draw(Graphics g) {
		
		
		getImage().draw(body.getPosition().getX() - 10,
				body.getPosition().getY() - bodyRadius * 1.5f);
		// ShapeRenderer.draw(this.getShape());

		g.drawString("Score: " + getScore(), getPosition().getX() - (1024 / 2)
				+ 90.0f, getPosition().getY() - (768 / 2) + 9.0f);
		g.drawString("HP: " + getHP(), getPosition().getX() - (1024 / 2)
				+ 225.0f, getPosition().getY() - (768 / 2) + 9.0f);
		//g.drawRect(body.getPosition().getX(), body.getPosition().getY(), 2, 2);
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

		// TODO: play walking sound???
	}

	public void right(int delta) {
		goingLeft = false;
		body.addForce(rightForce);

		// TODO: play walking sound???
	}

	public void jump() {
		// only allow jumping if the body is currently touching something
		CollisionEvent[] events = world.getContacts(body);
		// the distance that contact point needs to be below center of man
		float distanceBelow = 10;
		
		for (CollisionEvent e : events) {
			if (e.getPoint().getY() > body.getPosition().getY() + distanceBelow){
				body.addForce(jumpForce);
				jump.play();
			}
		}
	}

	public void goTo(float x, float y) {
		body.setPosition(x, y);
	}

	public float getPlayerRadius() {
		return this.bodyRadius;
	}

	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}

	public float getScore() {
		return playerScore;
	}

	public void addScore(float score) {
		playerScore += score;
	}

	public float getHP() {
		return health;
	}

	public void addHP(int hp) {
		health += hp;
		if (health >100) {
			health = 100;
		}
		
		if (health <= 0) {
			// TODO: DEAD!
		}
	}
	
	public void doDamage(int amount) {
		addHP(-amount);
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void collisionOccured(CollisionEvent event, GameObject other,
			World world) {
		
		ROVector2f cn = event.getPoint();
		System.out.println(cn);
		System.out.println(event);
		
		if (cn.getY() > body.getPosition().getY()) {
			canJump = true;
		} else {
			canJump = false;
		}
		
	}
	
	public void setWeapon (WeaponAttack newAttack){
		this.weapon = newAttack;
	}

	public void attack(float X, float Y) {
		weapon.attack(X, Y);		
	}
}
