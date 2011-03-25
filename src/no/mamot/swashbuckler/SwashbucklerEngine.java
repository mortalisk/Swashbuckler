package no.mamot.swashbuckler;

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

	public SwashbucklerEngine() throws SlickException {
		view = new ViewImpl(screenWidth, screenHeight);
		camera = view.getCamera();
		gameProxy = new GameProxy("Swashbuckler", view, this, this,
				screenWidth, screenHeight);

	}

	@Override
	public void updateWorld(int delta) {
		camera.setCenter(man.getPosition());
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
			if (man.velocityVector.y >= 0) {
				man.velocityVector.set(0.0f, man.jumpSpeed);
			}
		}
		if (input.isKeyDown(Input.KEY_W)) {
			// position.y -= speed / delta;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			// position.y += speed / delta;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			man.velocityVector.x -= man.acceleration / delta;
			if (man.velocityVector.x < -man.maxSpeed) {
				man.velocityVector.x = -man.maxSpeed;
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			man.velocityVector.x += man.acceleration / delta;
			if (man.velocityVector.x > man.maxSpeed) {
				man.velocityVector.x = man.maxSpeed;
			}
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			man.setPosition(input.getMouseX() + camera.getTopLeftCorner().x,input.getMouseY() + camera.getTopLeftCorner().y);
		}
	}

	@Override
	public void init() throws SlickException {

		level = levelLoader.loadLevel();

		man = new GameEntity("/data/WWFSoldierUzi.png", 18.0f, 350.0f, 200.0f);
		level.getGameObjectList().add(man);
		camera.setCenter(man.getPosition());

		view.setLevel(level);
	}

}
