package no.mamot.swashbuckler;

import org.newdawn.slick.Graphics;

public interface View {

	void draw(Graphics g);

	Camera getCamera();

	void setLevel(Level level);

}
