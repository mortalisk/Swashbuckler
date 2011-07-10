package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class EntityCreator {

	private List<Entity> entityList = new ArrayList<Entity>();
	private LevelImplEditor level;
	
	public EntityCreator(LevelImplEditor level) {
		this.level = level;
	}

	public void createNewEntity(String type, float x, float y) throws SlickException {
		Entity newEntity = new Entity(type, x, y);

		entityList.add(newEntity);
		level.getDrawableList().add(newEntity);
	}

	public void select(String type, float x, float y) {
		DeselectAll();
		
		for (Entity entity : entityList) {
			if (entity.getType().equals(type)
					&& x >= entity.getPosition().getX()
					&& x <= (entity.getPosition().getX() + entity.getWidth())
					&& y >= entity.getPosition().getY()
					&& y <= (entity.getPosition().getY() + entity.getHeight())) {

				if (entity.isSelected()) {
					entity.setSelected(false);
				} else {
					entity.setSelected(true);
				}
			}
		}
	}

	public void delete(String type) {
		for (int i = entityList.size() - 1; i >= 0; i--)
			if (entityList.get(i).getType().equals(type)
					&& entityList.get(i).isSelected()) {

				level.getDrawableList().remove(entityList.get(i));
				entityList.remove(i);				
			}
	}
	
	public void moveSelectedEntities(String type, float x, float y) {
		for (Entity entity : entityList) {
			if (entity.getType().equals(type)
					&& entity.isSelected()) {

				entity.move(x, y);
			}
		}
	}
	
	private void DeselectAll() {
		for (Entity entity : entityList) {
			entity.setSelected(false);
		}
	}
}
