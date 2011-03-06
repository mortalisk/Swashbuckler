package no.mamot.swashbuckler.editor;

import no.mamot.swashbuckler.Camera;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.renderer.Renderer;

public class Pointer {
	private Shape lineV;
	private Shape lineH;
	private Camera camera;
	private Vector2f position;
	
	public Pointer(Camera camera){
		this.camera = camera;
		lineV = new Line(new Vector2f(0.0f , 1.0f), new Vector2f(0.0f, 0.5f));
		lineH = new Line(new Vector2f(1.0f , 0.0f), new Vector2f(0.5f, 0.0f));
		position = new Vector2f();
	}
	
	public void handleInput(Input input){
		float x = input.getMouseX();
		float y = input.getMouseY();
		x -= camera.getTopLeftCorner().x;
		y -= camera.getTopLeftCorner().y;
		position.x = x;
		position.y = y;
		lineV = new Line(new Vector2f(x , y+10), new Vector2f(x, y-10));
		lineH = new Line(new Vector2f(x+10 , y), new Vector2f(x-10, y));
	}
	
	public void draw(){
		ShapeRenderer.draw(lineV);
		ShapeRenderer.draw(lineH);
	}
	public Vector2f getPosition() {
		return position;
	}
	
	
	
}
