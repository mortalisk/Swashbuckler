package no.mamot.swashbuckler.editor.state;

import no.mamot.engine.Level;
import no.mamot.swashbuckler.editor.ParticleCreator;

public class ParticleState implements LevelEditorState {
	
	private ParticleCreator particleCreator;

	
	public ParticleState(ParticleCreator particleCreator){
		this.particleCreator = particleCreator;
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void move(float x, float y) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void place(float x, float y) {
		particleCreator.createNewParticle(x, y);
		
	}

	@Override
	public void placeFinished() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void select(float x, float y) {
		// TODO Auto-generated method stub		
	}

}
