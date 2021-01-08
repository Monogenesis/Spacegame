package spacegame.animation;

import java.awt.image.BufferedImage;

import spacegame.Game;

public class Textures {

	private static int TILESIZE = 32;
	public BufferedImage health3, health2, health1;

	public BufferedImage[] playerLookRight = new BufferedImage[3];
	public BufferedImage[] playerLookLeft = new BufferedImage[3];
	public BufferedImage[] playerTurnLeft = new BufferedImage[6];
	public BufferedImage[] playerTurnRight = new BufferedImage[6];
	public BufferedImage[] enemy1 = new BufferedImage[3];
	public BufferedImage[] enemy1Destroy = new BufferedImage[8];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] ammunition = new BufferedImage[7];
	public BufferedImage[] enemy2 = new BufferedImage[7];
	public BufferedImage[] enemy2Destroy = new BufferedImage[12];
	public BufferedImage[] enemy2Projectile = new BufferedImage[7];
	public BufferedImage[] enemy3 = new BufferedImage[9];

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
		playerLookRight = loadImages(1, 3);
		playerLookLeft = loadImages(13, 3);
		playerTurnLeft = loadImages(12, 6);
		playerTurnRight = loadImages(14, 6);

		bullet = loadImages(2, 3);

		ammunition = loadImages(7, 7);
		enemy1 = loadImages(3, 3);
		enemy1Destroy = loadImages(8, 8);
		enemy2 = loadImages(9, 7);
		enemy2Destroy = loadImages(10, 12);
		enemy2Projectile = loadImages(11, 7);
		enemy3 = loadImages(15, 9);

		health3 = ss.grabImage(4, 1, TILESIZE, TILESIZE);
		health2 = ss.grabImage(5, 1, TILESIZE, TILESIZE);
		health1 = ss.grabImage(6, 1, TILESIZE, TILESIZE);
	}

}
