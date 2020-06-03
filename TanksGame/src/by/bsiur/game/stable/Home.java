package by.bsiur.game.stable;

import by.bsiur.game.sprite.Sprite;
import by.bsiur.game.sprite.Missile;



public class Home extends Stable {
	
	public Home() {
		super();
	}

	public void dealWithCollision(Sprite s) {
		if (s instanceof Missile) {
			setHealth(0);
			imageFile = "nohome.gif";
			setImage(imageFile);
		}
	}

	@Override
	public void setImageFile() {
		imageFile = "home.gif";
	}



	@Override
	public int compareTo(Sprite o) {
		return 0;
	}
}
