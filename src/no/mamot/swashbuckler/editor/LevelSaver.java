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

import no.mamot.swashbuckler.GameObstacle;
import no.mamot.swashbuckler.Level;
import no.mamot.swashbuckler.LevelImpl;
import no.mamot.swashbuckler.xml.LevelType;
import no.mamot.swashbuckler.xml.ObjectFactory;
import no.mamot.swashbuckler.xml.ObjectType;
import no.mamot.swashbuckler.xml.PointType;
import no.mamot.swashbuckler.xml.ShapeEnum;
import no.mamot.swashbuckler.xml.ShapeType;
import no.mamot.swashbuckler.xml.ShapeType.Points;

import org.newdawn.slick.Input;

public class LevelSaver {

	private PolygonCreator polygonCreator;
	private String fileName = "/data/testlevel.xml";

	public LevelSaver(PolygonCreator polygonCreator) {
		this.polygonCreator = polygonCreator;
	}

	public void save() {
		ObjectFactory obF = new ObjectFactory();

		LevelType level = obF.createLevelType();
		JAXBElement<LevelType> doc = obF.createLevel(level);
		List<Obstacle> obstacles = polygonCreator.getObstacles();
		for (int i = 0; i < obstacles.size(); ++i) {
			ObjectType objT = obF.createObjectType();
			ShapeType shapeT = obF.createShapeType();
			shapeT.setType(ShapeEnum.POLYGON);
			Points pointS = obF.createShapeTypePoints();
			shapeT.setPoints(pointS);
			List<PointType> pointList = shapeT.getPoints().getPoint();
			float[] points = obstacles.get(i).getShape().getPoints();
			for (int j = 0; j < points.length; ++j) {
				PointType point = obF.createPointType();
				point.setX(points[j]);
				point.setY(points[++j]);
				pointList.add(point);
			}
			objT.setShape(shapeT);
			level.getObject().add(objT);
		}
		try {
			System.out.println("Saving...");
			marshall(doc, fileName);
			System.out.println("Saving complete!");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleInput(Input input) {
		if (input.isKeyPressed(Input.KEY_S)
				&& input.isKeyPressed(Input.KEY_LCONTROL)) {
			save();
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
		JAXBElement<T> doc = (JAXBElement<T>) u.unmarshal(inputStream);
		return doc.getValue();
	}
	
	public Level loadLevel() {
		try {
			LevelType levelType = unmarshal(LevelType.class, new FileInputStream(
			"data/testlevel.xml"));
			Level level = new LevelImpl();
			
			for (ObjectType object : levelType.getObject()) {
				List<PointType> points = object.getShape().getPoints()
						.getPoint();
				float[] array = new float[points.size()*2];
				for (int i = 0; i < points.size(); i++) {
					array[i*2] = points.get(i).getX();
					array[i*2+1] = points.get(i).getY();
				}
				GameObstacle obstacle1 = new GameObstacle(array);
				
				level.getGameObjectList().add(obstacle1);

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
