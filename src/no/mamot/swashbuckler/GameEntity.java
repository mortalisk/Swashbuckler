package no.mamot.swashbuckler;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

public class GameEntity extends GameObject{

	private Vector2f position = null;
	private Vector2f gravity = new Vector2f(0.0f, 1.0f);
	private Vector2f jumpVector = new Vector2f(0.0f , 0.0f);
	
	private Image image = null;
	// bounding spheres for collision
	private float radius = 0.0f;
	private float speed = 3.0f;
	private float jumpSpeed = -8.0f;
	private float correctionSpeed = 4.0f; // should be greater then speed ??
	
	public GameEntity(String file,float radius, float x, float y) throws SlickException {
		image = new Image(file);
		position = new Vector2f (x,y);
		this.radius = radius;
		this.setShape(new Circle(position.x, position.y, radius));	
	}

	public void draw(){
		int imagePosX = (int) (position.x - (image.getWidth() / 2) + 2);
		int imagePosY = (int) (position.y - (image.getHeight() / 2));
		image.draw(imagePosX, imagePosY);
		this.setShape(new Circle(position.x, position.y, radius));
		
		ShapeRenderer.draw(this.getShape());
	}
	
	public void update (GameContainer gc, int delta, GameEntity other, List <GameObstacle> obstacle , List<GameObject> list){
		move(delta, gc.getInput(), list);
	}
	
	public void move(int delta, Input input, List <GameObject> gameEntityList){
		
		Vector2f before = position.copy();
		
		//set jumpvector
		if (jumpVector.y < 0) {
			jumpVector.y += gravity.y;
		}
		position.add(jumpVector);
		
		//set new gravity
		Vector2f thisGravity = gravity.copy();
		thisGravity.x = thisGravity.x / delta;
		thisGravity.y = thisGravity.y / delta;			
		position.add(thisGravity);				
		
		//setting shape to the new position
		this.setShape(new Circle(position.x, position.y, radius));
		
		//Doing first check to ensure we don't drop or jump into anything
		for (GameObject otherEntity : gameEntityList) {
			if (this.detectCollision(otherEntity)) {
				position = before.copy();
				break;
			}
		}

		//movement
		if (input.isKeyPressed(Input.KEY_SPACE)){
			if (jumpVector.y >= 0) {
				jumpVector = new Vector2f(0.0f, jumpSpeed);
			}			
		}
		if (input.isKeyDown(Input.KEY_W)){
			//position.y -= speed / delta;			
		}
		if (input.isKeyDown(Input.KEY_S)){
			position.y += speed / delta;
		}
		if (input.isKeyDown(Input.KEY_A)){
			position.x -= speed / delta;
		}
		if (input.isKeyDown(Input.KEY_D)){
			position.x += speed / delta;
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			System.out.println();
			position.x = input.getMouseX();
			position.y = input.getMouseY();
		}
		
		//check for collisions after jump or movement
		collisionCorrection(before, delta, gameEntityList, input);
		
		System.out.println("position: " + position.x + " | " + position.y);
	}

	public void collisionCorrection(Vector2f before, int delta, List <GameObject> gameEntityList, Input input) {
		//setting shape to the new position in case it has changed
		this.setShape(new Circle(position.x, position.y, radius));
	
		//loop game objects to check for any collisions
		for (GameObject otherEntity1 : gameEntityList) {
			if (this.detectCollision(otherEntity1)) {				
				position = before.copy();
				
				//slide to avoid collision
				if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_W)){
					position.x += correctionSpeed / delta;
				} else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)){
					position.y += correctionSpeed / delta;
				}
				
				//setting shape to the new position
				this.setShape(new Circle(position.x, position.y, radius));
				
				if (this.detectCollision(otherEntity1)) {
					position = before.copy();	
					
					//slide to avoid collision						
					if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_W)){
						position.x -= correctionSpeed / delta;
					} else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)){
						position.y -= correctionSpeed / delta;
					}	
					
					//setting shape to the new position
					this.setShape(new Circle(position.x, position.y, radius));
					
					if (this.detectCollision(otherEntity1)) {
						position = before.copy();
						return;
					} else {
						//check against all other if the slide is ok
						for (GameObject otherEntity2 : gameEntityList) {
							if (this.detectCollision(otherEntity2)) {				
								position = before.copy();
								return;
							}
						}	
					}
				} else {
					//check against all other if the slide is ok
					for (GameObject otherEntity2 : gameEntityList) {
						if (this.detectCollision(otherEntity2)) {				
							position = before.copy();
							return;
						}
					}						
				}
			}
		}		
	}	
}
