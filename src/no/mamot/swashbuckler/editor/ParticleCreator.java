package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import no.mamot.swashbuckler.Fire;

import org.newdawn.slick.SlickException;

public class ParticleCreator {

	private LevelImplEditor level;
	private List <Fire> particleObjects;

	public ParticleCreator(LevelImplEditor level) {
		this.level = level;
		particleObjects = new ArrayList<Fire>();
	}

	public void createNewParticle(float x, float y) {
		Fire newParticleObject = null;
		try {
			newParticleObject = new Fire(x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		particleObjects.add(newParticleObject);
		level.getDrawableList().add(newParticleObject);
		level.getUpdatableList().add(newParticleObject);
	}
}
