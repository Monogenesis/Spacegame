import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {

  public static LinkedList<Entity> e = new LinkedList<Entity>();

  private ArrayList<Enemy> wave1 = new ArrayList<>();
  private ArrayList<Enemy> wave2 = new ArrayList<>();

  private Entity entity;

  private Game game;

  private Textures tex;
  private int levelCounter = 1;

  public Controller(Game game, Textures tex) {
    this.game = game;
    this.tex = tex;

    loadLevel(1);

  }

  public void loadLevel(int wave) {
    System.out.println("Loading level " + wave);
    switch (wave) {
      case 1: {
        for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 96) {
          addEntity(new Enemy(640, i, tex));
        }
        break;
      }
      case 2: {
        for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 64) {
          addEntity(new Enemy(640, i, tex));
        }
        break;
      }
      default: {
        for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 96) {
          addEntity(new Enemy(640, i, tex));
        }
      }
    }

  }

  public void restartlevel() {
    e.clear();
  }

  public void tick() {
    boolean allEnemiesGone = true;
    for (Entity entity : e) {
      if (entity instanceof Enemy) {
        allEnemiesGone = false;
      }
    }
    if (allEnemiesGone) {
      loadLevel(levelCounter++);
    }

    for (int i = 0; i < e.size(); i++) {
      if (entity != null) {
        if (entity.getX() > 640 || entity.getY() > 480) {
          removeEntity(entity);
        } else {
          entity = e.get(i);
          entity.tick();
        }
      }

    }

    // System.out.println("Bullet is empty " + b.isEmpty() + " " + "Enemy is empty "
    // + e.isEmpty());
  }

  public void render(Graphics g) {

    for (int i = 0; i < e.size(); i++) {

      entity = e.get(i);
      entity.render(g);
    }

  }

  public void addEntity(Entity block) {
    e.add(block);

  }

  public void removeEntity(Entity block) {
    e.remove(block);

  }

}
