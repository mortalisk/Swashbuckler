package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import no.mamot.engine.GameObject;
import no.mamot.engine.LevelImpl;
import no.mamot.engine.Updateable;

public class LevelImplEditor extends LevelImpl{

	List<Obstacle> obstacles = new ArrayList<Obstacle>();


	public List<Obstacle> getObstacleList() {
		return obstacles;
	}
}
