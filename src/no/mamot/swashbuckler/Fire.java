package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

public class Fire extends GameEntity {

	private Image image = null;
	private ParticleSystem system;
	private int damage = 5;
	private boolean particleEnable = true;
	
	
	public Fire (float x, float y) throws SlickException{
		image = new Image("data/particles/Fire.png", false);
		system = new ParticleSystem(image);
		system.setPosition(x, y);
		// x y might just be relative to the position of the system....	
		system.addEmitter(new FireEmitter(0, 0, 20.0f));
		system.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		
		Box shape = new Box(30, 40);
		body = new StaticBody(shape);
		body.setPosition(x, y);
		
	}	
	@Override
	public void draw(Graphics g) {
		if (!particleEnable){
			system.render();
		}		
		else {
			g.drawImage(image, body.getPosition().getX(), body.getPosition().getY());
		}
	}
	@Override
	public void update(int delta) {
		system.update(delta);
	}
	@Override
	public void addPhysics(World world) {
		world.add(body);
		
	}
	@Override
	public ROVector2f getPosition() {
		return body.getPosition();
	}
	public void setEnableParticleEffect(boolean enable){
		particleEnable = enable;
	}

}
