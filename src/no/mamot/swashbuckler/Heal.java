package no.mamot.swashbuckler;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.Graphics;

public class Heal extends GameEntity {

	
	public Heal(){
		Box shape = new Box(30, 40);
		body = new StaticBody(shape);
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public ROVector2f getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

}
