package no.mamot.swashbuckler.editor;

import no.mamot.engine.Camera;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Pointer {
	private Camera camera;
	private Vector2f position;
	
	public Pointer(Camera camera){
		this.camera = camera;

		position = new Vector2f();
	}
	
	public void handleInput(Input input){
		float x = input.getMouseX();
		float y = input.getMouseY();
		x -= camera.getTopLeftCorner().x;
		y -= camera.getTopLeftCorner().y;
		position.x = x;
		position.y = y;

	}
	
	public void draw(){

	}
	public Vector2f getPosition() {
		return position;
	}
	
	
	
}
