package no.mamot.swashbuckler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import no.mamot.swashbuckler.xml.LevelType;
import no.mamot.swashbuckler.xml.ObjectType;
import no.mamot.swashbuckler.xml.PointType;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class PlatformGame extends BasicGame {

	static AppGameContainer app = null;

	private GameEntity man = null;
	private List<GameObject> objectList = null;
	private Camera camera = null;
	private LevelType level = null;

	public PlatformGame(String title) {
		super(title);

		// TODO Auto-generated constructor stub
	}

	public <T> T unmarshal(Class<T> docClass, InputStream inputStream)
			throws JAXBException {

		String packageName = docClass.getPackage().getName();
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		JAXBElement<T> doc = (JAXBElement<T>) u.unmarshal(inputStream);
		return doc.getValue();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		try {
			camera = new Camera();
			
			camera.setScreenHeight(arg0.getHeight());
			camera.setScreenWidth(arg0.getWidth());
			
			level = unmarshal(LevelType.class, new FileInputStream(
					"data/testlevel.xml"));
			man = new GameEntity("/data/WWFSoldierUzi.png", 18.0f, 350.0f,
					200.0f);
			camera.setCenter(man.getPosition());
			
			objectList = new ArrayList<GameObject>();
			
			for (ObjectType object : level.getObject()) {
				List<PointType> points = object.getShape().getPoints()
						.getPoint();
				float[] array = new float[points.size()*2];
				for (int i = 0; i < points.size(); i++) {
					array[i*2] = points.get(i).getX();
					array[i*2+1] = points.get(i).getY();
				}
				GameObstacle obstacle1 = new GameObstacle(array);
				
				objectList.add(obstacle1);

			}

			arg0.setTargetFrameRate(60);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		man.update(gc, delta, objectList);
		
		camera.setCenter(man.getPosition());
		//camera.setTopLeftCorner(man.getPosition());
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.translate(-camera.getTopLeftCorner().x, -camera.getTopLeftCorner().y);
		man.draw();		
		drawObstacles();
	}

	private void drawObstacles() {
		for (GameObject obstacle : objectList) {
			obstacle.draw();
		}
	}

	public static void main(String[] args) {
		try {
			app = new AppGameContainer(new PlatformGame("Title"));
			app.setDisplayMode(1024, 768, false);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
