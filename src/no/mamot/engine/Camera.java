package no.mamot.engine;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private Vector2f center = new Vector2f(0.0f, 0.0f);
	private int screenWidth;
	private int screenHeight;

	

	public Camera(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}



	public void moveCameraVertical(float y) {
		center.y += y;

	}

	public void moveCameraHorizontal(float x) {
		center.x += x;
	}

	Vector2f returnVector = new Vector2f();

	public Vector2f getTopLeftCorner() {
		returnVector.x = center.x - screenWidth / 2;
		returnVector.y = center.y - screenHeight / 2;
		return returnVector;
	}

	public void setCenter(Vector2f playerPosition) {
		center = playerPosition;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

}
