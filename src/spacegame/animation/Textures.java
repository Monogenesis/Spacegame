package spacegame.animation;

import java.awt.image.BufferedImage;

import spacegame.Game;

public class Textures {

	private static int TILESIZE = 32;
	public BufferedImage health3, health2, health1;

	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] ammunition = new BufferedImage[7];

	private SpriteSheet ss = null;

	public Textures(Game game) {

		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private void getTextures() {
		player[0] = ss.grabImage(1, 1, TILESIZE, TILESIZE);
		player[1] = ss.grabImage(1, 2, TILESIZE, TILESIZE);
		player[2] = ss.grabImage(1, 3, TILESIZE, TILESIZE);

		bullet[0] = ss.grabImage(2, 1, TILESIZE, TILESIZE);
		bullet[1] = ss.grabImage(2, 2, TILESIZE, TILESIZE);
		bullet[2] = ss.grabImage(2, 3, TILESIZE, TILESIZE);

		enemy[0] = ss.grabImage(3, 1, TILESIZE, TILESIZE);
		enemy[1] = ss.grabImage(3, 2, TILESIZE, TILESIZE);
		enemy[2] = ss.grabImage(3, 3, TILESIZE, TILESIZE);

		ammunition[0] = ss.grabImage(7, 1, TILESIZE, TILESIZE);
		ammunition[1] = ss.grabImage(7, 2, TILESIZE, TILESIZE);
		ammunition[2] = ss.grabImage(7, 3, TILESIZE, TILESIZE);
		ammunition[3] = ss.grabImage(7, 4, TILESIZE, TILESIZE);
		ammunition[4] = ss.grabImage(7, 5, TILESIZE, TILESIZE);
		ammunition[5] = ss.grabImage(7, 6, TILESIZE, TILESIZE);
		ammunition[6] = ss.grabImage(7, 7, TILESIZE, TILESIZE);

		health3 = ss.grabImage(4, 1, TILESIZE, TILESIZE);
		health2 = ss.grabImage(5, 1, TILESIZE, TILESIZE);
		health1 = ss.grabImage(6, 1, TILESIZE, TILESIZE);
	}

}
