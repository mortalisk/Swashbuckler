package no.mamot.swashbuckler.editor;

import no.mamot.engine.Drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

public class Dott implements Drawable{

	private Shape dott;
	private Vector2f position;
	private boolean selected = false;
	private Color startCol;
	private Color endCol;
	private ShapeFill fill;
	private ShapeRenderer renderer;
	
	private static float radius = 3.0f; // radius of the dott..
	
	public Dott (float x , float y){
		dott = new Circle(x, y, radius);
		position = new Vector2f(x,y);
		renderer = new ShapeRenderer();
		startCol = new Color(1.0f, 1.0f, 1.0f);
		endCol = new Color(1.0f, 1.0f, 1.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
		
	}
	public Shape getShape(){
		return dott;
	}
	public Vector2f getPosition(){
		return position;
	}
	
	public void select(){
		if (!selected){
			makeRed();
			selected = true;
		}
		else {
			makeWhite();
			selected = false;
		}
	}
	
	private void makeRed(){
		startCol = new Color(1.0f, 0.0f, 0.0f);
		endCol = new Color(1.0f, 0.0f, 0.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	private void makeWhite(){
		startCol = new Color(1.0f, 1.0f, 1.0f);
		endCol = new Color(1.0f, 1.0f, 1.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	public void draw(){
		renderer.draw(dott, fill);
	}
	
	public boolean isSelected (){
		return selected;
	}
}
