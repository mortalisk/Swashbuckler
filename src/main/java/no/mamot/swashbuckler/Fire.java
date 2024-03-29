package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

public class Fire extends GameEntity {

	private Image image = null;
	private ParticleSystem system;
	private int damage = -5;
	private boolean particleEnable = true;
	private long timeWhenDamagedLast;
	
	
	
	public Fire () throws SlickException{
		image = new Image("data/particles/Fire.png", false);
		system = new ParticleSystem(image);	
		Box shape = new Box(30, 40);
		body = new StaticBody(shape);
		body.setEnabled(false);
	}	
	

	public void init() {
		system.setPosition(body.getPosition().getX(), body.getPosition().getY());
		// x y might just be relative to the position of the system....	
		system.addEmitter(new FireEmitter(0, 0, 20.0f));
		system.setBlendingMode(ParticleSystem.BLEND_COMBINE);				
	}
	@Override
	public void draw(Graphics g) {
		if (particleEnable){
			system.render();
		}		
		else {
			g.drawImage(image, body.getPosition().getX(), body.getPosition().getY());
		}
	}
	@Override
	public void update(int delta) {
		system.update(delta);		
		
		long time = System.currentTimeMillis();
		float distance = player.getPosition().distance(getPosition());
		if((time-timeWhenDamagedLast) > 100 && distance < 35) {
			player.addHP(damage);
			timeWhenDamagedLast = time;
		}
	}
	


	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}
	public void setEnableParticleEffect(boolean enable){
		particleEnable = enable;
	}
	@Override
	public void collisionOccured(CollisionEvent event, GameObject other,World world) {
		if (other.equals(player)){
			player.addHP(damage);
		}
	}

}
