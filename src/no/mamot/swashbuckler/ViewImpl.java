package no.mamot.swashbuckler;

import org.newdawn.slick.Graphics;

public class ViewImpl implements View {

	private Camera camera;
	private Level level;

	public ViewImpl(int x, int y) {
		this.camera = new Camera(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.translate(-camera.getTopLeftCorner().x, -camera.getTopLeftCorner().y);
		for (GameObject gameObject : level.getGameObjectList()) {
			gameObject.draw();
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public Camera getCamera() {
		return camera;
	}

}
