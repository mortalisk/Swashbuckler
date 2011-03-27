package no.mamot.swashbuckler;

import net.phys2d.math.Vector2f;
import no.mamot.engine.Camera;
import no.mamot.engine.Engine;
import no.mamot.engine.GameProxy;
import no.mamot.engine.InputHandler;
import no.mamot.engine.Level;
import no.mamot.engine.View;
import no.mamot.engine.ViewImpl;
import no.mamot.swashbuckler.editor.LevelSaver;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SwashbucklerEngine implements Engine, InputHandler {

	private GameProxy gameProxy;
	private Camera camera;
	private Level level;
	private View view;
	private LevelSaver levelLoader = new LevelSaver(null);
	private GameEntity man;
	int screenHeight = 768;
	int screenWidth = 1024;
	private Physics physics;

	public SwashbucklerEngine() throws SlickException {
		view = new ViewImpl(screenWidth, screenHeight);
		camera = view.getCamera();
		gameProxy = new GameProxy("Swashbuckler", view, this, this,
				screenWidth, screenHeight);
		physics = new Physics(500);

	}

	@Override
	public void updateWorld(int delta) {
		physics.doPhysics(delta);
		camera.setCenter(man.getPosition().getX(),man.getPosition().getY());
	}

	@Override
	public void start() throws SlickException {
		gameProxy.start();
	}

	public static void main(String[] args) throws SlickException {
		Engine swashbuckler = new SwashbucklerEngine();
		swashbuckler.start();
	}
	
	@Override
	public void handleInput(Input input, int delta) {
		// movement
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			man.jump();
		}
		if (input.isKeyDown(Input.KEY_W)) {
			// position.y -= speed / delta;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			// position.y += speed / delta;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			man.left(delta);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			man.right(delta);
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			man.goTo(input.getMouseX() + camera.getTopLeftCorner().x,input.getMouseY() + camera.getTopLeftCorner().y);
		}
	}

	@Override
	public void init() throws SlickException {
		level = levelLoader.loadLevel();

		man = new GameEntity("/data/WWFSoldierUzi.png", "Hero", 19.0f, 350.0f, 200.0f, new org.newdawn.slick.geom.Vector2f(250,500));
		Elevator elevator = new Elevator(10, 50);
		level.getGameObjectList().add(man);
		level.getEntityList().add(man);
		level.getDrawableList().add(man);
		level.getGameObjectList().add(elevator);
		level.getDrawableList().add(elevator);
		camera.setCenter(man.getPosition().getX(),man.getPosition().getY());

		view.setLevel(level);
		physics.setLevel(level);
		physics.init();
	}

}
