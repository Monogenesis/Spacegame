package spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.animation.Textures;
import spacegame.controller.MouseInput;
import spacegame.entities.Entity;

public class Menu implements Entity {

	private double x, y;

	private static int BOUNDWIDTH = 15;
	private static int BOUNDHEIGHT = 5;

	private Font fnt0 = new Font("arial", Font.BOLD, 50);
	private Font fnt1 = new Font("arial", Font.BOLD, 30);

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

	public Menu(double x, double y, Textures tex) {
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SPACE GAME", Game.WIDTH / 2, 100);

		g.setFont(fnt1);

		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);

		g.setColor(Color.lightGray);

		g.drawString("PLAY", playButton.x + 12, playButton.y + 35);
		g.drawString("HELP", helpButton.x + 12, helpButton.y + 35);
		g.drawString("QUIT", quitButton.x + 12, quitButton.y + 35);
	}

	public void tick() {

	}

	public Rectangle getbounds() {
		return new Rectangle((int) x, (int) y, BOUNDWIDTH, BOUNDHEIGHT);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
