package no.mamot.swashbuckler.editor;


import org.newdawn.slick.SlickException;

import no.mamot.swashbuckler.GameEntity;
import no.mamot.swashbuckler.Robat;
import no.mamot.swashbuckler.Robot;
import no.mamot.swashbuckler.Swashbuckler;
import no.mamot.swashbuckler.Tourmaline;

public enum TypeEnum {
	SWASHBUCKLER(Swashbuckler.class), TOURMALINE(Tourmaline.class), ROBOT(Robot.class), ROBAT(Robat.class);

	Class<? extends GameEntity> c;
	TypeEnum(Class<? extends GameEntity> c) {
		this.c = c;
	}
	
	GameEntity getInstance() throws SlickException{
		try {
			GameEntity ge = c.newInstance();
			return ge;
		} catch (InstantiationException e) {
			throw new SlickException("instatiation exception",e);
		} catch (IllegalAccessException e) {
			throw new SlickException("illegal access exception",e);
		}
		
	}
}