package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

import org.newdawn.slick.SlickException;

public class RhinobotState implements LevelEditorState {

	private EntityCreator entityCreator = null;

	public RhinobotState(EntityCreator robotCreator) {
		this.entityCreator = robotCreator;
	}

	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity(TypeEnum.RHINOBOT, x, y);
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
		entityCreator.select(TypeEnum.RHINOBOT, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.RHINOBOT);
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.RHINOBOT, x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub

	}

}
