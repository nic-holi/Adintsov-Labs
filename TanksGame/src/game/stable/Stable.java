package game.stable;

import game.main.Game;
import game.sprite.Sprite;
//import game.sprite.Sprite;


public abstract class Stable extends Sprite {
	public static String imageFile;
	
	public Stable() {
		setImageFile();
		setImage(imageFile);
		setBITMASK(Game.STABLE_MASK);
	}
	
	public abstract void setImageFile();

	public abstract void dealWithCollision(Sprite s);
}