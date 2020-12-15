package spacegame.controller;

import java.awt.Graphics;
import java.util.LinkedList;

import spacegame.Game;
import spacegame.Game.STATE;
import spacegame.animation.Textures;
import spacegame.entities.AmmunitionDrop;
import spacegame.entities.Enemy;
import spacegame.entities.GameObject;
import spacegame.entities.Entity;

public class Controller {

  public static LinkedList<Entity> e = new LinkedList<Entity>();
  public static int time;
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
          // addEntity(new Enemy(640, i, tex));
          addEntity(new AmmunitionDrop(640, i, 1, tex));
        }
        break;
      }
      case 2: {
        for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 64) {
          Enemy enemy = new Enemy(640, i, 3, tex);
          addEntity(enemy);
        }
        break;
      }
      default: {
        for (int i = 0; i < (Game.HEIGHT * Game.SCALE); i += 96) {
          addEntity(new Enemy(640, i, 4, tex));
        }
      }
    }

  }

  public void restartlevel() {
    Game.state = STATE.Score;
    time = 0;
    levelCounter = 0;
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

  public int getLevelCounter() {
    return this.levelCounter;
  }

}
