package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;

public class PolygonCreator {

	private List <Dott> dotts;
	private List <Obstacle> obstacles;
	private Pointer pointer;
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public PolygonCreator(Pointer pointer){
		dotts = new ArrayList<Dott>();
		obstacles = new ArrayList<Obstacle>();
		this.pointer = pointer;
	}
	
	public void createNewDott(float x, float y){
		Dott newDott = new Dott(x,y);
		dotts.add(newDott);
	}
	
	private void removeDotts(){
		dotts.clear();
	}
	
	public void createNewPolygon(){
		
		if (dotts.size() > 2){
			// make a polygon of these dotts...
			
			int size = dotts.size();
			
			float [] points = new float[size*2];
			int j = 0;
			for (int i = 0; i < dotts.size() ; i++){						
				points [j] = dotts.get(i).getPosition().x;					
				j++;					
				points [j] = dotts.get(i).getPosition().y;					
				j++;
			}
			Polygon newPolygon = new Polygon(points);
			Obstacle newObstacle = new Obstacle(newPolygon);
			obstacles.add(newObstacle);
			removeDotts();
		}

		
	}
	
	public void draw(){
		for (Dott dott : dotts){
			dott.draw();
		}
		for (Obstacle obstacle : obstacles){
			obstacle.draw();
			Polygon polygon = (Polygon)obstacle.getShape();
			float[] points = polygon.getPoints();
			for (int i = 0; i<points.length;i+=2) {
				ShapeRenderer.draw(new Dott(points[i], points[i+1]).getShape());
			}
		}
	}
	public void selectDot(float x , float y){
		for (Dott dott : dotts){
			if (dott.getShape().contains(x, y)){					
				dott.select();		
				
			}
		}
	}
	
	public void selectPolygon(float x , float y){
		for (Obstacle obstacle : obstacles){
			if (obstacle.getShape().contains(x, y)){					
				obstacle.select();		
				
			}
		}
	}
	
	public void deleteSelected(){
		for (int i = 0 ; i < dotts.size(); ++i){
			if (dotts.get(i).isSelected()){
				dotts.remove(i);
				i--;
			}
		}
		
		for (int i = 0 ; i < obstacles.size(); ++i){
			if (obstacles.get(i).isSelected()){
				obstacles.remove(i);
				i--;
			}
		}		
	}
	
	public void moveSelectedPolygons(float x, float y){
		for (int i = 0 ; i < obstacles.size();++i){
			if (obstacles.get(i).isSelected()){
				Obstacle obstacle = obstacles.get(i);
				// move to mouse position..
				obstacle.move(x, y);
			}
		}
	}	
	
	public boolean noDottsSelected() {
		// TODO Auto-generated method stub
		boolean noDottsSelected = true;
		for (Dott dott : dotts){
			if (dott.isSelected()) return false;
		}		
		return noDottsSelected;
	}
	
	public boolean noObstaclesSelected(){
		boolean noObstaclesSelected = true;
		for (Obstacle obstacle : obstacles){
			if (obstacle.isSelected()) return false;
		}		
		return noObstaclesSelected;
	}
	
	
}
