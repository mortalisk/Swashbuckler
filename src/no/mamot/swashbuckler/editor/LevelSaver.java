package no.mamot.swashbuckler.editor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
	private String fileName;

	public LevelSaver(PolygonCreator polygonCreator) {
		this.polygonCreator = polygonCreator;
	}
	
	public void save(){
		ObjectFactory obF = new ObjectFactory();
		
		LevelType level = obF.createLevelType();
		JAXBElement<LevelType> doc = obF.createLevel(level);
		List <Obstacle> obstacles = polygonCreator.getObstacles();
		for (int i = 0 ; i < obstacles.size(); ++i){
			ObjectType objT = obF.createObjectType();
			ShapeType shapeT = obF.createShapeType();
			shapeT.setType(ShapeEnum.POLYGON);
			Points pointS = obF.createShapeTypePoints();
			shapeT.setPoints(pointS);
			List <PointType> pointList = shapeT.getPoints().getPoint();
			float [] points = obstacles.get(i).getShape().getPoints();
			for (int j = 0 ; j < points.length; ++j){
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
			writeDocument(doc, "data/testlevel.xml");
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
		if (input.isKeyPressed(Input.KEY_S)	&& input.isKeyPressed(Input.KEY_LCONTROL)) {
			save();
		}
	}

	void writeDocument(JAXBElement<LevelType> document, String pathname)
			throws JAXBException, IOException {
		Class<?> clazz = document.getValue().getClass();
		JAXBContext context = JAXBContext.newInstance(clazz.getPackage()
				.getName());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(document, new FileOutputStream(pathname));
	}

}
