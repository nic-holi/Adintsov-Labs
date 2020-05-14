package game.stable;

import game.sprite.Sprite;


public class Grass extends Stable {
	
	public Grass() {
		super();
	}

	@Override
	public void setImageFile() {
		imageFile = "grass.gif";
	}

	@Override
	public void dealWithCollision(Sprite s) {
	}

	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}
