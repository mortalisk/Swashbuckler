package no.mamot.swashbuckler;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Interactive objects in the level. I.e. objects that move. (the player or an
 * enemy)
 */
public final class GameEntity implements GameObject {

	private Circle circle;
	private Vector2f before = new Vector2f();
	private float slideAngle = 35;
	private Line line = null;
	Vector2f position = null;
	private Vector2f gravity = new Vector2f(0.0f, 10.0f);
	Vector2f velocityVector = new Vector2f(0.0f, 0.0f);
	private Image image = null;
	float acceleration = 10.0f;
	float maxSpeed = 10.0f;
	private float breakSpeed = 1.0f;
	float jumpSpeed = -14.0f;
	private float correctionSpeed = 5.0f;

	GameEntity(String imageFile, float radius, float x, float y)
			throws SlickException {
		if (imageFile != null)
			image = new Image(imageFile);
		position = new Vector2f(x, y);
		circle = new Circle(x, y, radius);
	}

	public final void draw() {
		int imagePosX = (int) (circle.getX() + circle.getRadius() - getImage()
				.getWidth() / 2.0);
		int imagePosY = (int) (circle.getY() + circle.getRadius() - getImage()
				.getHeight() / 2.0);
		getImage().draw(imagePosX, imagePosY);

		ShapeRenderer.draw(this.getShape());
		if (getLine() != null)
			ShapeRenderer.draw(getLine());
	}

	@Override
	public Shape getShape() {
		return circle;
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

			Result = (angle > 90 + slideAngle || angle < 90 - slideAngle);
		}

		return Result;
	}

	public final void update(GameContainer gc, int delta, List<GameObject> list) {
		move(delta, gc.getInput(), list);
	}

	public final void move(int delta, Input input,
			List<GameObject> gameEntityList) {

		before.set(position);

		// set velocityVector
		position.add(velocityVector);

		// setting shape to the new position
		setShapePosition(position.x, position.y);

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

				// position.set(before);
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
		setPosition(position.x, position.y);
	}

	public final Vector2f getPosition() {
		return position;
	}

	public final Image getImage() {
		return image;
	}

	public final void setImage(Image image) {
		this.image = image;
	}

	public final void setShapePosition(float x, float y) {
		getShape().setX(x);
		getShape().setY(y);
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
		setShapePosition(position.x, position.y);
	}
}
