package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import no.mamot.engine.Level;

public class ParticleCreator {

	private Level level;
	private List <ParticleObject> particleObjects = new ArrayList<ParticleObject>();
	
	public ParticleCreator(Level level){
		this.level = level;
	}
	
	public void createNewParticle(float x , float y){
		ParticleObject newParticleObject = new ParticleObject(x, y);
		try {
			newParticleObject.load();			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		particleObjects.add(newParticleObject);
		level.getDrawableList().add(newParticleObject);
		level.getUpdatableList().add(newParticleObject);		
	}
	
}
