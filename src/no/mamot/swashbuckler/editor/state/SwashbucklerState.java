package no.mamot.swashbuckler.editor.state;

import org.newdawn.slick.SlickException;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

public class SwashbucklerState implements LevelEditorState {

	private EntityCreator entityCreator = null;
	
	public SwashbucklerState(EntityCreator entityCreator){
		this.entityCreator = entityCreator;
	}	
	
	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity(TypeEnum.SWASHBUCKLER, x, y);
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
		entityCreator.select(TypeEnum.SWASHBUCKLER, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.SWASHBUCKLER);	
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.SWASHBUCKLER, x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}

}
