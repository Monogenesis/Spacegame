package spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import spacegame.Game;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.entities.Entity;

public class Menu implements Entity {

	private double x, y;
	private int labelTextPos;
	private String labelText = "SPACE GAME";
	private static int BOUNDWIDTH = 15;
	private static int BOUNDHEIGHT = 5;

	private Font menuLabelFont = new Font("arial", Font.BOLD, 50);
	public final static Font buttonFont = new Font("arial", Font.BOLD, 30);
	public final static Color enabledColor = Color.WHITE;
	public final static Color disabledColor = Color.GRAY;

	public MenuButton continueButton;
	public MenuButton playButton;
	public MenuButton helpButton;
	public MenuButton quitButton;
	private Controller controller;

	public Menu(double x, double y, Controller controller) {
		this.x = x;
		this.y = y;
		this.controller = controller;
		continueButton = new MenuButton(0, 150, "CONTINUE", false);
		playButton = new MenuButton(0, 210, "NEW GAME", true);
		helpButton = new MenuButton(0, 270, "HELP", true);
		quitButton = new MenuButton(0, 330, "QUIT", true);

		labelTextPos = (int) ((Game.WIDTH / 2) - (getTextBounds(menuLabelFont, labelText).getBounds().getWidth() / 2));
	}

	public static Rectangle2D getTextBounds(Font font, String text) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		return font.getStringBounds(text, frc);
	}

	public void render(Graphics g) {
		g.setFont(menuLabelFont);
		g.setColor(Color.WHITE);
		g.drawString(labelText, labelTextPos, 100);

		continueButton.render(g);
		playButton.render(g);
		helpButton.render(g);
		quitButton.render(g);
	}

	public void tick() {
		continueButton.enabled = controller.running ? true : false;
	}

	public Rectangle getBounds() {
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
