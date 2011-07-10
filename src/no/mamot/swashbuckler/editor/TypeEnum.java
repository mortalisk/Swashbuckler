package no.mamot.swashbuckler.editor;

public enum TypeEnum {
	SWASHBUCKLER, TOURMALINE, ROBOT, ROBAT;

	@Override
	public String toString() {
		// only capitalize the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}