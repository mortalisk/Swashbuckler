package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.PolygonCreator;

public class PolygonState implements LevelEditorState {

	private PolygonCreator polygonCreator;
	
	public PolygonState(PolygonCreator polygonCreator){		
		this.polygonCreator = polygonCreator;
	}
	
	
	@Override
	public void delete() {
		polygonCreator.deleteSelected();
	}

	@Override
	public void move(float x , float y) {
		polygonCreator.moveSelectedPolygons(x, y);
	}

	@Override
	public void place(float x ,float y) {
			polygonCreator.createNewDott(x, y);		
	}

	@Override
	public void select(float x ,float y) {
		if (polygonCreator.noDottsSelected()){
			polygonCreator.selectPolygon(x, y);
		}
		if (polygonCreator.noObstaclesSelected()){
			polygonCreator.selectDot(x, y);
		}
	}


	@Override
	public void placeFinished() {
		polygonCreator.createNewPolygon();
		
	}


	@Override
	public void setTransition(String transitionTo) {
		// TODO Auto-generated method stub
		
	}

}
