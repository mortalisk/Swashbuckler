package no.mamot.swashbuckler.editor;

import org.newdawn.slick.SlickException;

public class ParticleCreator {

	private LevelImplEditor level;

	public ParticleCreator(LevelImplEditor level) {
		this.level = level;
	}

	public void createNewParticle(float x, float y) {
		ParticleObject newParticleObject = new ParticleObject(x, y);
		try {
			newParticleObject.load();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findCurrentSelectedObstacle().getParticles().add(newParticleObject);
	}

	private Obstacle findCurrentSelectedObstacle() {

		for (Obstacle obstacle : level.getObstacleList()) {
			if (obstacle.isSelected()) {
				return obstacle;
			}
		}
		return null;
	}
}
