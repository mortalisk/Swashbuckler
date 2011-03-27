package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import no.mamot.engine.Drawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleSystem;

public class Obstacle implements Drawable{
	
	private Shape polygon;

	private boolean selected = false;
	private Color startCol;
	private Color endCol;
	private ShapeFill fill;
	private ShapeRenderer renderer;
	private List <ParticleObject> particles = new ArrayList<ParticleObject>();
	
	public Obstacle(Shape polygon){
		this.polygon = polygon;
		renderer = new ShapeRenderer();
		startCol = new Color(1.0f, 1.0f, 1.0f);
		endCol = new Color(1.0f, 1.0f, 1.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	public Shape getShape(){
		return polygon;
	}
	
	public void select(){
		if (!selected){
			makeRed();
			selected = true;
		}
		else {
			makeWhite();
			selected = false;
		}
	}
	
	private void makeRed(){
		startCol = new Color(1.0f, 0.0f, 0.0f);
		endCol = new Color(1.0f, 0.0f, 0.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	private void makeWhite(){
		startCol = new Color(1.0f, 1.0f, 1.0f);
		endCol = new Color(1.0f, 1.0f, 1.0f);
		fill = new GradientFill(0.0f, 0.0f, startCol, 1000.0f, 1000.0f ,endCol);
	}
	
	public void draw(Graphics g){
		renderer.draw(polygon, fill);
		for (ParticleObject particle : particles){
			particle.update(1000/60);
			particle.draw(null);
		}
	}
	
	public boolean isSelected (){
		return selected;
	}
	
	public void move(float x , float y){
		polygon.setLocation((x - (polygon.getWidth()/2)), (y - (polygon.getHeight()/2)));
		ParticleSystem system;
		for (ParticleObject particle : particles){
			system = particle.getParticleSystem();
			float oldx = system.getPositionX();
			float oldy = system.getPositionY();
			system.setPosition(x-oldx, y-oldy);
		}	
	}

	public List<ParticleObject> getParticles() {
		return particles;
	}
	
}
