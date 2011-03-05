package no.mamot.swashbuckler;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

public class GameObstacle extends GameObject{

	private Vector2f position;
	//private Polygon shape;
	private ShapeRenderer renderer;
	
	public GameObstacle (float [] arg0, Vector2f position){
		this.setShape(new Polygon(arg0));
		this.position = position;
		renderer = new ShapeRenderer();		
	}
	
	public void draw(){		
		renderer.draw(this.getShape());		
	}

	public Vector2f getPosition() {
		return position;
	}
}
