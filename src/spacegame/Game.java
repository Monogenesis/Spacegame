
package spacegame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import spacegame.animation.BufferedImageLoader;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.controller.KeyInput;
import spacegame.controller.MouseInput;
import spacegame.entities.Player;
import spacegame.projectiles.Bullet;
import spacegame.screens.Menu;
import spacegame.screens.ScoreScreen;

import java.awt.event.MouseEvent;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final String TITLE = "Space Game";

	private Boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage menuBackground = null;
	// private BufferedImage player;

	private Player p;
	boolean right, left, up, down, isShooting;
	private Controller c;
	private Textures tex;

	private Menu menu;
	private ScoreScreen scoreScreen;

	public static enum STATE {
		Game, Menu, Help, Score
	}

	public static STATE state = STATE.Menu;

	public void init() {

		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			spriteSheet = loader.loadImage("/resources/SpriteSheet.png");
			menuBackground = loader.loadImage("/resources/menuBackground.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener((new MouseInput(this)));
		tex = new Textures(this);
		p = new Player(200, 200, tex);
		c = new Controller(this, tex);
		p.setController(c);
		menu = new Menu(0, 0, tex);
		scoreScreen = new ScoreScreen();
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
				c.updateTime();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.printf("Mousecontroll: %s, right: %s, left: %s, up: %s,
			// down:%s%n", mouseControl, right, left,
			// up, down);
			// if (mouseControl)
			// mouseControl();

			if (right == true)
				p.setVelX(5);
			if (left == true)
				p.setVelX(-3);
			if (down == true)
				p.setVelY(5);
			if (up == true)
				p.setVelY(-5);

			if (right == false && left == false)
				p.setVelX(0);
			if (down == false && up == false)
				p.setVelY(0);
		}

		stop();
	}

	private void tick() {

		if (state == STATE.Game) {
			p.tick();
			c.tick();
		} else if (state == STATE.Menu) {

			menu.tick();

		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		/////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(menuBackground, 0, 0, getWidth(), getHeight(), this);

		if (state == STATE.Game) {
			g.setColor(Color.white);
			g.drawString("score: " + Player.score, 20, 40);
			g.drawString("level: " + c.getLevelCounter(), 20, 15);
			g.drawString("time: " + Controller.time, 70, 15);
			g.drawString("ammo: " + p.getAmmunitionCount(), 20, 60);
		}

		if (state == STATE.Game) {
			p.render(g);
			c.render(g);
		} else if (state == STATE.Menu) {
			menu.render(g);
		} else if (state == STATE.Score) {
			scoreScreen.render(g);
		}
		////////////////

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(Game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return this.spriteSheet;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (state == STATE.Game) {

			if (key == KeyEvent.VK_D) {
				// p.setVelX(5);
				right = true;
			} else if (key == KeyEvent.VK_A) {
				// p.setVelX(-5);
				left = true;
			} else if (key == KeyEvent.VK_S) {
				// p.setVelY(5);
				down = true;
			} else if (key == KeyEvent.VK_W) {
				// p.setVelY(-5);
				up = true;
			} else if (key == KeyEvent.VK_SPACE && !isShooting) {
				isShooting = true;
				p.shoot();
			}

		}

		if (state == STATE.Menu && key == KeyEvent.VK_SPACE) {
			left = right = up = down = false;
			Game.state = Game.STATE.Game;
		}

		if (key == KeyEvent.VK_ESCAPE)
			state = STATE.Menu;

	}

	public void mouseControl() {
		double mx = MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
		double my = MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY();

		// System.out.printf("Mousecontrol: %s, mouseX: %s, mouseY: %s --- %s%n",
		// mouseControl, mx, my, p);
		if (state == STATE.Game) {
			if (mx > p.getX() + Player.BOUNDWIDTH) {
				left = false;
				right = true;
			}
			if (mx < p.getX() + Player.BOUNDWIDTH) {
				right = false;
				left = true;
			}
			if (my > p.getY() + Player.BOUNDHEIGHT) {
				up = false;
				down = true;
			}
			if (mx < p.getY() + Player.BOUNDHEIGHT) {
				down = false;
				up = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (state == STATE.Game) {
			if (key == KeyEvent.VK_D) {
				// p.setVelX(0);
				right = false;
			} else if (key == KeyEvent.VK_A) {
				// p.setVelX(0);
				left = false;
			} else if (key == KeyEvent.VK_S) {
				// p.setVelY(0);
				down = false;
			} else if (key == KeyEvent.VK_W) {
				// p.setVelY(0);
				up = false;
			} else if (key == KeyEvent.VK_SPACE && isShooting) {
				isShooting = false;

			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

		if (state == STATE.Menu) {
			if (menu.playButton.contains(e.getPoint())) {
				// pressed playbutton
				left = right = up = down = false;
				Game.state = Game.STATE.Game;
				Controller.time = 0;
			} else if (menu.helpButton.contains(e.getPoint())) {
				// pressed help button
				left = right = up = down = false;
				Game.state = Game.STATE.Help;
			} else if (menu.quitButton.contains(e.getPoint())) {
				System.exit(1);
			}
		} else if (state == STATE.Score) {
			if (scoreScreen.restartButton.contains(e.getPoint())) {
				// pressed playbutton
				left = right = up = down = false;
				Game.state = Game.STATE.Game;
				Controller.time = 0;
				Player.score = 0;
			}
		}

	}

}
