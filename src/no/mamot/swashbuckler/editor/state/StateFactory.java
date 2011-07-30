package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.LevelEditor;

public class StateFactory {

	private LevelEditor levelEditor;

	private LevelEditorState polygonState = null;
	private LevelEditorState fireParticleState = null;
	private LevelEditorState healParticleState = null;
	private LevelEditorState robotState = null;
	private LevelEditorState robatState = null;
	private LevelEditorState tourmalineState = null;
	private LevelEditorState swashbucklerState = null;

	public LevelEditorState getPolygonState() {
		if (polygonState == null) {
			polygonState = new PolygonState(levelEditor.getPolygonCreator());
		}
		return polygonState;
	}

	public LevelEditorState getFireParticleState() {
		if (fireParticleState == null) {
			fireParticleState = new FireParticleState(levelEditor.getEntityCreator());
		}
		return fireParticleState;
	}
	
	public LevelEditorState getHealParticleState() {
		if (healParticleState == null) {
			healParticleState = new HealParticleState(levelEditor.getEntityCreator());
		}
		return healParticleState;
	}

	public LevelEditorState getRobotState() {
		if (robotState == null) {
			robotState = new RobotState(levelEditor.getEntityCreator());
		}
		return robotState;
	}

	public LevelEditorState getRobatState() {
		if (robatState == null) {
			robatState = new RobatState(levelEditor.getEntityCreator());
		}
		return robatState;
	}

	public LevelEditorState getTourmalineState() {
		if (tourmalineState == null) {
			tourmalineState = new TourmalineState(
					levelEditor.getEntityCreator());
		}
		return tourmalineState;
	}

	public LevelEditorState getSwashbucklerState() {
		if (swashbucklerState == null) {
			swashbucklerState = new SwashbucklerState(
					levelEditor.getEntityCreator());
		}
		return swashbucklerState;
	}



	public LevelEditor getLevelEditor() {
		return levelEditor;
	}

	public void setLevelEditor(LevelEditor levelEditor) {
		this.levelEditor = levelEditor;
	}



}
