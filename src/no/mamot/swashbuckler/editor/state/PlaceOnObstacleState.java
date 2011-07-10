package no.mamot.swashbuckler.editor.state;

import no.mamot.swashbuckler.editor.LevelEditor;
import no.mamot.swashbuckler.editor.Obstacle;

public class PlaceOnObstacleState implements LevelEditorState {

	private String transitionTo;
	private LevelEditor levelEditor;

	public PlaceOnObstacleState(LevelEditor levelEditor) {
		this.levelEditor = levelEditor;
	}

	@Override
	public void delete() {

	}

	@Override
	public void move(float x, float y) {

	}

	@Override
	public void place(float x, float y) {
		System.out.println("Select obstacle to place on first");
	}

	@Override
	public void placeFinished() {

	}

	@Override
	public void select(float x, float y) {

		// Deselect obstacles..
		for (Obstacle obstacle : levelEditor.getLevel().getObstacleList()) {
			if (obstacle.isSelected()) {
				obstacle.deSelect();
			}
		}
		// select obstacle
		boolean hasSelected = false;
		for (Obstacle obstacle : levelEditor.getLevel().getObstacleList()) {
			if (obstacle.getShape().contains(x, y)) {
				obstacle.select();
				hasSelected = true;
				break;
			}
		}
		if (hasSelected) {
			if (transitionTo.equals("Draw_Particle")) {
				// then transition
				levelEditor.setState(levelEditor.getStateFactory()
						.getParticleState());
			}
		}
	}

	@Override
	public void setTransition(String transitionTo) {
		this.transitionTo = transitionTo;
	}

}
