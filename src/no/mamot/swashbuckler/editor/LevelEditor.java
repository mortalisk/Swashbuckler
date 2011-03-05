package no.mamot.swashbuckler.editor;

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
	public LevelEditor(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		pointer = new Pointer();
		creator = new PolygonCreator();
		levelSaver = new LevelSaver(creator);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		float x = input.getMouseX();
		float y = input.getMouseY();
		pointer.update(x, y);
		creator.getInput(input);
		levelSaver.checkInput(input);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
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
