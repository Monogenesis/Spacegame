import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

  public static LinkedList<Entity> e = new LinkedList<Entity>();

  private Entity entity;

  private Game game;

  private Textures tex;

  public Controller(Game game, Textures tex) {
    this.game = game;
    this.tex = tex;

    restartlevel();
  }

  public void restartlevel() {
    e.clear();
    for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 64) {

      addEntity(new Enemy(640, i, tex));

    }
  }

  public void tick() {

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
