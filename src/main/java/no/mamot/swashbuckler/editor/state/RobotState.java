package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

import org.newdawn.slick.SlickException;

public class RobotState implements LevelEditorState {

	private EntityCreator entityCreator = null;

	public RobotState(EntityCreator robotCreator) {
		this.entityCreator = robotCreator;
	}

	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity(TypeEnum.ROBOT, x, y);
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
		entityCreator.select(TypeEnum.ROBOT, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.ROBOT);
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.ROBOT, x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub

	}

}
