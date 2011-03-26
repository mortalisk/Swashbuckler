package no.mamot.swashbuckler.editor;

public interface LevelEditorState {

	// State spesific actions
	
	public void place(float x , float y);
	public void placeFinished();
	public void select(float x, float y);
	public void delete();
	public void move(float x , float y);	
	
}
