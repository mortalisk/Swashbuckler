package no.mamot.swashbuckler.editor;

import javax.swing.SwingUtilities;

import no.mamot.engine.Camera;
import no.mamot.engine.Engine;
import no.mamot.engine.GameProxy;
import no.mamot.engine.InputHandler;
import no.mamot.engine.Updateable;
import no.mamot.engine.View;
import no.mamot.engine.ViewImpl;
import no.mamot.swashbuckler.editor.state.LevelEditorState;
import no.mamot.swashbuckler.editor.state.PolygonState;
import no.mamot.swashbuckler.editor.state.StateFactory;

import org.newdawn.slick.SlickException;

public class LevelEditor implements Engine {

	private PolygonCreator polygonCreator;
	private LevelSaver levelSaver;
	private Camera camera;
	private View view;
	private int screenWidth = 1024;
	private int screenHeight = 768;
	private GameProxy gameProxy;
	private LevelEditorState state;
	private LevelImplEditor level;
	private StateFactory stateFactory;
	private boolean loadLevel = false;
	private String levelToLoad = "";

	private EntityCreator entityCreator;

	public LevelEditor() throws SlickException {
		view = new ViewImpl(screenWidth, screenHeight);
		level = new LevelImplEditor();
		view.setLevel(level);
		camera = view.getCamera();
		polygonCreator = new PolygonCreator(level);

		entityCreator = new EntityCreator(level);
		state = new PolygonState(polygonCreator);
		stateFactory = new StateFactory();
		stateFactory.setLevelEditor(this);

		InputHandler inputHandler = new InputHandlerEditor(this, camera);
		gameProxy = new GameProxy("Swashbuckler Editor", view, inputHandler,
				this, screenWidth, screenHeight);
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

	public void save(String name) {
		levelSaver.save(name);
	}

	@Override
	public void init() throws SlickException {
		levelSaver = new LevelSaver(polygonCreator, entityCreator);

	}

	@Override
	public void start() throws SlickException {
		gameProxy.start();

	}

	@Override
	public void updateWorld(int delta) {
		if (loadLevel){
			System.out.println("hei");
			levelSaver.loadLevelForEditor(levelToLoad);
			loadLevel = false;
		}
		for (Updateable updateable : level.getUpdatableList()) {
			updateable.update(delta);
		}


	}

	public LevelEditorState getState() {
		return state;
	}

	public void setState(LevelEditorState state) {
		this.state = state;
	}

	// String based
	public void setState(DrawEnum changeto) {
		if (changeto.equals(DrawEnum.DRAW_POLYGON)) {
			state = stateFactory.getPolygonState();
		}
		if (changeto.equals(DrawEnum.DRAW_FIREPARTICLE)) {
			state = stateFactory.getFireParticleState();
		}
		if (changeto.equals(DrawEnum.DRAW_HEALPARTICLE)) {
			state = stateFactory.getHealParticleState();
		}
		if (changeto.equals(DrawEnum.DRAW_ROBOT)) {
			state = stateFactory.getRobotState();
		}
		if (changeto.equals(DrawEnum.DRAW_RHINOBOT)) {
			state = stateFactory.getRhinobotState();
		}
		if (changeto.equals(DrawEnum.DRAW_ROBAT)) {
			state = stateFactory.getRobatState();
		}
		if (changeto.equals(DrawEnum.DRAW_TOURMALINE)) {
			state = stateFactory.getTourmalineState();
		}
		if (changeto.equals(DrawEnum.DRAW_SWASHBUCKLER)) {
			state = stateFactory.getSwashbucklerState();
		}
		if (changeto.equals(DrawEnum.DRAW_SPACESHIP)) {
			state = stateFactory.getSpaceshipState();
		}

	}

	public StateFactory getStateFactory() {
		return stateFactory;
	}

	public PolygonCreator getPolygonCreator() {
		// TODO Auto-generated method stub
		return polygonCreator;
	}

	public LevelImplEditor getLevel() {
		return (LevelImplEditor) level;
	}



	public EntityCreator getEntityCreator() {
		return entityCreator;
	}

	public void load(String string) {
		levelToLoad = string;
		loadLevel = true;
	}

}
