package no.mamot.swashbuckler;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public final class GameEntity extends GameObject {

	private Circle circle;

	GameEntity(String file, float radius, float x, float y)
			throws SlickException {
		super(file, x, y);
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

//	@Override
//	public void setPosition(float x, float y) {
//		circle.setX(x);
//		circle.setY(y);
//	}
}
