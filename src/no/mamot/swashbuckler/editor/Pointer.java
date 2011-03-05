package no.mamot.swashbuckler.editor;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.renderer.Renderer;

public class Pointer {
	private Shape lineV;
	private Shape lineH;
	private ShapeRenderer renderer;
	
	public Pointer(){
		renderer = new ShapeRenderer();
		lineV = new Line(new Vector2f(0.0f , 1.0f), new Vector2f(0.0f, 0.5f));
		lineH = new Line(new Vector2f(1.0f , 0.0f), new Vector2f(0.5f, 0.0f));
	}
	public void update(float x , float y){
		lineV = new Line(new Vector2f(x , y+10), new Vector2f(x, y-10));
		lineH = new Line(new Vector2f(x+10 , y), new Vector2f(x-10, y));
	}
	
	public void draw(){
		renderer.draw(lineV);
		renderer.draw(lineH);
	}
	
	
	
}
