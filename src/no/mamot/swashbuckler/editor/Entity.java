package no.mamot.swashbuckler.editor;

import net.phys2d.math.Vector2f;
import no.mamot.engine.Drawable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity implements Drawable {
	private Vector2f position;
	private String type;
	private boolean selected;
	private Image image;
	
	public Entity(String type, float x, float y) throws SlickException {
		this.type = type;
		position = new Vector2f(x, y);
		
		if (type.equals("Robot")) {
			image = new Image("/data/Robots/Robot1.png");
		}
	}
	
	public void move(float x, float y) {
		position.x = x;
		position.y = y;
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
	
	public String getType() {
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
		image.draw(position.x, position.y);
	}
}
