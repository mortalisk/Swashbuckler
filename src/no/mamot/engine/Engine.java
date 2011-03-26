package no.mamot.engine;

import org.newdawn.slick.SlickException;

public interface Engine {

	void updateWorld(int delta);

	// TODO replace with GameCouldNotStartException
	void start() throws SlickException;
	
	void init() throws SlickException;

}
