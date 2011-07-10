package no.mamot.swashbuckler.editor.state;

import org.newdawn.slick.SlickException;

import no.mamot.swashbuckler.editor.EntityCreator;

public class RobotState implements LevelEditorState {

	private EntityCreator entityCreator = null;
	
	public RobotState(EntityCreator robotCreator){
		this.entityCreator = robotCreator;
	}	
	
	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity("Robot", x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void placeFinished() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void select(float x, float y) {
		entityCreator.select("Robot", x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete("Robot");	
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities("Robot", x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}

}
