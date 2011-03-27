package no.mamot.swashbuckler.editor.state;

import no.mamot.engine.Level;
import no.mamot.swashbuckler.editor.LevelEditor;
import no.mamot.swashbuckler.editor.Obstacle;
import no.mamot.swashbuckler.editor.ParticleCreator;

public class ParticleState implements LevelEditorState {
	
	private ParticleCreator particleCreator;
	private LevelEditor levelEditor;
	

	
	public ParticleState(LevelEditor levelEditor){
		this.particleCreator = levelEditor.getParticleCreator();
		this.levelEditor = levelEditor;
		
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
		LevelEditorState state = levelEditor.getStateFactory().getPlaceOnObstacleState();
		state.setTransition("Draw_Particle");
		levelEditor.setState(state);	
		
		// Deselect obstacle
		for (Obstacle obstacle : levelEditor.getLevel().getObstacleList()){
			if (obstacle.isSelected()){
				obstacle.select();
			}			
		}
	}

	@Override
	public void select(float x, float y) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}
	


}
