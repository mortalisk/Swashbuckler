package no.mamot.swashbuckler.editor;

import no.mamot.engine.Drawable;
import no.mamot.engine.Updateable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

public class ParticleObject implements Drawable, Updateable {

	private ParticleSystem system;
	private int x, y;
	private int damage = 10;
	private int radius = 30;

	public ParticleObject(float x, float y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public void load() throws SlickException {

		Image image = new Image("data/particles/Fire.png", false);
		system = new ParticleSystem(image);
		system.setPosition(x, y);
		// x y might just be relative to the position of the system....
		system.addEmitter(new FireEmitter(0, 0, 10.0f));
		system.setBlendingMode(ParticleSystem.BLEND_COMBINE);

	}

	@Override
	public void draw(Graphics g) {
		system.render();
	}

	@Override
	public void update(int delta) {
		system.update(delta);
	}

	public ParticleSystem getParticleSystem() {
		return system;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
