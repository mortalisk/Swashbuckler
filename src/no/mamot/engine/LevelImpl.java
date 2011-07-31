package no.mamot.engine;

import java.util.ArrayList;
import java.util.List;

import no.mamot.swashbuckler.Swashbuckler;

public class LevelImpl implements Level {
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private List<Drawable> drawables = new ArrayList<Drawable>();
	private List<Updateable> updateables = new ArrayList<Updateable>();
	private Swashbuckler man;

	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjects;
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
	public void removeObject(Object o) {
		gameObjects.remove(o);
		drawables.remove(o);
		updateables.remove(o);
	}

	public Swashbuckler getMan() {
		return man;
	}

	public void setMan(Swashbuckler man) {
		this.man = man;
	}
	
	

}
