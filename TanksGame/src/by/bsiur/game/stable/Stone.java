package by.bsiur.game.stable;

import by.bsiur.game.sprite.Sprite;

public final class Stone extends Stable {
	
	public Stone() {
		super();
	}

	@Override
	public void setImageFile() {
		imageFile = "stone.gif";
	}

	@Override
	public void dealWithCollision(Sprite s) {
	}

	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}