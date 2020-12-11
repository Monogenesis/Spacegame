

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



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
	//private BufferedImage player;
	
	private Player p;
	boolean r, l, u, d, isShooting;
	private Controller c;
	private Textures tex;
	
	private Menu menu;
	
	
	public static enum STATE{
		Game,
		Menu,
		Help
	}
	
	public static STATE state = STATE.Menu;
	
	public void init() {
		
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			spriteSheet = loader.loadImage("/SpriteSheet.png");
			menuBackground = loader.loadImage("/menuBackground.png");
		}catch(IOException e) {
			e.printStackTrace();			
		}
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener((new MouseInput()));
		tex = new Textures(this);
		p = new Player( 200, 200, tex);
		c = new Controller(this, tex);
		menu = new Menu(0, 0, tex);
	
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;				 
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(r == true)
				p.setVelX(5);
			if(l == true)
				p.setVelX(-3);
			if(d == true)
				p.setVelY(5);
			if(u == true)
				p.setVelY(-5);
			
			if(r == false && l == false)
				p.setVelX(0);
			if(d == false && u == false)
				p.setVelY(0);
		}
				
		stop();
	}
	
	private void tick() {
		
		if(state == STATE.Game) {
		p.tick();
		c.tick();
		}
		else if(state == STATE.Menu) {
			
			menu.tick();
			
			
		}
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/////////////
		
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);				
					g.drawImage(menuBackground, 0, 0, getWidth(), getHeight(), this);
					
				if(state == STATE.Game) {
					p.render(g);
					c.render(g);				
				}
				else if(state == STATE.Menu){
					
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
	
	 
	    public  BufferedImage getSpriteSheet() {
	    	return this.spriteSheet;
	    }
	    
	  
	    
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(state == STATE.Game) {
				
			
				if(key == KeyEvent.VK_D) {
			//	p.setVelX(5);
					r = true;
				}else if(key == KeyEvent.VK_A) {
			//	p.setVelX(-5); 
					l = true;
				}else if(key == KeyEvent.VK_S) {
			//	p.setVelY(5); 
					d = true;
				}else if(key == KeyEvent.VK_W) {
			//	p.setVelY(-5);
					u = true;
				}else if(key == KeyEvent.VK_SPACE && !isShooting) {
					isShooting = true;
					c.addEntity(new Bullet(p.getX(), p.getY()+13, tex));			
				}
		
			}
			
				if(key == KeyEvent.VK_ESCAPE) 
				state = STATE.Menu;
				
				
		}
		public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
			
		if(state == STATE.Game) {
			if(key == KeyEvent.VK_D) {
			//	p.setVelX(0);
				r = false;
			}else if(key == KeyEvent.VK_A) {
			//	p.setVelX(0);
				l = false;
			}else if(key == KeyEvent.VK_S) {
			//	p.setVelY(0); 
				d = false;		
			}else if(key == KeyEvent.VK_W) {
			//	p.setVelY(0);
				u = false;
			}else if(key == KeyEvent.VK_SPACE && isShooting) {
					isShooting = false;			
				
				
			}
			}	
		}
	/*    
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			
			if(key == KeyEvent.VK_RIGHT) {
				p.setVelX(5); r = true;
			}else if(key == KeyEvent.VK_LEFT) {
				p.setVelX(-5); l = true;
			}else if(key == KeyEvent.VK_DOWN) {
				p.setVelY(5); d = true;
			}else if(key == KeyEvent.VK_UP) {
				p.setVelY(-5); u = true;
			}
				
			
		}
		
		public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_RIGHT) {
				p.setVelX(0); r = false;
			}else if(key == KeyEvent.VK_LEFT) {
				p.setVelX(0); l = false;
			}else if(key == KeyEvent.VK_DOWN) {
				p.setVelY(0); d = false;
			}else if(key == KeyEvent.VK_UP) {
				p.setVelY(0); u = false;
			}
				
			
		}
 
	*/
	

	   
	   
	   
	
			
	   
			  
	   

}
