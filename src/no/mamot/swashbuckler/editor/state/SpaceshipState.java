package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

import org.newdawn.slick.SlickException;

public class SpaceshipState implements LevelEditorState {

	private EntityCreator entityCreator = null;

	public SpaceshipState(EntityCreator robotCreator) {
		this.entityCreator = robotCreator;
	}

	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity(TypeEnum.SPACESHIP, x, y);
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
		entityCreator.select(TypeEnum.SPACESHIP, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.SPACESHIP);
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.SPACESHIP, x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub

	}

}
