package no.mamot.swashbuckler;

import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.World;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Level;
import no.mamot.engine.Updateable;

public abstract class GameEntity implements Drawable, Updateable, GameObject {

	protected Swashbuckler player = null;

	protected Level playerLevel;

	protected Body body;
	
	protected World world;
	
	float health = 100.0f;

	public void init() {
		
	}
	
	@Override
	public Body getBody() {
		return body;
	}

	public Swashbuckler getPlayer() {
		return player;
	}

	public void setPlayer(Swashbuckler player) {
		this.player = player;
	}

	public Level getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}

	public void setPosition(float x, float y) {
		body.setPosition(x, y);
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	@Override
	public void collisionOccured(CollisionEvent event, GameObject other, World world) {
		
	}
	
	@Override
	public boolean collidesWithMan() {
		return true;
	}
	
	@Override
	public boolean collides() {
		return true;
	}
	
	@Override
	public void takeDamage(int amount) {
		health -= amount;
		
		if (health <= 0) {
			die();
		}
	}

	private void die() {
			// TODO make fancy animation
		playerLevel.removeObject(this);
		world.remove(this.getBody());
		System.out.println("I am dead " + this);
	}

}
