package no.mamot.swashbuckler.editor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class ParticleCreator {

	private LevelImplEditor level;
	private List <ParticleObject> particleObjects = new ArrayList<ParticleObject>();
	
	public ParticleCreator(LevelImplEditor level){
		this.level = level;
	}
	
	public void createNewParticle(float x , float y){		
		ParticleObject newParticleObject = new ParticleObject(x, y);
		try {
			newParticleObject.load();			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		findCurrentSelectedObstacle().getParticles().add(newParticleObject);	
	}
	
	
	
	private Obstacle findCurrentSelectedObstacle(){
		
		for (Obstacle obstacle : level.getObstacleList()){
			if (obstacle.isSelected()){
				return obstacle;
			}			
		}	
		return null;
	}
}
