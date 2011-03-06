package no.mamot.swashbuckler;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private Vector2f topLeftCorner = new Vector2f(0.0f, 0.0f);
	private int keyUp; 
	private int keyDown;
	private int keyLeft;
	private int keyRight;
	
	private float speed = 2.0f;
	
	
	public Camera(int keyUp, int keyDown, int keyLeft, int keyRight){
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
	}
	
	
	public void handleInput(Input input){
		if (input.isKeyDown(keyUp)){
			moveCameraVertical(speed);
		}
		if (input.isKeyDown(keyDown)){
			moveCameraVertical(-speed);
		}
		if (input.isKeyDown(keyLeft)){
			moveCameraHorizontal(speed);
		}
		if (input.isKeyDown(keyRight)){
			moveCameraHorizontal(-speed);
		}
		
		
		
	}
	
	
	private void moveCameraVertical (float y){
		topLeftCorner.y += y;
		
	}
	
	private void moveCameraHorizontal (float x){
		topLeftCorner.x += x;
	}

	public Vector2f getTopLeftCorner() {
		return topLeftCorner;
	}
	public void setTopLeftCorner(Vector2f topLeftCorner){
		this.topLeftCorner = topLeftCorner;
	}
	
}
