package no.mamot.swashbuckler.editor.state;

import org.newdawn.slick.SlickException;

import no.mamot.swashbuckler.editor.EntityCreator;
import no.mamot.swashbuckler.editor.LevelEditor;
import no.mamot.swashbuckler.editor.Obstacle;

import no.mamot.swashbuckler.editor.TypeEnum;

public class FireParticleState implements LevelEditorState {


	private LevelEditor levelEditor;
	private EntityCreator entityCreator = null;
	
	public FireParticleState(EntityCreator entityCreator) {
		this.entityCreator = entityCreator;
	}

	@Override
	public void place(float x, float y) {
		//particleCreator.createNewParticle(x, y);
		try {
			entityCreator.createNewEntity(TypeEnum.FIRE, x, y);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void select(float x, float y) {
		entityCreator.select(TypeEnum.FIRE, x, y);
	}

	@Override
	public void delete() {
		entityCreator.delete(TypeEnum.FIRE);
	}

	@Override
	public void move(float x, float y) {
		entityCreator.moveSelectedEntities(TypeEnum.FIRE, x, y);
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
