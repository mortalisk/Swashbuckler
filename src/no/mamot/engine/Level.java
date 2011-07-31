package no.mamot.engine;

import java.util.List;

import no.mamot.swashbuckler.Swashbuckler;

public interface Level {

	List<GameObject> getGameObjectList();

	List<Drawable> getDrawableList();

	List<Updateable> getUpdatableList();

	void removeObject(Object object);
	
	Swashbuckler getMan();
	
}
