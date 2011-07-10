package no.mamot.engine;

import java.util.List;

import no.mamot.swashbuckler.Swashbuckler;

public interface Level {

	List<GameObject> getGameObjectList();
	List<Swashbuckler> getEntityList();
	List<Drawable> getDrawableList();
	List<Updateable> getUpdatableList();
	void RemoveObject(Object object);
	void AddToObjectList(GameObject object);
	void AddToEntityList(Swashbuckler object);
	void AddToDrawableList(Drawable object);
	void AddToUpdatableList(Updateable object);
}
