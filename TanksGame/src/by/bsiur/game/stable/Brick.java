package by.bsiur.game.stable;

import by.bsiur.game.main.Game;
import by.bsiur.game.sprite.Sprite;


public final class Brick extends Stable {
	
	public Brick() {
		super();
	}

	public void dealWithCollision(Sprite s) {
		switch (s.getBITMASK()) {
		case Game.PLAYER_MISSILE_MASK:
		case Game.ENEMY_MISSILE_MASK:
			setHealth(0);
			break;
		default:
			break;
		}
	}

	@Override
	public void setImageFile() {
		imageFile = "brick.gif";
	}

	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}
