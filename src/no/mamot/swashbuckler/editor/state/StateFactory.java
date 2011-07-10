package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.LevelEditor;

public class StateFactory {

	
	private LevelEditor levelEditor;
	

	private LevelEditorState polygonState = null;
	private LevelEditorState particleState = null;
	private LevelEditorState placeOnObstacleState = null;
	private LevelEditorState robotState = null;
	
	
	public LevelEditorState getPolygonState(){
		if (polygonState == null){
			polygonState = new PolygonState(levelEditor.getPolygonCreator());
		}
		return polygonState;
	}
	
	public LevelEditorState getParticleState(){
		if (particleState == null){
			particleState = new ParticleState(levelEditor);
		}
		return particleState;		
	}
	
	public LevelEditorState getRobotState() {
		if (robotState == null){
			robotState = new RobotState(levelEditor.getEntityCreator());
		}		
		return robotState;
	}
	
	public LevelEditorState getPlaceOnObstacleState() {
		if (placeOnObstacleState == null){
			placeOnObstacleState = new PlaceOnObstacleState(levelEditor);
		}		
		return placeOnObstacleState;
	}
	
	
	
	
	
	public LevelEditor getLevelEditor() {
		return levelEditor;
	}
	public void setLevelEditor(LevelEditor levelEditor) {
		this.levelEditor = levelEditor;
	}
	
}
