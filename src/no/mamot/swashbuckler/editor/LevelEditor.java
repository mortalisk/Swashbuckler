package no.mamot.swashbuckler.editor;

import no.mamot.swashbuckler.Camera;
import no.mamot.swashbuckler.PlatformGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LevelEditor extends BasicGame {

	private Pointer pointer;
	private PolygonCreator creator;
	private LevelSaver levelSaver ;
	private Camera camera;
	
	public LevelEditor(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
		
		
		camera = new Camera(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT);
		pointer = new Pointer(camera);
		creator = new PolygonCreator(pointer);
		levelSaver = new LevelSaver(creator);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		
		pointer.handleInput(input);
		creator.handleInput(input);
		levelSaver.handleInput(input);
		camera.handleInput(input);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.translate(camera.getTopLeftCorner().x, camera.getTopLeftCorner().y);
		pointer.draw();
		creator.draw();
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new LevelEditor("Title"));
			app.setDisplayMode(1024, 768, false);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
