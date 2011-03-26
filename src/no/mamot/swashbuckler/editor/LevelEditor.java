package no.mamot.swashbuckler.editor;

import javax.swing.SwingUtilities;

import no.mamot.engine.Camera;
import no.mamot.engine.Engine;
import no.mamot.engine.GameProxy;
import no.mamot.engine.InputHandler;
import no.mamot.engine.Level;
import no.mamot.engine.LevelImpl;
import no.mamot.engine.View;
import no.mamot.engine.ViewImpl;
import no.mamot.swashbuckler.editor.state.LevelEditorState;
import no.mamot.swashbuckler.editor.state.PolygonState;
import no.mamot.swashbuckler.editor.state.StateFactory;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LevelEditor implements Engine{

	private Pointer pointer;
	private PolygonCreator polygonCreator;
	private LevelSaver levelSaver ;
	private Camera camera;
	private View view;
	private int screenWidth = 1024;
	private int screenHeight = 768;
	private GameProxy gameProxy;
	private LevelEditorState state;
	private Level level;
	private StateFactory stateFactory;
	private ParticleObject particleTest;

	public LevelEditor() throws SlickException {
		view = new ViewImpl(screenWidth, screenHeight);
		level = new LevelImpl();
		view.setLevel(level);
		camera = view.getCamera();
		polygonCreator = new PolygonCreator(level);
		state = new PolygonState(polygonCreator);
		stateFactory = new StateFactory();
		stateFactory.setLevelEditor(this);

		
		InputHandler inputHandler = new InputHandlerEditor(this,camera);
		gameProxy = new GameProxy("Swashbuckler Editor", view, inputHandler, this,
				screenWidth, screenHeight);
	}


	public static void main(String[] args) {
		try {
			LevelEditor levelEditor = new LevelEditor();			
			SwingUtilities.invokeLater(new ControlPanel(levelEditor));			
			levelEditor.start();
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void init() throws SlickException {
		pointer = new Pointer(camera);		
		levelSaver = new LevelSaver(polygonCreator);
		
		particleTest = new ParticleObject(100,200);
		particleTest.load();
		level.getDrawableList().add(particleTest);
	}


	@Override
	public void start() throws SlickException {
		gameProxy.start();
		
	}


	@Override
	public void updateWorld(int delta) {
		particleTest.update(delta);
		
	}
	
	public LevelEditorState getState() {
		return state;
	}
	public void setState(LevelEditorState state) {
		this.state = state;
	}


	public PolygonCreator getPolygonCreator() {
		// TODO Auto-generated method stub
		return polygonCreator;
	}
	
	public void setState(String changeto){
		if (changeto.equals("Draw_Polygon")){
			state = stateFactory.getPolygonState();
		}
		if (changeto.equals("Draw_Particle")){
			state = stateFactory.getParticleState();
		}
	}


}
