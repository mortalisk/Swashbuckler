package no.mamot.engine;

import java.util.ArrayList;
import java.util.List;

import no.mamot.swashbuckler.GameEntity;

public class LevelImpl implements Level {
	List<GameObject> gameObjects = new ArrayList<GameObject>();
	List<GameEntity> gameEntities = new ArrayList<GameEntity>();
	List<Drawable> drawables = new ArrayList<Drawable>();

	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjects;
	}

	@Override
	public List<GameEntity> getEntityList() {
		return gameEntities;
	}

	@Override
	public List<Drawable> getDrawableList() {
		// TODO Auto-generated method stub
		return drawables;
	}

}
