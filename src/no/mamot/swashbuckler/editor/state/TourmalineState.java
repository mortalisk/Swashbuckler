package no.mamot.swashbuckler.editor.state;

import org.newdawn.slick.SlickException;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

public class TourmalineState implements LevelEditorState {

	private EntityCreator entityCreator = null;
	
	public TourmalineState(EntityCreator entityCreator){
		this.entityCreator = entityCreator;
	}	
	
	@Override
	public void place(float x, float y) {
		try {
			entityCreator.createNewEntity(TypeEnum.TOURMALINE, x, y);
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
		entityCreator.select(TypeEnum.TOURMALINE, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.TOURMALINE);	
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.TOURMALINE, x, y);
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}

}
