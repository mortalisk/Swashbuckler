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
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class Heal extends GameEntity {

	private Image image = null;
	private ParticleSystem system;
	private int heal = 5;
	private boolean particleEnable = true;
	private long timeWhenHealedLast = 0;
	private Sound healSound = null;
	
	
	
	public Heal () throws SlickException{
		image = new Image("data/particles/Heal.png", false);
		system = new ParticleSystem(image);	
		Box shape = new Box(20, 40);
		body = new StaticBody(shape);
		body.setEnabled(false);
		healSound = new Sound("data/Sound/healing.wav");
		
		
	}	
	

	private class HealEmitterValue implements ConfigurableEmitter.Value  {		
		private float value = 0.0f;
		public void setValue(float value){
			this.value = value;
		}
		@Override
		public float getValue(float time) {
			return value;
		}	
	}
	
	
	public void init() {
		system.setPosition(body.getPosition().getX(), body.getPosition().getY());
		// x y might just be relative to the position of the system....	
		ConfigurableEmitter healEmitter = new ConfigurableEmitter("Heal");
		HealEmitterValue gravity = new HealEmitterValue();
		gravity.setValue(-5.0f);		
		HealEmitterValue angle = new HealEmitterValue();
		angle.setValue(60.0f);		
		HealEmitterValue growth = new HealEmitterValue();
		growth.setValue(10.0f);		
		healEmitter.gravityFactor = gravity;
		healEmitter.angularOffset = angle;
		healEmitter.growthFactor = growth;
		healEmitter.spawnCount.setMin(1.0f);
		healEmitter.spawnCount.setMax(1.0f);
		healEmitter.initialSize.setMin(20.0f);
		healEmitter.initialSize.setMax(20.0f);
		healEmitter.spawnInterval.setMin(250.0f);
		healEmitter.spawnInterval.setMax(250.0f);
		healEmitter.initialLife.setMin(1000.0f);
		healEmitter.initialLife.setMax(1000.0f);
		healEmitter.speed.setMin(50.0f);
		healEmitter.speed.setMax(50.0f);
		system.addEmitter(healEmitter);
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
		if((time-timeWhenHealedLast) > 100 && distance < 35) {
			player.addHP(heal);
			timeWhenHealedLast = time;
			healSound.play();
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
		
	}

}
