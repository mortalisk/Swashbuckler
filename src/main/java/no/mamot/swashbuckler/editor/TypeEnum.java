package no.mamot.swashbuckler.editor;

import no.mamot.swashbuckler.Fire;
import no.mamot.swashbuckler.GameEntity;
import no.mamot.swashbuckler.Heal;
import no.mamot.swashbuckler.Rhinobot;
import no.mamot.swashbuckler.Robat;
import no.mamot.swashbuckler.Robot;
import no.mamot.swashbuckler.Spaceship;
import no.mamot.swashbuckler.Swashbuckler;
import no.mamot.swashbuckler.Tourmaline;

import org.newdawn.slick.SlickException;

public enum TypeEnum {
	SWASHBUCKLER(Swashbuckler.class), TOURMALINE(Tourmaline.class), ROBOT(
			Robot.class), RHINOBOT(Rhinobot.class), ROBAT(Robat.class), FIRE(Fire.class), HEAL(Heal.class), SPACESHIP(Spaceship.class);

	Class<? extends GameEntity> c;

	TypeEnum(Class<? extends GameEntity> c) {
		this.c = c;
	}

	GameEntity getInstance() throws SlickException {
		try {
			GameEntity ge = c.newInstance();
			return ge;
		} catch (InstantiationException e) {
			throw new SlickException("instatiation exception", e);
		} catch (IllegalAccessException e) {
			throw new SlickException("illegal access exception", e);
		}

	}
}