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
	private Vector2f gravity = new Vector2f(0.0f, 0.5f);
	private Vector2f jumpVector = new Vector2f(0.0f , 0.0f);
	
	private Image image = null;
	// bounding spheres for collision
	private float radius = 0.0f;
	private float speed = 2.0f;
	private float jumpSpeed = -4.0f;
	private float correctionSpeed = 2.3f; // should be greater then speed ??

	private ShapeRenderer renderer = null;

	
	public GameEntity(String file,float radius, float x, float y) throws SlickException {
		// TODO Auto-generated constructor stub
		image = new Image(file);
		position = new Vector2f (x,y);
		this.radius = radius;
		renderer = new ShapeRenderer();
		this.setShape(new Circle(position.x, position.y, radius));	
	}


	public void draw(){
		image.draw(position.x, position.y);
		this.setShape(new Circle(position.x, position.y, radius));
		
		renderer.draw(this.getShape());
	}
	
	public void update (GameContainer gc, int delta, GameEntity other, List <GameObstacle> obstacle , List<GameObject> list){
		move(delta, gc.getInput(), list);
	}
	
	public void move(int delta, Input input, List <GameObject> gameEntityList){
		
		Vector2f before = position.copy();
		/*if (jumpVector.y < 0) {
			jumpVector.y += gravity.y;
		}*/
		
		//set new gravity
		Vector2f thisGravity = gravity.copy();
		thisGravity.x = thisGravity.x / delta;
		thisGravity.y = thisGravity.y / delta;		
		
		//position.add(jumpVector);
		position.add(thisGravity);
		
		//setting shape to the new position
		this.setShape(new Circle(position.x, position.y, radius));
		
		//Doing first check to ensure we don't jump into anything
		for (GameObject otherEntity : gameEntityList) {
			if (this.detectCollision(otherEntity)) {
				//collisionCorrection(before, delta, gameEntityList, null);
				position = before.copy();
				break;
			}
		}
		
		//jump
		/*if (input.isKeyDown(Input.KEY_SPACE)){
			if (jumpVector.y >= 0) {
				jumpVector = new Vector2f(0.0f, jumpSpeed);
			}			
		}*/
		
		//move
		if (input.isKeyDown(Input.KEY_W)){
			position.y -= speed / delta;
			collisionCorrection(before, delta, gameEntityList, input);
		}
		if (input.isKeyDown(Input.KEY_S)){
			position.y += speed / delta;
			collisionCorrection(before, delta, gameEntityList, input);
		}
		if (input.isKeyDown(Input.KEY_A)){
			position.x -= speed / delta;
			collisionCorrection(before, delta, gameEntityList, input);
		}
		if (input.isKeyDown(Input.KEY_D)){
			position.x += speed / delta;
			collisionCorrection(before, delta, gameEntityList, input);
		}	
	}

	public void collisionCorrection(Vector2f before, int delta, List <GameObject> gameEntityList, Input input) {
		//setting shape to the new position
		this.setShape(new Circle(position.x, position.y, radius));
		
		if (input != null) { //this is a movement	
			//entity is moving up or down -> try to slide left or right	
			if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_W)){
				System.out.println("UP or DOWN");
				for (GameObject otherEntity1 : gameEntityList) {
					if (this.detectCollision(otherEntity1)) {				
						position = before.copy();
						position.x += correctionSpeed / delta;
						this.setShape(new Circle(position.x, position.y, radius));
						if (this.detectCollision(otherEntity1)) {
							position = before.copy();				
							position.x -= correctionSpeed/delta;	
							this.setShape(new Circle(position.x, position.y, radius));
							if (this.detectCollision(otherEntity1)) {
								position = before.copy();
								return;
							}
							return;
						}
					}
				}				
			}
			
			if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)){
				System.out.println("LEFT or RIGHT");
				//entity is moving left or right -> try to slide up or down
				for (GameObject otherEntity1 : gameEntityList) {
					if (this.detectCollision(otherEntity1)) {				
						position = before.copy();
						position.y += correctionSpeed / delta;
						this.setShape(new Circle(position.x, position.y, radius));
						if (this.detectCollision(otherEntity1)) {
							position = before.copy();				
							position.y -= correctionSpeed / delta;	
							this.setShape(new Circle(position.x, position.y, radius));
							if (this.detectCollision(otherEntity1)) {
								position = before.copy();
								return;
							}
							return;
						}
					}
				}				
			}		
		} else { //entity is not moving actively - >try to slide in any direction
			for (GameObject otherEntity : gameEntityList) {
				if (this.detectCollision(otherEntity)) {				
					position = before.copy();
					position.y += correctionSpeed / delta;
					this.setShape(new Circle(position.x, position.y, radius));
					if (this.detectCollision(otherEntity)) {
						position = before.copy();				
						position.y -= correctionSpeed / delta;	
						this.setShape(new Circle(position.x, position.y, radius));
						if (this.detectCollision(otherEntity)) {
							position = before.copy();
							position.x += correctionSpeed / delta;
							this.setShape(new Circle(position.x, position.y, radius));
							if (this.detectCollision(otherEntity)) {
								position = before.copy();				
								position.x -= correctionSpeed / delta;	
								this.setShape(new Circle(position.x, position.y, radius));
								if (this.detectCollision(otherEntity)) {
									position = before.copy();
									return;
								}
								return;
							}
						}
						return;
					}
				}
			}
		}		
	}	
}
