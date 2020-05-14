package game.map;

import game.sprite.Direction;
import game.sprite.EnemyTank;
import game.sprite.PlayerTank;
import game.sprite.Sprite;



import game.stable.*;

import java.util.ArrayList;


public class GameMap {
	public static final double SPAWN_POS = 0.0025;
	public static final int unitSize = 20;

	private int width;
	private int height;
	private ArrayList<Sprite> elements;
	private PlayerTank playerTank;
	private MapData data;
	private Map currentMap;

	public int getHeight() { return height;	}

	public int getWidth() { return width;	}

	public MapData getData() { return data;	}

	public ArrayList<Sprite> getElements() { return elements;}

	public void setHeight(int height) { this.height = height;}

	public void setWidth(int width) { this.width = width; }

	public void setData(MapData data) { this.data = data;}

	public void setElements(ArrayList<Sprite> elements) { this.elements = elements;	}

	public GameMap(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public void init(ArrayList<Sprite> elements) {
		this.setElements(elements);
		data = new MapData();
	}

	public void buildMap(int level) {
		pad();
		createMap(level);
	}
	

	public void spawnTank() {
		for (int[] pos: currentMap.tankPos) {
			if (Math.random() < SPAWN_POS) {
				EnemyTank tank = new EnemyTank(elements);
				tank.setPosition(pos[0], pos[1]);
				boolean valid = true;
				for (Sprite e: elements) {
					if (tank.intersects(e)) {
						valid = false;
					}
				}
				if (valid) {
					tank.setDirection(Direction.DOWN);
					elements.add(tank);
				}
			}
		}
	}

	public PlayerTank getPlayerTank() {
		return playerTank;
	}

	public PlayerTank revivePlayerTank() {
		playerTank = new PlayerTank(elements);
		playerTank.setPosition(currentMap.playerPos[0], currentMap.playerPos[1]);
		elements.add(playerTank);
		return playerTank;
	}

	private void pad() {
		double x = 0, y = 0;
		x = -unitSize;
		for (y = 0; y <= height; y += unitSize) {
			addPadding(x, y);
		}
		y = height;
		for (x = 0; x <= width; x += unitSize) {
			addPadding(x, y);
		}
		x = width;
		for (y = 0; y <= height; y+= unitSize) {
			addPadding(x, y);
		}
		y = -unitSize;
		for (x = 0; x <= width; x += unitSize) {
			addPadding(x, y);
		}
	}

	private void addPadding(double x, double y) {
		Stone padding = new Stone();
		padding.setPosition(x, y);
		elements.add(padding);
	}

	private void createMap(int level) {
		currentMap = data.mapWithLevel(level);
		addStable(currentMap.brickPos, Brick.class);
		addStable(currentMap.stonePos, Stone.class);
		addStable(currentMap.grassPos, Grass.class);
		addStable(currentMap.waterPos, Water.class);
		addHome();
		playerTank = revivePlayerTank();
	}
	
	public int numLevels() {
		return data.numLevels();
	}

	private void addStable(int[][] pos, Class<? extends Stable> cls) {
		for (int[] chunk: pos) {
			int x = chunk[0];
			int y = chunk[1];
			int chunkWidth = chunk[2];
			int chunkHeight = chunk[3];
			for (int p = x; p <= x + chunkWidth - unitSize; p += unitSize) {
				for (int q = y; q <= y + chunkHeight - unitSize; q += unitSize) {
					Stable e = null;
					try {
						e = (Stable) cls.newInstance();
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					e.setPosition(p, q);
					elements.add(e);
				}
			}
		}
	}

	private void addHome() {
		Home home = new Home();
		home.setPosition(currentMap.homePos[0], currentMap.homePos[1]);
		elements.add(home);
	}
}