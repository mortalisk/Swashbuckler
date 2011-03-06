package no.mamot.swashbuckler;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject {
	private Vector2f before = new Vector2f();
	private float slideAngle = 35;
	private Line line = null;
	private Vector2f position = null;
	private Vector2f gravity = new Vector2f(0.0f, 10.0f);
	private Vector2f velocityVector = new Vector2f(0.0f, 0.0f);
	private Image image = null;
	private float acceleration = 10.0f;
	private float maxSpeed = 10.0f;
	private float breakSpeed = 1.0f;
	private float jumpSpeed = -14.0f;
	private float correctionSpeed = 5.0f;

	GameObject(String file, float x, float y) throws SlickException {

		if (file != null)
			image = new Image(file);
		position = new Vector2f(x, y);
	}

	public final boolean detectCollision(GameObject collidingWith) {
		Shape other = collidingWith.getShape();
		if (this.getShape().intersects(other)) {
			return true;
		}

		return false;
	}

	private Vector2f intersectionPointResult = new Vector2f();

	public final Vector2f GetIntersectionPoint(Shape other) {
		float[] thisPoints = this.getShape().getPoints();

		for (int i = 0; i < thisPoints.length; i++) {
			float x = thisPoints[i];
			i++;
			float y = thisPoints[i];

			if (other.contains(x, y)) {
				intersectionPointResult.x = x;
				intersectionPointResult.y = y;
				break;
			}
		}

		return intersectionPointResult;
	}

	private Vector2f intersectionVector = new Vector2f();

	public final boolean shouldSlide(Vector2f intersectionPoint) {
		boolean Result = false;

		if (intersectionPoint != null) {

			intersectionVector.set(intersectionPoint);
			intersectionVector.x -= position.x + getShape().getWidth() / 2.0;
			intersectionVector.y -= position.y + getShape().getHeight() / 2.0;

			float angle = (float) intersectionVector.getTheta();

			// line = new Line(center, intersectionPoint);

			Result = (angle > 90 + slideAngle || angle < 90 - slideAngle);
		}

		return Result;
	}

	public abstract void draw();

	public final void update(GameContainer gc, int delta, GameEntity other,
			List<GameObstacle> obstacle, List<GameObject> list) {
		move(delta, gc.getInput(), list);
	}

	public final void move(int delta, Input input,
			List<GameObject> gameEntityList) {

		before.set(position);

		// set velocityVector
		position.add(velocityVector);

		// setting shape to the new position
		setShapePosition(position.x, position.y);

		// movement
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			if (velocityVector.y >= 0) {
				velocityVector.set(0.0f, jumpSpeed);
			}
		}
		if (input.isKeyDown(Input.KEY_W)) {
			// position.y -= speed / delta;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			// position.y += speed / delta;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			velocityVector.x -= acceleration / delta;
			if (velocityVector.x < -maxSpeed) {
				velocityVector.x = -maxSpeed;
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			velocityVector.x += acceleration / delta;
			if (velocityVector.x > maxSpeed) {
				velocityVector.x = maxSpeed;
			}
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			position.x = input.getMouseX();
			position.y = input.getMouseY();
		}

		// check for collisions after jump or movement
		collisionCorrection(before, delta, gameEntityList, input);

		// adding gravity
		velocityVector.y += gravity.y / delta;
		if (velocityVector.x > 1.0) {
			velocityVector.x -= breakSpeed / delta;
		} else if (velocityVector.x < -1.0) {
			velocityVector.x += breakSpeed / delta;
		}
	}

	public final void collisionCorrection(Vector2f before, int delta,
			List<GameObject> gameEntityList, Input input) {
		// setting shape to the new position in case it has changed
		this.setShapePosition(position.x, position.y);

		// loop game objects to check for any collisions
		for (GameObject otherEntity1 : gameEntityList) {
			if (this.detectCollision(otherEntity1)) {

				if (velocityVector.y > 0)
					velocityVector.y = 0;

				//position.set(before);
				boolean shouldSlide = shouldSlide(this
						.GetIntersectionPoint(otherEntity1.getShape()));
				// slide to avoid collision
				if (input.isKeyDown(Input.KEY_A)
						|| input.isKeyDown(Input.KEY_D)) {
					position.y += correctionSpeed / delta;
				} else if (shouldSlide) {
					position.x += correctionSpeed / delta;
				}

				// setting shape to the new position
				this.setShapePosition(position.x, position.y);

				if (this.detectCollision(otherEntity1)) {

					position.set(before);

					// slide to avoid collision
					if (input.isKeyDown(Input.KEY_A)
							|| input.isKeyDown(Input.KEY_D)) {
						position.y -= correctionSpeed / delta;
					} else if (shouldSlide) {
						position.x -= correctionSpeed / delta;
					}

					// setting shape to the new position
					this.setShapePosition(position.x, position.y);

					if (this.detectCollision(otherEntity1)) {
						position.set(before);
						return;
					} else {
						// check against all other if the slide is ok
						for (GameObject otherEntity2 : gameEntityList) {
							if (this.detectCollision(otherEntity2)) {
								position.set(before);
								return;
							}
						}
					}
				} else {
					// check against all other if the slide is ok
					for (GameObject otherEntity2 : gameEntityList) {
						if (this.detectCollision(otherEntity2)) {
							position.set(before);
							return;
						}
					}
				}
			}
		}
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public final void setPosition(Vector2f position) {
		this.position = position;
	}

	public final Image getImage() {
		return image;
	}

	public final void setImage(Image image) {
		this.image = image;
	}

	public abstract Shape getShape();

	public final void setShapePosition(float x, float y) {
		getShape().setX(x);
		getShape().setY(y);
	}
}