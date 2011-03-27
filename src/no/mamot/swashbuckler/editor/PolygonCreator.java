package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import no.mamot.engine.Level;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;

public class PolygonCreator {

	private LevelImplEditor level;
	private List <Dott> dotts;
	private List <Obstacle> obstacles;

	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public PolygonCreator(Level level){
		dotts = new ArrayList<Dott>();
		this.level = (LevelImplEditor) level;
		
		obstacles = this.level.getObstacleList();
		
	}
	
	public void createNewDott(float x, float y){
		Dott newDott = new Dott(x,y);
		dotts.add(newDott);
		level.getDrawableList().add(newDott);
	}
	
	private void removeDotts(){
		for (Dott dott : dotts){
			level.getDrawableList().remove(dott);
		}		
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
			level.getDrawableList().add(newObstacle);
			removeDotts();
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
				level.getDrawableList().remove(dotts.get(i));
				dotts.remove(i);
				i--;
			}
		}
		
		for (int i = 0 ; i < obstacles.size(); ++i){
			if (obstacles.get(i).isSelected()){
				level.getDrawableList().remove(obstacles.get(i));
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
