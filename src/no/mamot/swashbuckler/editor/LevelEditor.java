package no.mamot.swashbuckler.editor;

import javax.swing.SwingUtilities;

import no.mamot.engine.Camera;
import no.mamot.engine.Engine;
import no.mamot.engine.GameProxy;
import no.mamot.engine.InputHandler;
import no.mamot.engine.View;
import no.mamot.engine.ViewImpl;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LevelEditor implements Engine{

	private Pointer pointer;
	private PolygonCreator creator;
	private LevelSaver levelSaver ;
	private Camera camera;
	private View view;
	private int screenWidth = 1024;
	private int screenHeight = 768;
	private GameProxy gameProxy;
	private LevelEditorState state;

	public LevelEditor() throws SlickException {
		view = new ViewImpl(screenWidth, screenHeight);
		camera = view.getCamera();
		creator = new PolygonCreator(pointer);
		state = new PolygonState(creator);
		InputHandler inputHandler = new InputHandlerEditor(this,camera);
		gameProxy = new GameProxy("Swashbuckler Editor", view, inputHandler, this,
				screenWidth, screenHeight);
	}

/*
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		
		pointer.handleInput(input);
		creator.handleInput(input);
		levelSaver.handleInput(input);
		camera.handleInput(input);
	}


	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.translate(camera.getTopLeftCorner().x, camera.getTopLeftCorner().y);
	
		creator.draw();
	}
	
	*/
	public static void main(String[] args) {
		try {
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ControlPanel inst = new ControlPanel();
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
			LevelEditor levelEditor = new LevelEditor();
			levelEditor.start();
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void init() throws SlickException {
		// TODO Auto-generated method stub
		
		pointer = new Pointer(camera);		
		levelSaver = new LevelSaver(creator);
	}


	@Override
	public void start() throws SlickException {
		// TODO Auto-generated method stub
		gameProxy.start();
	}


	@Override
	public void updateWorld(int delta) {
		// TODO Auto-generated method stub
		
	}
	
	public LevelEditorState getState() {
		return state;
	}
	public void setState(LevelEditorState state) {
		this.state = state;
	}


}
