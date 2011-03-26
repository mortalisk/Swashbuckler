package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.LevelEditor;

public class StateFactory {

	
	private LevelEditor levelEditor;
	

	private PolygonState polygonState = null;
	private ParticleState particleState = null;
	
	public LevelEditorState getPolygonState(){
		if (polygonState == null){
			polygonState = new PolygonState(levelEditor.getPolygonCreator());
		}
		return polygonState;
	}
	public LevelEditorState getParticleState(){
		if (particleState == null){
			particleState = new ParticleState();
		}
		return particleState;		
	}
	
	
	
	
	
	public LevelEditor getLevelEditor() {
		return levelEditor;
	}
	public void setLevelEditor(LevelEditor levelEditor) {
		this.levelEditor = levelEditor;
	}
	
}
