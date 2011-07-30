package no.mamot.swashbuckler.editor;

import net.phys2d.math.Vector2f;
import no.mamot.engine.Drawable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;

public class Entity implements Drawable {
	private Vector2f position;
	private TypeEnum type;
	private boolean selected;
	private Image image;
	private Circle selector;

	public Entity(TypeEnum type, float x, float y) throws SlickException {
		this.type = type;
		position = new Vector2f(x, y);
		selector = new Circle(x + 10.0f, y + 15.0f, 5.0f);

		if (type.equals(TypeEnum.ROBOT)) {
			image = new Image("/data/Robots/Robot1.png");
		} else if (type.equals(TypeEnum.TOURMALINE)) {
			image = new Image("/data/Items/Tourmaline1.png");
		} else if (type.equals(TypeEnum.ROBAT)) {
			image = new Image("/data/Robots/Robat1.png");
		} else if (type.equals(TypeEnum.SWASHBUCKLER)) {
			image = new Image("/data/Swashbuckler/Swashbuckler.png");
		}else if (type.equals(TypeEnum.FIRE)) {
			image = new Image("/data/particles/Fire.png");
		}
		else if (type.equals(TypeEnum.HEAL)) {
			image = new Image("/data/particles/Heal.png");
		}
	}

	public void move(float x, float y) {
		position.x = x;
		position.y = y;
		selector.setLocation(position.x + 10.0f, position.y + 15.0f);
	}

	public Vector2f getPosition() {
		return this.position;
	}

	public float getWidth() {
		return image.getWidth();
	}

	public float getHeight() {
		return image.getHeight();
	}

	public TypeEnum getType() {
		return this.type;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public void draw(Graphics g) {
		if (image != null) {
			image.draw(position.x, position.y);
			if (selected) {
				ShapeRenderer.draw(selector);
			}
		}

	}
}
