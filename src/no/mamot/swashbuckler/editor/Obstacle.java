package no.mamot.swashbuckler.editor;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

public class Obstacle {
	
	private Shape polygon;

	private boolean selected = false;
	private Color startCol;
	private Color endCol;
	private ShapeFill fill;
	private ShapeRenderer renderer;
	
	public Obstacle(Shape polygon){
		this.polygon = polygon;
		renderer = new ShapeRenderer();
		startCol = new Color(1.0f, 1.0f, 1.0f);
		endCol = new Color(1.0f, 1.0f, 1.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	public Shape getShape(){
		return polygon;
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
		renderer.draw(polygon, fill);
	}
	
	public boolean isSelected (){
		return selected;
	}
}
