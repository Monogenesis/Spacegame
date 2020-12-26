
package spacegame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import spacegame.animation.BufferedImageLoader;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.controller.KeyInput;
import spacegame.controller.MouseInput;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;
import spacegame.screens.HelpMenu;
import spacegame.screens.MainMenu;
import spacegame.screens.MenuButton;
import spacegame.screens.ScoreMenu;

public class Game extends Canvas implements Runnable {
	public static JFrame frame;
	public static boolean drawHitboxes = false;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 1;
	public static final String TITLE = "Space Game";
	private double backgroundPos = 0;
	private Boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage menuBackground = null;
	private BufferedImage helpMenuBackground = null;
	// private BufferedImage player;

	private Player p;
	private boolean right, left, up, down, isShooting;
	private Controller c;
	private Textures tex;

	private MainMenu mainMenu;
	private ScoreMenu scoreMenu;
	private HelpMenu helpMenu;

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
			helpMenuBackground = loader.loadImage("/resources/helpMenuBackground.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener((new MouseInput(this)));
		tex = new Textures(this);
		p = new Player(200, 200, tex);
		c = new Controller(this, tex, p);
		p.setController(c);
		mainMenu = new MainMenu("SPACE PIRATES", c);
		scoreMenu = new ScoreMenu("SCORE", c);
		helpMenu = new HelpMenu("HELP", c, helpMenuBackground);

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
				System.out.println(updates + " Ticks, Fps " + frames + ", entities: " + Controller.entities.size());
				updates = 0;
				frames = 0;
				if (state == STATE.Game)
					c.updateTime();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (right == true)
				p.moveRight();
			if (left == true)
				p.moveLeft();
			if (down == true)
				p.moveDown();
			if (up == true)
				p.moveUp();

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
			mainMenu.tick();
		} else if (state == STATE.Score) {
			scoreMenu.tick();
		} else if (state == STATE.Help) {
			helpMenu.tick();
		}
	}

	private void render() {
		backgroundPos += 0.2;
		if ((int) backgroundPos == getWidth()) {
			backgroundPos = 0;
		}
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		/////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(menuBackground, (int) (0 - backgroundPos), 0, getWidth(), getHeight(), this);
		g.drawImage(menuBackground, (int) (getWidth() - backgroundPos), 0, getWidth(), getHeight(), this);

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
			mainMenu.render(g);
		} else if (state == STATE.Score) {
			scoreMenu.render(g);
		} else if (state == STATE.Help) {
			helpMenu.render(g);
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

		frame = new JFrame(Game.TITLE);
		frame.add(game);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return this.spriteSheet;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			switch (state) {
				case Menu: {
					if (c.running)
						state = STATE.Game;
					break;
				}
				case Game:
					Game.state = STATE.Menu;
					break;
				case Score: {
					mainMenu.getContinueButton().enabled = c.running ? true : false;
					Game.state = STATE.Menu;
				}
					break;
				case Help: {
					state = STATE.Menu;
				}
				default:
					state = STATE.Menu;
					break;
			}
		} else
			switch (Game.state) {
				case Game: {
					switch (key) {
						case KeyEvent.VK_D: {
							right = true;
							break;
						}
						case KeyEvent.VK_A: {
							left = true;
							break;
						}
						case KeyEvent.VK_S: {
							down = true;
							break;
						}
						case KeyEvent.VK_W: {
							up = true;
							break;
						}
						case KeyEvent.VK_SPACE: {
							if (!isShooting) {
								isShooting = true;
								p.shoot();
							}
							break;
						}
					}
					break;
				}
				case Menu:
					switch (key) {
						case KeyEvent.VK_SPACE: {
							if (!c.running)
								triggerNewGame();
							else
								state = STATE.Game;
							break;
						}
					}
					break;
				case Help:
					break;
				case Score:
					break;
				default:
					break;
			}

	}

	private void triggerNewGame() {
		left = right = up = down = false;
		Game.state = Game.STATE.Game;
		p.init();
		Controller.entities.clear();
		Enemy.enemyCounter = 0;
		Controller.time = 0;
		c.levelCounter = 0;
		Player.score = 0;
		c.running = true;
		mainMenu.getContinueButton().enabled = c.running ? true : false;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (state == STATE.Game) {
			if (key == KeyEvent.VK_D) {
				right = false;
			} else if (key == KeyEvent.VK_A) {
				left = false;
			} else if (key == KeyEvent.VK_S) {
				down = false;
			} else if (key == KeyEvent.VK_W) {
				up = false;
			} else if (key == KeyEvent.VK_SPACE && isShooting) {
				isShooting = false;

			} else if (key == KeyEvent.VK_K) {

			} else if (key == KeyEvent.VK_H) {
				Game.drawHitboxes = !drawHitboxes;
			}

		}

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		Point mousePos = e.getPoint();
		MenuButton menuButton;
		noHit: if (state == STATE.Menu) {
			menuButton = mainMenu.getClickedButton(mousePos);
			if (menuButton == null || !menuButton.enabled) {
				break noHit;
			}
			switch (menuButton.getText()) {
				case MainMenu.continueText: {
					left = right = up = down = false;
					Game.state = Game.STATE.Game;
					break;
				}
				case MainMenu.newGameText: {
					triggerNewGame();
					break;
				}
				case MainMenu.helpText: {
					left = right = up = down = false;
					Game.state = Game.STATE.Help;
					break;
				}
				case MainMenu.quitText: {
					System.exit(1);
					break;
				}
				default:
			}
		} else if (state == STATE.Score) {
			menuButton = scoreMenu.getClickedButton(mousePos);
			if (menuButton == null || !menuButton.enabled) {
				break noHit;
			}
			switch (menuButton.getText()) {
				case ScoreMenu.restartText: {
					triggerNewGame();
					break;
				}
				case ScoreMenu.menuText: {
					mainMenu.getContinueButton().enabled = c.running ? true : false;
					Game.state = STATE.Menu;
					break;
				}
				default:
			}
		} else if (state == STATE.Help) {
			menuButton = helpMenu.getClickedButton(mousePos);
			if (menuButton == null || !menuButton.enabled) {
				break noHit;
			}
			switch (menuButton.getText()) {
				case HelpMenu.menuText: {
					Game.state = STATE.Menu;
					break;
				}
				default:
			}
		} else if (state == STATE.Game) {

		}

	}

}
