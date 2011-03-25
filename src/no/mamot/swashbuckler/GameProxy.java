package no.mamot.swashbuckler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import no.mamot.swashbuckler.xml.LevelType;
import no.mamot.swashbuckler.xml.ObjectType;
import no.mamot.swashbuckler.xml.PointType;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class GameProxy extends BasicGame {

	private InputHandler inputHandler;
	private Engine engine;
	private View view;

	AppGameContainer app = null;

	public GameProxy(String title, View view, InputHandler inputHandler, Engine engine, int x, int y)
			throws SlickException {
		super(title);
		this.view = view;
		this.inputHandler = inputHandler;
		this.engine = engine;
		app = new AppGameContainer(this);
		app.setDisplayMode(x, y, false);
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {

		arg0.setTargetFrameRate(60);
		engine.init();

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		inputHandler.handleInput(gc.getInput(), delta);
		engine.updateWorld(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		view.draw(g);
	}

	public void start() throws SlickException {
		app.start();
	}

}
