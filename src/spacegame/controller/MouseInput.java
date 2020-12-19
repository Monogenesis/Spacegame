package spacegame.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import spacegame.Game;

public class MouseInput implements MouseListener {
	Game game;

	public MouseInput(Game game) {
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		game.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		game.mouseReleased(e);

	}

}
