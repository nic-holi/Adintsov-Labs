package game.sprite;

import game.main.Game;

import java.util.ArrayList;


public class EnemyTank extends Tank {
	
	private long lastChangeDirection = System.nanoTime();
	public static final long DIRECTION_DELAY = 50000000;
	public static final double DIRECTION_CHANGE_POS = 0.02;
	public static final double FIRE_MISSILE_POS = 0.02;
	
	public EnemyTank(ArrayList<Sprite> elements) {
		super(elements);
		SPEED = 100;
	}
	

	public void update(double time) {
		super.update(time);
		attemptChangeDirection();
		fireMissileRandom();
		missileDirection= getDirection();
	}
	
	public int getMissileMask() {
		return Game.ENEMY_MISSILE_MASK;
	}
	
	private void attemptChangeDirection() {
		if (Math.random() < DIRECTION_CHANGE_POS 
				&& System.nanoTime() - lastChangeDirection > DIRECTION_DELAY) {
			changeRandomDirection();
		}
	}
	
	private void changeRandomDirection() {
		int dir = (int)(Math.random() * 4);
		switch (dir) {
		case 0:
			setDirection(Direction.UP);
			break;
		case 1:
			setDirection(Direction.DOWN);
			break;
		case 2:
			setDirection(Direction.LEFT);
			break;
		case 3:
			setDirection(Direction.RIGHT);
			break;
		default:
			break;
		}
		lastChangeDirection = System.nanoTime();
	}
	
	private void fireMissileRandom() {
		if (Math.random() < FIRE_MISSILE_POS) {
			fireMissile();
		}
	}
	
	protected void dealWithCollision(Sprite s) {
		if (s.getBITMASK() == Game.PLAYER_MISSILE_MASK
				|| s.getBITMASK() == Game.PLAYER_TANK_MASK) {
			setHealth(0);
		}
	}

	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}
