package no.mamot.engine;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import no.mamot.swashbuckler.GameEntity;
import no.mamot.swashbuckler.editor.Obstacle;

public interface Level {

	List<GameObject> getGameObjectList();
	List<GameEntity> getEntityList();
	List<Drawable> getDrawableList();
}
