package no.mamot.swashbuckler.editor;

import net.phys2d.math.ROVector2f;
import no.mamot.engine.Drawable;
import no.mamot.engine.Updateable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.tests.ParticleTest;

public class ParticleObject implements Drawable, Updateable{

	private ParticleSystem system;
	private int x,y;
	
	public ParticleObject(float x , float y){
		this.x = (int)x;
		this.y = (int)y;
	}
	
	public void load() throws SlickException{
	       
		Image image = new Image("data/particles/poisoncloud.tga", true); 
	    system = new ParticleSystem(image);
	    system.setPosition(x, y);
	    // x y might just be relative to the position of the system....
		system.addEmitter(new FireEmitter(0, 0, 10.0f));		
		system.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		
	}

	@Override
	public void draw(Graphics g) {
		system.render();
	}
	@Override
	public void update(int delta){
		system.update(delta);
	}
	
	public ParticleSystem getParticleSystem(){
		return system;
	}
	
}
