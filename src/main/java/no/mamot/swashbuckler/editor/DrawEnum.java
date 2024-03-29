package no.mamot.swashbuckler.editor;

public enum DrawEnum {
	DRAW_SWASHBUCKLER, DRAW_TOURMALINE, DRAW_ROBOT, DRAW_RHINOBOT, DRAW_ROBAT, DRAW_POLYGON, DRAW_FIREPARTICLE, DRAW_HEALPARTICLE, DRAW_SPACESHIP ;

	@Override
	public String toString() {
		// only capitalize the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}