package no.mamot.swashbuckler;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Matrix2f;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.AngleJoint;
import net.phys2d.raw.BasicJoint;
import net.phys2d.raw.Body;
import net.phys2d.raw.DistanceJoint;
import net.phys2d.raw.FixedJoint;
import net.phys2d.raw.Joint;
import net.phys2d.raw.SlideJoint;
import net.phys2d.raw.SpringJoint;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Polygon;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Elevator implements GameObject, Drawable {
	private Body engine;
	private Body hook;
	private Body anchor1;
	private Body anchor2;
	private Body platform;
	private Body test;
	private BasicJoint j1;
	private BasicJoint j2;
	private BasicJoint j3;
	private BasicJoint j4;
	private BasicJoint j0;

	public Elevator(float x, float y) {
		engine = new StaticBody("ElevatorEngine", new Circle(3.0f));
		engine.setPosition(x, y);

		hook = new Body("ElevatorHook", new Circle(1), 50);
		hook.setPosition(x, y + 100);
		platform = new Body("ElevatorPlatform", new Box(150.0f, 10.0f), 50);
		platform.setPosition(x, y + 200);
		
		anchor1 = new Body("ElevatorHook", new Circle(5), 50);
		anchor1.setPosition(x-50, y + 220);
		
		Circle testc = new Circle(7);
		test = new Body( testc, 5);
		test.setFriction(100);
		test.setPosition(200, 100);
		
		anchor2 = new Body("ElevatorHook", new Circle(5), 50);
		anchor2.setPosition(x+50, y + 220);
		j0 = new BasicJoint(engine, hook, new Vector2f(engine.getPosition()));
		j1 = new BasicJoint(hook, anchor1, new Vector2f(hook.getPosition()));
		j2 = new BasicJoint(hook, anchor2, new Vector2f(hook.getPosition()));
		j3 = new BasicJoint(anchor1, platform,new Vector2f(x-50, y + 220));
		j4 = new BasicJoint(anchor2, platform,new Vector2f(x+50, y + 220));

	}

	@Override
	public void addPhysics(World world) {
		world.add(test);
		world.add(engine);
		world.add(hook);
		world.add(platform);
		world.add(anchor1);
		world.add(anchor2);
		world.add(j0);
		world.add(j1);
		world.add(j2);
		world.add(j3);
		world.add(j4);
	}
	
	@Override
	public ROVector2f getPosition() {
		return engine.getPosition();
	}
	
	@Override
	public void draw(Graphics g) {
		test.setTorque(10000);
		drawBody(g, test);
		drawBody(g, engine);
		drawBody(g, hook);
		drawBody(g, platform);
		drawBody(g, anchor1);
		drawBody(g, anchor2);
		drawJoint(g, j0);
		drawJoint(g, j1);
		drawJoint(g, j2);
		drawJoint(g, j3);
		drawJoint(g, j4);
	}
	
	protected void drawBody(Graphics g,  Body body) {
		if (body.getShape() instanceof Box) {
			drawBoxBody(g,body,(Box) body.getShape());
		}
		if (body.getShape() instanceof Circle) {
			drawCircleBody(g,body,(Circle) body.getShape());
		}
		if (body.getShape() instanceof Line) {
			drawLineBody(g,body,(net.phys2d.raw.shapes.Line) body.getShape());
		}
		if (body.getShape() instanceof Polygon) {
			drawPolygonBody(g,body,(Polygon) body.getShape());
		}
	}

	/**
	 * Draw a polygon into the demo
	 * 
	 * @param g The graphics to draw the poly onto
	 * @param body The body describing the poly's position
	 * @param poly The poly to be drawn
	 */
	protected void drawPolygonBody(Graphics g, Body body, Polygon poly) {

		ROVector2f[] verts = poly.getVertices(body.getPosition(), body.getRotation());
		for ( int i = 0, j = verts.length-1; i < verts.length; j = i, i++ ) {			
			g.drawLine(
					(int) (0.5f + verts[i].getX()),
					(int) (0.5f + verts[i].getY()), 
					(int) (0.5f + verts[j].getX()),
					(int) (0.5f + verts[j].getY()));
		}
	}

	/**
	 * Draw a line into the demo
	 * 
	 * @param g The graphics to draw the line onto
	 * @param body The body describing the line's position
	 * @param line The line to be drawn
	 */
	protected void drawLineBody(Graphics g, Body body, net.phys2d.raw.shapes.Line line) {
//
//		float x = body.getPosition().getX();
//		float y = body.getPosition().getY();
//		float dx = line.getDX();
//		float dy = line.getDY();
//		
//		g.drawLine((int) x,(int) y,(int) (x+dx),(int) (y+dy));
		Vector2f[] verts = line.getVertices(body.getPosition(), body.getRotation());
		g.drawLine(
				(int) verts[0].getX(),
				(int) verts[0].getY(), 
				(int) verts[1].getX(),
				(int) verts[1].getY());
	}
	
	/**
	 * Draw a circle in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param circle The shape to be drawn
	 */
	protected void drawCircleBody(Graphics g, Body body, Circle circle) {
		float x = body.getPosition().getX();
		float y = body.getPosition().getY();
		float r = circle.getRadius();
		float rot = body.getRotation();
		float xo = (float) (Math.cos(rot) * r);
		float yo = (float) (Math.sin(rot) * r);
		
		g.drawOval((int) (x-r),(int) (y-r),(int) (r*2),(int) (r*2));
		g.drawLine((int) x,(int) y,(int) (x+xo),(int) (y+yo));
	}
	
	/**
	 * Draw a box in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param box The shape to be drawn
	 */
	protected void drawBoxBody(Graphics g, Body body, Box box) {
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
		
		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];
		
		g.drawLine((int) v1.x,(int) v1.y,(int) v2.x,(int) v2.y);
		g.drawLine((int) v2.x,(int) v2.y,(int) v3.x,(int) v3.y);
		g.drawLine((int) v3.x,(int) v3.y,(int) v4.x,(int) v4.y);
		g.drawLine((int) v4.x,(int) v4.y,(int) v1.x,(int) v1.y);
	}

	/**
	 * Draw a joint 
	 * 
	 * @param g The graphics contact on which to draw
	 * @param j The joint to be drawn
	 */
	public void drawJoint(Graphics g, Joint j) {
		if (j instanceof FixedJoint) {
			FixedJoint joint = (FixedJoint) j;
			
			float x1 = joint.getBody1().getPosition().getX();
			float x2 = joint.getBody2().getPosition().getX();
			float y1 = joint.getBody1().getPosition().getY();
			float y2 = joint.getBody2().getPosition().getY();
			
			g.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
		}
		if(j instanceof SlideJoint){
			SlideJoint joint = (SlideJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getAnchor2());
			p2.add(x2);
			
			Vector2f im = new Vector2f(p2);
			im.sub(p1);
			im.normalise();
			
			
			
			g.drawLine((int)p1.x,(int)p1.y,(int)(p1.x+im.x*joint.getMinDistance()),(int)(p1.y+im.y*joint.getMinDistance()));
			g.drawLine((int)(p1.x+im.x*joint.getMinDistance()),(int)(p1.y+im.y*joint.getMinDistance()),(int)(p1.x+im.x*joint.getMaxDistance()),(int)(p1.y+im.y*joint.getMaxDistance()));
		}
		if(j instanceof AngleJoint){
			AngleJoint angleJoint = (AngleJoint)j;
			Body b1 = angleJoint.getBody1();
			Body b2 = angleJoint.getBody2();
			float RA = j.getBody1().getRotation() + angleJoint.getRotateA();
			float RB = j.getBody1().getRotation() + angleJoint.getRotateB();
			
			Vector2f VA = new Vector2f((float) Math.cos(RA), (float) Math.sin(RA));
			Vector2f VB = new Vector2f((float) Math.cos(RB), (float) Math.sin(RB));
			
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
			
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,angleJoint.getAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,angleJoint.getAnchor2());
			p2.add(x2);
			
			g.drawLine((int)p1.x,(int)p1.y,(int)(p1.x+VA.x*20),(int)(p1.y+VA.y*20));
			g.drawLine((int)p1.x,(int)p1.y,(int)(p1.x+VB.x*20),(int)(p1.y+VB.y*20));
		}
		if (j instanceof BasicJoint) {
			BasicJoint joint = (BasicJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getLocalAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getLocalAnchor2());
			p2.add(x2);
	
			g.drawLine((int) x1.getX(), (int) x1.getY(), (int) p1.x, (int) p1.y);
			g.drawLine((int) p1.x, (int) p1.y, (int) x2.getX(), (int) x2.getY());
			g.drawLine((int) x2.getX(), (int) x2.getY(), (int) p2.x, (int) p2.y);
			g.drawLine((int) p2.x, (int) p2.y, (int) x1.getX(), (int) x1.getY());
		}
		if(j instanceof DistanceJoint){
			DistanceJoint joint = (DistanceJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getAnchor2());
			p2.add(x2);
			
			g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.x, (int) p2.y);
		}
		if (j instanceof SpringJoint) {
			SpringJoint joint = (SpringJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getLocalAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getLocalAnchor2());
			p2.add(x2);
			
			g.drawLine((int) x1.getX(), (int) x1.getY(), (int) p1.x, (int) p1.y);
			g.drawLine((int) p1.x, (int) p1.y, (int) p2.getX(), (int) p2.getY());
			g.drawLine((int) p2.getX(), (int) p2.getY(), (int) x2.getX(), (int) x2.getY());
		}
	}

}
