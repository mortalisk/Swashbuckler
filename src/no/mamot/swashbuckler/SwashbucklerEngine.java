package no.mamot.swashbuckler;

import net.phys2d.math.Vector2f;
import no.mamot.engine.Camera;
import no.mamot.engine.Engine;
import no.mamot.engine.GameProxy;
import no.mamot.engine.InputHandler;
import no.mamot.engine.Level;
import no.mamot.engine.Updateable;
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
	private Swashbuckler man;
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
		camera.setCenter(man.getPosition().getX(), man.getPosition().getY());

		for (int i = level.getUpdatableList().size()-1; i >= 0; i--) {
			level.getUpdatableList().get(i).update(delta);
		}		
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
			man.goTo(input.getMouseX() + camera.getTopLeftCorner().x,
					input.getMouseY() + camera.getTopLeftCorner().y);
		}
	}

	@Override
	public void init() throws SlickException {
		level = levelLoader.loadLevel();

		man = new Swashbuckler("/data/Swashbuckler/Swashbuckler.png", "Hero",
				15.5f, 300.0f, 180.0f, new org.newdawn.slick.geom.Vector2f(250,
						500), 100.0f);
		Robot robot = new Robot("/data/Robots/Robot1.png", "Robot1", 15.5f, 200.0f,
				370.0f, 180.0f, new org.newdawn.slick.geom.Vector2f(150, 500), man, 1.0f, 25.0f, 75.0f);
		
		Robat robat = new Robat("/data/Robots/Robat1.png", "Robat1", 15.5f, 300.0f,
				370.0f, 180.0f, new org.newdawn.slick.geom.Vector2f(200, 500), man, 1.0f, 25.0f, 75.0f);
		
		Tourmaline crystal = new Tourmaline("/data/Items/Tourmaline1.png", "Tourmaline1", 15.0f, 175.0f, 270.0f, 100.0f, man, level);
		
		//Elevator elevator = new Elevator(10, 50);
		
		level.AddToObjectList(man);
		level.AddToEntityList(man);
		level.AddToDrawableList(man);
		
		//level.AddToObjectList(elevator);
		//level.AddToDrawableList(elevator);
		
		level.AddToObjectList(robot);
		level.AddToDrawableList(robot);
		level.AddToUpdatableList(robot);
		
		level.AddToObjectList(robat);
		level.AddToDrawableList(robat);
		level.AddToUpdatableList(robat);
		
		level.AddToObjectList(crystal);
		level.AddToDrawableList(crystal);
		level.AddToUpdatableList(crystal);
		
		camera.setCenter(man.getPosition().getX(), man.getPosition().getY());

		view.setLevel(level);
		physics.setLevel(level);
		physics.init();
		
		//TODO: play game music
	}

}
