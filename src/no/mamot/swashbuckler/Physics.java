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
	World world = new World(new net.phys2d.math.Vector2f(0,500), 5, new QuadSpaceStrategy(20,5));

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void init() {
		if (level!= null) {
			for (GameObject object :level.getGameObjectList()) {
				world.add(object.getBody());
			}
		}
		world.addListener(this);
	}

	public void doPhysics(int delta) {
		world.step();		
	}


	@Override
	public void collisionOccured(CollisionEvent event) {
		System.out.println(event);
	}

}
