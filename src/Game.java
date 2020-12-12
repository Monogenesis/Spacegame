
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final String TITLE = "Space Game";

	private Boolean running = false;
	private Boolean mouseControl = false;
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

	public static enum STATE {
		Game, Menu, Help
	}

	public static STATE state = STATE.Menu;

	public void init() {

		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			spriteSheet = loader.loadImage("/SpriteSheet.png");
			menuBackground = loader.loadImage("/menuBackground.png");
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
			p.render(g);
			c.render(g);
		} else if (state == STATE.Menu) {

			menu.render(g);

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

		JFrame frame = new JFrame(game.TITLE);
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
				c.addEntity(new Bullet(p.getX(), p.getY() + 13, tex));
			}

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
		mouseControl = true;

	}

	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (state == STATE.Game) {
			mouseControl = false;
			left = false;
			down = false;
			up = false;
			right = false;
		}

		if (state == STATE.Menu) {
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 150 && my <= 200) {
				// pressed playbutton
				Game.state = Game.STATE.Game;

			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 250 && my <= 300) {
				// pressed help button
				Game.state = Game.STATE.Help;

			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220 && my >= 350 && my <= 400) {
				System.exit(1);

			}
		}

	}

}
