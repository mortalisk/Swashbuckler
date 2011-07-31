package no.mamot.swashbuckler.editor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import no.mamot.engine.Level;
import no.mamot.engine.LevelImpl;
import no.mamot.swashbuckler.GameEntity;
import no.mamot.swashbuckler.GameObstacle;
import no.mamot.swashbuckler.Swashbuckler;
import no.mamot.swashbuckler.SwashbucklerEngine;
import no.mamot.swashbuckler.xml.EntityEnum;
import no.mamot.swashbuckler.xml.EntityType;
import no.mamot.swashbuckler.xml.LevelType;
import no.mamot.swashbuckler.xml.ObjectFactory;
import no.mamot.swashbuckler.xml.ObstacleType;
import no.mamot.swashbuckler.xml.ParticleType;
import no.mamot.swashbuckler.xml.PlayerType;
import no.mamot.swashbuckler.xml.PointType;
import no.mamot.swashbuckler.xml.ShapeType;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class LevelSaver {

	private PolygonCreator polygonCreator;
	private String path = "data/levels/";
	private EntityCreator entityCreator;

	public LevelSaver(PolygonCreator polygonCreator, EntityCreator entityCreator) {
		this.polygonCreator = polygonCreator;
		this.entityCreator = entityCreator;
	}

	public void save(String name) {
		ObjectFactory obF = new ObjectFactory();

		String file = path + name + ".level.xml";
		LevelType level = obF.createLevelType();
		JAXBElement<LevelType> doc = obF.createLevel(level);
		saveObstacles(obF, level);
		saveEntities(obF, level);
		try {
			System.out.println("Saving...");
			marshall(doc, file);
			System.out.println("Saving complete!");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveEntities(ObjectFactory obF, LevelType level) {
		PlayerType player = obF.createPlayerType();
		player.setX(entityCreator.getSwashbuckler().getPosition().x);
		player.setY(entityCreator.getSwashbuckler().getPosition().y);
		level.setPlayer(player);
		level.setEntities(obF.createLevelTypeEntities());

		for (Entity e : entityCreator.getEntityList()) {
			EntityType entityT = obF.createEntityType();
			entityT.setType(EntityEnum.valueOf(e.getType().toString()));
			entityT.setX(e.getPosition().x);
			entityT.setY(e.getPosition().y);
			level.getEntities().getEntities().add(entityT);
		}
	}

	private void saveObstacles(ObjectFactory obF, LevelType level) {
		List<Obstacle> obstacles = polygonCreator.getObstacles();
		level.setObstacles(obF.createLevelTypeObstacles());
		for (int i = 0; i < obstacles.size(); ++i) {
			ObstacleType obstacleT = obF.createObstacleType();
			ShapeType shapeT = obF.createShapeType();
			obstacleT.setShape(shapeT);
			shapeT.setPoints(obF.createShapeTypePoints());
			List<PointType> pointsT = shapeT.getPoints().getPoint();
			Obstacle o = obstacles.get(i);

			obstacleT.setTexture(o.getTexture());

			float[] points = o.getShape().getPoints();
			for (int j = 0; j < points.length; j += 2) {
				PointType pointT = obF.createPointType();
				pointT.setX(points[j]);
				pointT.setY(points[j + 1]);
				pointsT.add(pointT);
			}

			obstacleT.setParticles(obF.createObstacleTypeParticles());
			for (ParticleObject p : o.getParticles()) {
				ParticleType particleT = obF.createParticleType();
				particleT.setX(p.getParticleSystem().getPositionX());
				particleT.setY(p.getParticleSystem().getPositionY());
				particleT.setDamage(p.getDamage());
				particleT.setRadius(p.getRadius());
				obstacleT.getParticles().getParticle().add(particleT);
			}

			level.getObstacles().getObstacle().add(obstacleT);
		}
	}

	void marshall(JAXBElement<LevelType> document, String pathname)
			throws JAXBException, IOException {
		Class<?> clazz = document.getValue().getClass();
		JAXBContext context = JAXBContext.newInstance(clazz.getPackage()
				.getName());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(document, new FileOutputStream(pathname));
	}

	public <T> T unmarshal(Class<T> docClass, InputStream inputStream)
			throws JAXBException {

		String packageName = docClass.getPackage().getName();
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<T> doc = (JAXBElement<T>) u.unmarshal(inputStream);
		return doc.getValue();
	}
	
	public void loadLevelForEditor(String name) {
		try {
			LevelType levelType = unmarshal(LevelType.class,
					new FileInputStream("data/levels/" +name +".level.xml"));
			
			// clear stuff
			entityCreator.getEntityList().clear();
			polygonCreator.getObstacles().clear();
			Level level = entityCreator.getLevel();
			level.getDrawableList().clear();
			level.getUpdatableList().clear();
			
			
			// SET ENTITIES
			List<EntityType> entities = levelType.getEntities().getEntities();
			for (EntityType et : entities) {
				Entity entity = new Entity(TypeEnum.valueOf(et.getType().toString()), (float)et.getX(), (float)et.getY());
				entityCreator.getEntityList().add(entity);
				level.getDrawableList().add(entity);
			}
			
			// SET PLAYER
			Entity swashbuckler = new Entity(TypeEnum.SWASHBUCKLER, (float)levelType.getPlayer().getX(), (float)levelType.getPlayer().getY());
			entityCreator.setSwashbuckler(swashbuckler);
			level.getDrawableList().add(swashbuckler);
			
			// SET OBSTACLES
			List<ObstacleType> obstacles = levelType.getObstacles().getObstacle();
			for (ObstacleType ot : obstacles) {
				List<PointType> points = ot.getShape().getPoints().getPoint();
				float[] poly = new float[points.size()*2];
				for (int i = 0; i<points.size(); i+=2) {
					poly[i] = points.get(i).getX();
					poly[i+1] = points.get(i).getY();
				}
				Shape polygon = new Polygon(poly);
				Obstacle obstacle = new Obstacle(polygon, ot.getTexture());
				polygonCreator.getObstacles().add(obstacle);
				level.getDrawableList().add(obstacle);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Level loadLevel() throws SlickException {
		try {
			LevelType levelType = unmarshal(LevelType.class,
					new FileInputStream("data/levels/hei.level.xml"));
			LevelImpl level = new LevelImpl();

			float manX = (float) levelType.getPlayer().getX();
			float manY = (float) levelType.getPlayer().getY();

			Swashbuckler man = new Swashbuckler(
					"/data/Swashbuckler/Swashbuckler.png", "Hero", 15.5f, manX,
					manY, new org.newdawn.slick.geom.Vector2f(250, 500), 100.0f);

			level.setMan(man);
			level.getDrawableList().add(man);
			level.getGameObjectList().add(man);

			for (ObstacleType obstacle : levelType.getObstacles().getObstacle()) {
				List<PointType> points = obstacle.getShape().getPoints()
						.getPoint();
				float[] array = new float[points.size() * 2];
				for (int i = 0; i < points.size(); i++) {
					array[i * 2] = points.get(i).getX();
					array[i * 2 + 1] = points.get(i).getY();
				}
				GameObstacle obstacle1 = new GameObstacle(array,
						obstacle.getTexture());
				// obstacle is both drawable and a game object
				level.getDrawableList().add(obstacle1);
				level.getGameObjectList().add(obstacle1);

			}

			for (EntityType entityType : levelType.getEntities().getEntities()) {
				TypeEnum type = TypeEnum.valueOf(entityType.getType()
						.toString());
				GameEntity entity = type.getInstance();
				entity.setPlayer(man);
				entity.setPlayerLevel(level);
				entity.setPosition((float) entityType.getX(),
						(float) entityType.getY());
				entity.init();
				level.getDrawableList().add(entity);
				level.getGameObjectList().add(entity);
				level.getUpdatableList().add(entity);

			}

			return level;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
