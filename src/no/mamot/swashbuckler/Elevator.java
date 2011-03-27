package no.mamot.swashbuckler;

import java.awt.Graphics2D;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.BasicJoint;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Polygon;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public class Elevator implements GameObject, Drawable {
	private Body engine;
	private Shape engineShape;
	private Body hook;
	private Shape hookShape;
	private Body platform;
	private Shape platformShape;
	private BasicJoint j1;
	private Shape j1Shape;
	private BasicJoint j2;
	private Shape j2Shape;
	private BasicJoint j3;
	private Shape j3Shape;

	public Elevator(float x, float y) {
		engine = new StaticBody("ElevatorEngine", new Circle(3.0f));
		engine.setPosition(x, y);
		engineShape = new org.newdawn.slick.geom.Circle(x, y, 3);

		hook = new Body("ElevatorHook", new Circle(1), 50);
		hook.setPosition(x, y + 100);
		hookShape = new org.newdawn.slick.geom.Circle(x, y, 1);

		platform = new Body("ElevatorPlatform", new Box(150.0f, 10.0f), 50);
		platform.setPosition(x - 75, y + 200);
		platformShape = new Rectangle(x - 75, y + 200,150, 10);

		j1 = new BasicJoint(engine, hook, new Vector2f(0, 100));
		j1Shape = new Line(x,y);
		j2 = new BasicJoint(hook, platform, new Vector2f(-75, 100));
		j2Shape = new Line(x,y);
		j3 = new BasicJoint(hook, platform, new Vector2f(75, 100));
		j3Shape = new Line(x,y);

	}

	@Override
	public void addPhysics(World world) {
		world.add(engine);
		world.add(hook);
		world.add(platform);
		world.add(j1);
		world.add(j2);
		world.add(j3);
	}
	
	@Override
	public ROVector2f getPosition() {
		return engine.getPosition();
	}
	
	@Override
	public void draw(Graphics g) {
		ROVector2f pos = engine.getPosition();
		engineShape.setLocation(pos.getX(), pos.getY());
		ShapeRenderer.draw(engineShape);
		
		pos = hook.getPosition();
		hookShape.setLocation(pos.getX(), pos.getY());
		ShapeRenderer.draw(hookShape);
		
		pos = platform.getPosition();
		platformShape.setLocation(pos.getX(), pos.getY());
		ShapeRenderer.draw(platformShape);
	}
	
	protected void drawBody( Body body) {
		if (body.getShape() instanceof Box) {
			drawBoxBody(body,(Box) body.getShape());
		}
		if (body.getShape() instanceof Circle) {
			drawCircleBody(body,(Circle) body.getShape());
		}
		if (body.getShape() instanceof Line) {
			drawLineBody(body,(Line) body.getShape());
		}
		if (body.getShape() instanceof Polygon) {
			drawPolygonBody(body,(Polygon) body.getShape());
		}
	}

	private void drawPolygonBody(Body body, Polygon shape) {
		// TODO Auto-generated method stub
		
	}

	private void drawLineBody(Body body, Line shape) {
		// TODO Auto-generated method stub
		
	}

	private void drawCircleBody(Body body, Circle shape) {
		// TODO Auto-generated method stub
		
	}

	private void drawBoxBody(Body body, Box shape) {
		// TODO Auto-generated method stub
		
	}

}
