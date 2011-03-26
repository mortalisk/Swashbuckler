package no.mamot.engine;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
	List<GameObject> gameObjects = new ArrayList<GameObject>();

	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjects;
	}

}
