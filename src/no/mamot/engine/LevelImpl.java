package no.mamot.engine;

import java.util.ArrayList;
import java.util.List;

import no.mamot.swashbuckler.Swashbuckler;

public class LevelImpl implements Level {
	List<GameObject> gameObjects = new ArrayList<GameObject>();
	List<Swashbuckler> gameEntities = new ArrayList<Swashbuckler>();
	List<Drawable> drawables = new ArrayList<Drawable>();
	List<Updateable> updateables = new ArrayList<Updateable>();

	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjects;
	}

	@Override
	public List<Swashbuckler> getEntityList() {
		return gameEntities;
	}

	@Override
	public List<Drawable> getDrawableList() {
		return drawables;
	}

	@Override
	public List<Updateable> getUpdatableList() {
		return updateables;
	}

	@Override
	public void RemoveObject(Object o) {
		gameObjects.remove(o);
		gameEntities.remove(o);
		drawables.remove(o);
		updateables.remove(o);
	}

	@Override
	public void AddToObjectList(GameObject object) {
		gameObjects.add(object);
	}

	@Override
	public void AddToEntityList(Swashbuckler object) {
		gameEntities.add(object);
	}

	@Override
	public void AddToDrawableList(Drawable object) {
		drawables.add(object);
	}

	@Override
	public void AddToUpdatableList(Updateable object) {
		updateables.add(object);
	}

	

}
