package no.mamot.swashbuckler.weapon;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import no.mamot.engine.GameObject;
import no.mamot.engine.Level;
import no.mamot.swashbuckler.GameEntity;
import no.mamot.swashbuckler.Swashbuckler;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class LightningStrike extends GameEntity implements WeaponAttack{

	private Image image = null;
	private ParticleSystem system;
	private int heal = 5;
	private boolean particleEnable = true;
	private long timeWhenSpawned = 0;
	private Shape thisShape;
	private boolean collision = false;
	
	
	
	public LightningStrike (Swashbuckler player) {
		try {
			image = new Image("data/particles/Lightning.png", false);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		system = new ParticleSystem(image);	
		Circle shape = new Circle(20);
		body = new Body(shape,1000.0f);
		body.setEnabled(true);
		timeWhenSpawned = System.currentTimeMillis();
		
		
		
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
		gravity.setValue(0.0f);		
		HealEmitterValue angle = new HealEmitterValue();
		angle.setValue(60.0f);		
		HealEmitterValue growth = new HealEmitterValue();
		growth.setValue(-44.0f);		
		healEmitter.gravityFactor = gravity;
		healEmitter.angularOffset = angle;
		healEmitter.growthFactor = growth;
		healEmitter.spawnCount.setMin(20.0f);
		healEmitter.spawnCount.setMax(20.0f);
		healEmitter.initialSize.setMin(13.0f);
		healEmitter.initialSize.setMax(13.0f);
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
		
		ShapeRenderer.draw( new org.newdawn.slick.geom.Circle
				(body.getPosition().getX(), body.getPosition().getY(), 20));
		
		if (collision){
			ShapeRenderer.draw( new org.newdawn.slick.geom.Circle
					(body.getPosition().getX(), body.getPosition().getY(), 10));
		}
		
		if (particleEnable){
			system.render();
		}		
		else {
			
		}
	}
	@Override
	public void update(int delta) {
		system.update(delta);		
		long time = System.currentTimeMillis();
		float distance = player.getPosition().distance(getPosition());
		if((time-timeWhenSpawned) > 400 ) {
			setEnableParticleEffect(false);
			collision = false;
			
		}
		
		
		
	
	}
	
	private void remove(){
		playerLevel.getDrawableList().remove(this);
		playerLevel.getUpdatableList().remove(this);
		playerLevel.getGameObjectList().remove(this);
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
		collision = true;
	}
	@Override
	public void attack(float mouseX, float mouseY) {
		world.remove(body);
		body.setPosition(mouseX, mouseY);
		world.add(body);
		system.setPosition(mouseX, mouseY);
		timeWhenSpawned = System.currentTimeMillis();
		setEnableParticleEffect(true);
		float x = body.getPosition().getX();
		float y = body.getPosition().getY();
		x -= playerLevel.getMan().getPosition().getX();
		y -= playerLevel.getMan().getPosition().getX();
		x = x*1000;
		y = y *1000;
		body.addForce(new Vector2f(x, y));
	}

}