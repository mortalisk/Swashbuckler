package no.mamot.engine;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
	List<GameObject> gameObjects = new ArrayList<GameObject>();
	List<Drawable> drawables = new ArrayList<Drawable>();

	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjects;
	}

	@Override
	public List<Drawable> getDrawableList() {
		// TODO Auto-generated method stub
		return drawables;
	}

}
