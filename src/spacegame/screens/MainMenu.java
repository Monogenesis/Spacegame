package spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.plaf.ColorUIResource;

import spacegame.controller.Controller;

public class MainMenu extends Menu {

	public static Color labelColor = new ColorUIResource(253, 250, 114);
	public final static Font menuLabelFont = new Font("arial", Font.BOLD, 50);
	public final static Font buttonFont = new Font("arial", Font.BOLD, 30);
	public final static Color enabledColor = Color.WHITE;
	public final static Color disabledColor = Color.GRAY;

	public final static String continueText = "CONTINUE";
	public final static String newGameText = "NEW GAME";
	public final static String highscoresText = "HIGHSCORES";
	public final static String helpText = "HELP";
	public final static String quitText = "QUIT";

	private MenuButton continueButton;
	private MenuButton newGameButton;
	private MenuButton highscoresButton;
	private MenuButton helpButton;
	private MenuButton quitButton;

	public MainMenu(String label, Controller controller) {
		super(label, controller, menuLabelFont, labelColor);
		;
		menuButtons.add(continueButton = new MenuButton(0, 150, continueText, false));
		menuButtons.add(newGameButton = new MenuButton(0, 210, newGameText, true));
		menuButtons.add(highscoresButton = new MenuButton(0, 270, highscoresText, true));
		menuButtons.add(helpButton = new MenuButton(0, 330, helpText, true));
		menuButtons.add(quitButton = new MenuButton(0, 390, quitText, true));

	}

	public void render(Graphics g) {
		super.render(g);

	}

	public void tick() {
		super.tick();
	}

	public MenuButton getHighscoreButton() {
		return this.highscoresButton;
	}

	public MenuButton getContinueButton() {
		return this.continueButton;
	}

	public MenuButton getNewGameButton() {
		return this.newGameButton;
	}

	public MenuButton getHelpButton() {
		return this.helpButton;
	}

	public MenuButton getQuitButton() {
		return this.quitButton;
	}

}
