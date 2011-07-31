package no.mamot.swashbuckler;

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
import org.newdawn.slick.Sound;

public class SwashbucklerEngine implements Engine, InputHandler {

	private GameProxy gameProxy;
	private Camera camera;
	private Level level;
	private View view;
	private LevelSaver levelLoader = new LevelSaver(null, null);
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

		for (int i = level.getUpdatableList().size() - 1; i >= 0; i--) {
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
		level = levelLoader.loadLevel(this);
		

		camera.setCenter(man.getPosition().getX(), man.getPosition().getY());

		view.setLevel(level);
		physics.setLevel(level);
		physics.init();

		Sound music = new Sound("data/Music/backgroundSound.wav");
		music.loop();
	}

	public Swashbuckler getMan() {
		return man;
	}

	public void setMan(Swashbuckler man) {
		this.man = man;
	}

}
