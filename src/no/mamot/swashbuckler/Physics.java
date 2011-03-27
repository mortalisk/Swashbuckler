package no.mamot.swashbuckler;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import no.mamot.engine.GameObject;
import no.mamot.engine.Level;

public class Physics implements CollisionListener{
	private Level level;
	World world = null;

	public Physics() {
		world = new World(new net.phys2d.math.Vector2f(0,500), 1, new QuadSpaceStrategy(20,5));
	}
	
	public Physics(float gravity) {
		world = new World(new net.phys2d.math.Vector2f(0,gravity), 5, new QuadSpaceStrategy(20,5));
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void init() {
		if (level!= null) {
			for (GameObject object :level.getGameObjectList()) {
				object.addPhysics(world);
				//world.add(object.getBody());
			}
		}
		world.addListener(this);
	}

	public void doPhysics(int delta) {
		world.step();		
	}


	@Override
	public void collisionOccured(CollisionEvent event) {
		//the acting object is now resting
		event.getBodyB().setIsResting(true);
		event.getBodyA().setIsResting(true);
		System.out.println(event);
	}

}
