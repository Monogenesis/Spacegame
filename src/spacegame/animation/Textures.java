package spacegame.animation;

import java.awt.image.BufferedImage;

import spacegame.Game;

public class Textures {

	private static int TILESIZE = 32;
	public BufferedImage health3, health2, health1;

	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] enemyDestroy = new BufferedImage[8];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] ammunition = new BufferedImage[7];

	private SpriteSheet ss = null;

	public Textures(Game game) {

		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private BufferedImage[] loadImages(int col, int frameCount) {
		BufferedImage[] result = new BufferedImage[frameCount];
		for (int i = 0; i < frameCount; i++) {
			result[i] = ss.grabImage(col, i + 1, TILESIZE, TILESIZE);
		}
		return result;
	}

	private void getTextures() {
		player = loadImages(1, 3);
		bullet = loadImages(2, 3);

		enemy = loadImages(3, 3);
		enemyDestroy = loadImages(8, 8);
		ammunition = loadImages(7, 7);

		health3 = ss.grabImage(4, 1, TILESIZE, TILESIZE);
		health2 = ss.grabImage(5, 1, TILESIZE, TILESIZE);
		health1 = ss.grabImage(6, 1, TILESIZE, TILESIZE);
	}

}
