package by.bsiur.game.stable;

import by.bsiur.game.sprite.Sprite;


public class Water extends Stable {
	
	public Water() {
		super();
	}

	@Override
	public void setImageFile() {
		imageFile = "water.gif";
	}

	@Override
	public void dealWithCollision(Sprite s) {
	}

	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}
