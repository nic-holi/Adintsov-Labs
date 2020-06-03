package by.bsiur.game.stable;

import by.bsiur.game.main.Game;
import by.bsiur.game.sprite.Sprite;
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