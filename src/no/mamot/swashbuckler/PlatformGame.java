package no.mamot.swashbuckler;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class PlatformGame extends BasicGame {

	static AppGameContainer app = null;

	private GameEntity man = null;
	private GameEntity brick = null;
	private List<GameObstacle> obstacleList = null;
	private List<GameObject> objectList = null;

	public PlatformGame(String title) {
		super(title);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		try {
			
			man = new GameEntity ("/data/WWFSoldierUzi.png",40.0f,0.0f,0.0f);
			brick = new GameEntity ("/data/brick.png", 40.0f ,150.0f, 200.0f);
			
			Vector2f pos = new Vector2f(400, 400);
			float [] points1  =  new float [8];
			points1[0] = 380.0f; // x1
			points1[1] = 380.0f; // y1
			points1[2] = 400.0f; // x2
			points1[3] = 400.0f; // etc
			points1[4] = 560.0f;
			points1[5] = 360.0f;
			points1[6] = 560.0f;
			points1[7] = 280.0f;
			
			GameObstacle obstacle1 = new GameObstacle(points1, pos);
			
			
			float [] points2  =  new float [8];
			points2[0] = 560.0f; // x1
			points2[1] = 380.0f; // y1
			points2[2] = 600.0f; // x2
			points2[3] = 390.0f; // etc
			points2[4] = 650.0f;
			points2[5] = 360.0f;
			points2[6] = 700.0f;
			points2[7] = 190.0f;
			
			GameObstacle obstacle2 = new GameObstacle(points2, pos);
			
			float [] points3  =  new float [8];
			points3[0] = 200.0f; // x1
			points3[1] = 590.0f; // y1
			points3[2] = 380.0f; // x2
			points3[3] = 620.0f; // etc
			points3[4] = 420.0f;
			points3[5] = 660.0f;
			points3[6] = 900.0f;
			points3[7] = 690.0f;
			
			GameObstacle obstacle3 = new GameObstacle(points3, pos);
			
			float [] points4  =  new float [8];
			points4[0] = 700.0f; // x1
			points4[1] = 190.0f; // y1
			points4[2] = 780.0f; // x2
			points4[3] = 320.0f; // etc
			points4[4] = 820.0f;
			points4[5] = 360.0f;
			points4[6] = 900.0f;
			points4[7] = 390.0f;
			
			GameObstacle obstacle4 = new GameObstacle(points4, pos);
			objectList = new ArrayList<GameObject>();
			objectList.add(obstacle1);
			objectList.add(obstacle2);
			objectList.add(obstacle3);
			objectList.add(obstacle4);
			objectList.add(brick);
			obstacleList = new ArrayList<GameObstacle> ();
			obstacleList.add(obstacle1);
			obstacleList.add(obstacle2);
			obstacleList.add(obstacle3);
			obstacleList.add(obstacle4);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		man.update(gc, delta,brick, obstacleList, objectList);
		//System.out.println("Delta : "+delta);
		
	}

	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
	
		man.draw();
		brick.draw();
		drawObstacles();
	}

	private void drawObstacles(){
		for (GameObstacle obstacle : obstacleList){
			obstacle.draw();
		}
	}
	
	public static void main(String[] args) {
		try {
			app = new AppGameContainer(new PlatformGame("Title"));
			app.setDisplayMode(1024, 768, false);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
