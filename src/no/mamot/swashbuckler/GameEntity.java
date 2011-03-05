package no.mamot.swashbuckler;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter.RandomValue;

public class GameEntity extends GameObject{

	private Vector2f position = null;
	private Vector2f gravity = new Vector2f(0.0f, 3.8f);
	private Vector2f jumpVector = new Vector2f(0.0f , 0.0f);
	
	private Image image = null;
	// bounding spheres for collision
	private float radius = 0.0f;
	private float speed = 2.3f;
	private float jumpSpeed = -12.0f;
	private float correctionSpeed = 2.3f; // should be greater then speed ??
	
	//private Circle sphere = null;

	private ShapeRenderer renderer = null;

	
	public GameEntity(String file,float radius, float x, float y) throws SlickException {
		// TODO Auto-generated constructor stub
		image = new Image(file);
		position = new Vector2f (x,y);
		this.radius = radius;
		renderer = new ShapeRenderer();
		
		
	}


	public void draw(){
		image.draw(position.x, position.y);
		this.setShape(new Circle(position.x, position.y, radius));
		
		renderer.draw(this.getShape());
	
		
		
		
	}
	
	public void update (GameContainer gc, int delta, GameEntity other, List <GameObstacle> obstacle){

		moveOld(gc, delta, other, obstacle);
		
	}
	
	
	
	private void moveOld(GameContainer gc, int delta, GameEntity other , List <GameObstacle> obstacleList){
		Input input = gc.getInput();		
		// first add gravity...
		// and check for collision..
		gravity = new Vector2f(0.0f, 1.2f); 
		gravity.y = gravity.y / delta;  // some other way to do this??
		//jumpVector.y = jumpVector.y*delta;
		Vector2f before = position.copy();
		if (jumpVector.y < 0) {
			jumpVector.y += gravity.y;
		}
		position.add(jumpVector);
		position.add(gravity);
		Vector2f testGravity = position.copy();
		for (GameObstacle obstacle : obstacleList){
			if (checkGameEntityCollision(testGravity,radius, other) || checkObstacleEntityCollision(testGravity, radius, obstacle)){
			  position = before.copy();
			}
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)){
			if (jumpVector.y >= 0)
			jumpVector = new Vector2f(0.0f, jumpSpeed);
		}
		
		// keep position before move..
		before = position.copy();
		if (input.isKeyDown(Input.KEY_W)){
			position.y -= (speed / delta);
			Vector2f testPosition = position.copy();
			this.setShape(new Circle(position.x, position.y, radius));
			for (GameObstacle obstacle : obstacleList) {
			if (checkGameEntityCollision(testPosition,radius, other) || checkObstacleEntityCollision(testPosition, radius, obstacle)){
				position = before.copy();				
				position.x += correctionSpeed / delta;
				testPosition = position.copy();
				this.setShape(new Circle(position.x, position.y, radius));
				if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
					position = before.copy();				
					position.x -= correctionSpeed/delta;
					testPosition = position.copy();
					this.setShape(new Circle(position.x, position.y, radius));
					if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
						position = before.copy();
						return;
					}
					return;
				}
			}
			}	
				
		}
		if (input.isKeyDown(Input.KEY_S)){
			position.y += speed / delta;
			Vector2f testPosition = position.copy();
			for (GameObstacle obstacle : obstacleList) {
				this.setShape(new Circle(position.x, position.y, radius));
			if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
				position = before.copy();				
				position.x += correctionSpeed / delta;
				testPosition = position.copy();
				this.setShape(new Circle(position.x, position.y, radius));
				if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
					position = before.copy();				
					position.x -= correctionSpeed / delta;
					testPosition = position.copy();
					this.setShape(new Circle(position.x, position.y, radius));
					if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
						position = before.copy();
						return;
					}
					return;
				}
			}
			}
		}
		if (input.isKeyDown(Input.KEY_A)){
			position.x -= speed / delta;
			Vector2f testPosition = position.copy();
			this.setShape(new Circle(position.x, position.y, radius));
			for (GameObstacle obstacle : obstacleList) {
			if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
				position = before.copy();				
				position.y += correctionSpeed / delta;
				testPosition = position.copy();
				this.setShape(new Circle(position.x, position.y, radius));
				if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
					position = before.copy();				
					position.y -= correctionSpeed / delta;
					testPosition = position.copy();
					this.setShape(new Circle(position.x, position.y, radius));
					if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
						position = before.copy();
						return;
					}
					return;
				}
			}
			}
		}
		if (input.isKeyDown(Input.KEY_D)){
			position.x += speed / delta;
			Vector2f testPosition = position.copy();
			this.setShape(new Circle(position.x, position.y, radius));
			for (GameObstacle obstacle : obstacleList) {
			if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
				position = before.copy();				
				position.y += correctionSpeed / delta;
				testPosition = position.copy();
				this.setShape(new Circle(position.x, position.y, radius));
				if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
					position = before.copy();				
					position.y -= correctionSpeed / delta;
					testPosition = position.copy();
					this.setShape(new Circle(position.x, position.y, radius));
					if (checkGameEntityCollision(testPosition,radius, other)|| checkObstacleEntityCollision(testPosition, radius, obstacle)){
						position = before.copy();
						return;
					}
					return;
				}
			}
		}
			}
		
		
		
		
	}
	
	private boolean checkGameEntityCollision(Vector2f position, float myRadius, GameEntity other){
		// Checks collision between two gameEntities with one boundingsphere
		float deltaX = position.x - other.position.x;
		float deltaY = position.y - other.position.y;
		float minimumDistance = myRadius + other.radius;
		if (deltaX*deltaX + deltaY*deltaY < minimumDistance * minimumDistance){
			return true;
		}		
		return false;
	}
	
	private boolean checkObstacleEntityCollision (Vector2f position, float myRadius, GameObstacle obstacle){
		Polygon polygon = (Polygon)obstacle.getShape();
		if (this.getShape().intersects(polygon)) {
			
			return true;
		}
		
		return false;
	}
	
	public void move(Input input, List <GameObject> gameEntityList){
		
		Vector2f before = position.copy();
		if (jumpVector.y < 0) {
			jumpVector.y += gravity.y;
		}
		position.add(jumpVector);
		position.add(gravity);
		Vector2f testGravity = position.copy();
		
		if (input.isKeyDown(Input.KEY_SPACE)){
			if (jumpVector.y >= 0)
			jumpVector = new Vector2f(0.0f, jumpSpeed);
		}
		
		// TODO . fix this method and make it better then the old one...
		boolean collision = false;
		do {
			for (GameObject otherEntity : gameEntityList){
				collision = detectCollision(otherEntity);
				
			}
			
			collisionCorrection();
			
		}while(collision); // 
	}


	public void collisionCorrection(){
		
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
