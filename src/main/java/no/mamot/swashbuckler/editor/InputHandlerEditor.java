package no.mamot.swashbuckler.editor;

import no.mamot.engine.Camera;
import no.mamot.engine.InputHandler;

import org.newdawn.slick.Input;

public class InputHandlerEditor implements InputHandler {

	// Should look at state when performing actions?
	private LevelEditor levelEditor;
	private Camera camera;

	public InputHandlerEditor(LevelEditor levelEditor, Camera camera) {
		this.levelEditor = levelEditor;
		this.camera = camera;
	}

	@Override
	public void handleInput(Input input, int delta) {
		// TODO Auto-generated method stub

		// Actions :

		// Place at mouse position when mouse left is pressed
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			float x = input.getMouseX();
			float y = input.getMouseY();
			x += camera.getTopLeftCorner().x;
			y += camera.getTopLeftCorner().y;

			levelEditor.getState().place(x, y);
		}

		// Finish placing -- Enter
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			levelEditor.getState().placeFinished();
		}

		// Select
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			float x = input.getMouseX();
			float y = input.getMouseY();
			x += camera.getTopLeftCorner().x;
			y += camera.getTopLeftCorner().y;
			levelEditor.getState().select(x, y);
		}

		// Delete selected

		if (input.isKeyPressed(Input.KEY_DELETE)) {
			levelEditor.getState().delete();
		}

		// Move selected
		if (input.isKeyDown(Input.KEY_LSHIFT)) {
			float x = input.getMouseX();
			float y = input.getMouseY();
			x += camera.getTopLeftCorner().x;
			y += camera.getTopLeftCorner().y;

			levelEditor.getState().move(x, y);
		}

		// Not state specific commands
		// Move camera

		if (input.isKeyDown(Input.KEY_UP)) {
			camera.moveCameraVertical(-3);
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			camera.moveCameraVertical(3);
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			camera.moveCameraHorizontal(-3);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			camera.moveCameraHorizontal(3);
		}

		// Save

	}

}
