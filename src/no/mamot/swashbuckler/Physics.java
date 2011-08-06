package no.mamot.swashbuckler;

import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import no.mamot.engine.GameObject;
import no.mamot.engine.Level;

public class Physics implements CollisionListener {
	private Level level;
	World world = null;

	public Physics(float gravity) {
		world = new World(new net.phys2d.math.Vector2f(0, gravity), 1,
				new QuadSpaceStrategy(5, 5));
	}

	public void setLevel(Level level) {
		this.level = level;
	}
    public void addGameObject(GameObject object){
    	world.add(object.getBody());
    	object.setWorld(world);
    	object.getBody().setUserData(object);
    }
	public void init() {
		if (level != null) {
			for (int i = level.getGameObjectList().size() - 1; i >= 0; i--) {
				GameObject gameObject = level.getGameObjectList().get(i);
				//if (gameObject.collides()) {
					world.add(gameObject.getBody());
				//}
				gameObject.setWorld(world);
				gameObject.getBody().setUserData(gameObject);
			}
		}
		world.addListener(this);
	}

	public void doPhysics(int delta) {
		world.step();
	}

	@Override
	public void collisionOccured(CollisionEvent event) {
		((GameObject)event.getBodyA().getUserData()).collisionOccured(event, (GameObject)event.getBodyB().getUserData(), world);
		((GameObject)event.getBodyB().getUserData()).collisionOccured(event, (GameObject)event.getBodyA().getUserData(), world);
		
	}

}
