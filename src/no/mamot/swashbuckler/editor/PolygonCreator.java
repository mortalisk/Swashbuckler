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
	
	private ShapeRenderer renderer;
	
	public PolygonCreator(){
		dotts = new ArrayList<Dott>();
		obstacles = new ArrayList<Obstacle>();
		renderer = new ShapeRenderer();
	}
	
	public void createNewDott(float x, float y){
		Dott newDott = new Dott(x,y);
		dotts.add(newDott);
	}
	
	private void removeDotts(){
		dotts = new ArrayList<Dott>();
	}
	
	public Polygon createNewPolygon(float [] points){
		Polygon newPolygon = new Polygon(points);
		return newPolygon;
		
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
				renderer.draw(new Dott(points[i], points[i+1]).getShape());
			}
		}
	}
	
	
	public void getInput(Input input){
		
		// Check mouse position
		float mouseX = input.getMouseX();
		float mouseY = input.getAbsoluteMouseY();
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			
			if (noObstaclesSelected()){
				for (Dott dott : dotts){
					if (dott.getShape().contains(mouseX, mouseY)){					
						dott.select();		
						
					}
				}
			}
			if (noDottsSelected()){ // then i can check to see if i selected some polygon..
				for (Obstacle obstacle : obstacles){
					if (obstacle.getShape().contains(mouseX, mouseY)){					
						obstacle.select();		
						
					}
				}
			}
		}
		
		
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			// create new dott in this point...
			float x = input.getMouseX();
			float y = input.getMouseY();
			createNewDott(x, y);
		}
		if (input.isKeyPressed(Input.KEY_P)){
			
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
				
				Obstacle newObstacle = new Obstacle(createNewPolygon(points));
				obstacles.add(newObstacle);
				removeDotts();
			}
		}
		
		if (input.isKeyPressed(Input.KEY_DELETE)){ // Delete selected dotts or polygons
			
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
	}

	private boolean noDottsSelected() {
		// TODO Auto-generated method stub
		boolean noDottsSelected = true;
		for (Dott dott : dotts){
			if (dott.isSelected()) return false;
		}		
		return noDottsSelected;
	}
	
	private boolean noObstaclesSelected(){
		boolean noObstaclesSelected = true;
		for (Obstacle obstacle : obstacles){
			if (obstacle.isSelected()) return false;
		}		
		return noObstaclesSelected;
	}
	
	
}
