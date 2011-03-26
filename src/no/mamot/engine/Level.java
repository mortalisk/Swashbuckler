package no.mamot.engine;

import java.util.List;

import no.mamot.swashbuckler.editor.Obstacle;

public interface Level {

	List<GameObject> getGameObjectList();

	List<Drawable> getDrawableList();

}
