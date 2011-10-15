package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.TypeEnum;

import org.newdawn.slick.SlickException;

public class HealParticleState implements LevelEditorState {

	private EntityCreator entityCreator = null;
	
	public HealParticleState(EntityCreator entityCreator) {
		this.entityCreator = entityCreator;
	}

	@Override
	public void place(float x, float y) {
		//particleCreator.createNewParticle(x, y);
		try {
			entityCreator.createNewEntity(TypeEnum.HEAL, x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void select(float x, float y) {
		entityCreator.select(TypeEnum.HEAL, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.HEAL);
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.HEAL, x, y);
	}

	@Override
	public void placeFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}


}
