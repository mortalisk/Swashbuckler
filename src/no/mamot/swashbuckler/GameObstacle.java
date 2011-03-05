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
	private Color startCol;
	private Color endCol;
	private ShapeFill fill;
	
	public GameObstacle (float [] arg0, Vector2f position){
		this.setShape(new Polygon(arg0));
		this.position = position;
		renderer = new ShapeRenderer();
		startCol = new Color(1.0f, 0.0f, 0.0f);
		endCol = new Color(1.0f, 0.5f, 0.7f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	public void draw(){
		
		
		renderer.draw(this.getShape());
		
	}

	public Vector2f getPosition() {
		return position;
	}



	@Override
	public boolean detectCollision(GameObject collidingWith) {
		// TODO Auto-generated method stub
		Shape other = collidingWith.getShape();
		if (this.getShape().intersects(other)){
			return true;
		}		
		
		return false;
	}
	
	
	
}
