package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class EntityCreator {

	private List<Entity> entityList = new ArrayList<Entity>();
	private Entity swashbuckler = null;
	public void setSwashbuckler(Entity swashbuckler) {
		this.swashbuckler = swashbuckler;
	}

	private LevelImplEditor level;

	public EntityCreator(LevelImplEditor level) {
		this.level = level;
	}

	public void createNewEntity(TypeEnum type, float x, float y)
			throws SlickException {
		if (!type.equals(TypeEnum.SWASHBUCKLER)) {
			Entity newEntity = new Entity(type, x, y);

			entityList.add(newEntity);
			level.getDrawableList().add(newEntity);
		} else if (type.equals(TypeEnum.SWASHBUCKLER)) {
			if (swashbuckler != null) {
				// swashbuckler.setSelected(true);
				swashbuckler.move(x, y);
			} else {
				swashbuckler = new Entity(TypeEnum.SWASHBUCKLER, x, y);
				level.getDrawableList().add(swashbuckler);
			}
		}
	}

	public void select(TypeEnum type, float x, float y) {
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

	public void delete(TypeEnum type) {
		for (int i = entityList.size() - 1; i >= 0; i--)
			if (entityList.get(i).getType().equals(type)
					&& entityList.get(i).isSelected()) {

				level.getDrawableList().remove(entityList.get(i));
				entityList.remove(i);
			}
	}

	public void moveSelectedEntities(TypeEnum type, float x, float y) {
		for (Entity entity : entityList) {
			if (entity.getType().equals(type) && entity.isSelected()) {

				entity.move(x, y);
			}
		}
	}

	public Entity getSwashbuckler() {
		return swashbuckler;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	private void DeselectAll() {
		for (Entity entity : entityList) {
			entity.setSelected(false);
		}
	}
}
