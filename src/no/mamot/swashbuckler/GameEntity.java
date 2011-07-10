package no.mamot.swashbuckler;

import net.phys2d.raw.Body;
import no.mamot.engine.Drawable;
import no.mamot.engine.GameObject;
import no.mamot.engine.Level;
import no.mamot.engine.Updateable;

public abstract class GameEntity implements Drawable, Updateable, GameObject{

	protected Swashbuckler player = null;

	protected Level playerLevel;
	
	protected Body body;

	public Swashbuckler getPlayer() {
		return player;
	}

	public void setPlayer(Swashbuckler player) {
		this.player = player;
	}

	public Level getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(Level playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	public void setPosition(float x, float y) {
		body.setPosition(x, y);
	}

}
