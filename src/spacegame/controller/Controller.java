package spacegame.controller;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import spacegame.Game;
import spacegame.Game.STATE;
import spacegame.animation.Textures;
import spacegame.gameobjects.AmmunitionDrop;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.GameObject;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;
import spacegame.gameobjects.enemies.Enemy1;
import spacegame.gameobjects.enemies.Enemy2;
import spacegame.gameobjects.enemies.Enemy3;

public class Controller {

  public static LinkedList<Entity> entities = new LinkedList<Entity>();
  public static int time;
  public static Rectangle maxEntityBounds = new Rectangle(-200, -200, Game.WIDTH + 400, Game.HEIGHT + 400);
  static boolean allEnemiesGone = true;
  private Game game;

  private Textures tex;
  public int levelCounter = 0;
  public boolean running;
  private Player p;

  public Controller(Game game, Textures tex, Player p) {
    this.game = game;
    this.tex = tex;
    // loadLevel(3);
    this.p = p;
  }

  public void loadLevel(int wave) {
    System.out.println("Loading level " + wave);

    switch (wave) {
      case 1: {
        addEntity(new Enemy1(640, 15, 1, tex));
        addEntity(new Enemy1(720, 45, 1, tex));
        addEntity(new Enemy1(680, 85, 1, tex));
        addEntity(new Enemy1(640, 155, 1, tex));
        addEntity(new Enemy1(720, 185, 1, tex));
        addEntity(new Enemy1(680, 225, 1, tex));
        addEntity(new Enemy1(640, 295, 1, tex));
        addEntity(new Enemy1(720, 325, 1, tex));
        addEntity(new Enemy1(680, 365, 1, tex));
        addEntity(new Enemy1(640, 435, 1, tex));
        break;
      }
      case 2: {
        addEntity(new Enemy2(640, 15, 1, tex));
        addEntity(new Enemy2(720, 45, 1, tex));
        addEntity(new Enemy2(680, 85, 1, tex));
        addEntity(new Enemy2(640, 155, 1, tex));
        addEntity(new Enemy2(720, 185, 1, tex));
        addEntity(new Enemy2(680, 225, 1, tex));
        addEntity(new Enemy2(640, 295, 1, tex));
        addEntity(new Enemy2(720, 325, 1, tex));
        addEntity(new Enemy2(680, 365, 1, tex));
        addEntity(new Enemy2(640, 435, 1, tex));
        break;

      }
      case 3: {
        addEntity(new Enemy3(640, 15, 3, tex));
        addEntity(new Enemy3(720, 45, 3, tex));
        addEntity(new Enemy3(680, 85, 3, tex));
        addEntity(new Enemy3(640, 155, 3, tex));
        addEntity(new Enemy3(720, 185, 3, tex));
        addEntity(new Enemy3(680, 225, 3, tex));
        addEntity(new Enemy3(640, 295, 3, tex));
        addEntity(new Enemy3(720, 325, 3, tex));
        addEntity(new Enemy3(680, 365, 3, tex));
        addEntity(new Enemy3(640, 435, 3, tex));

        break;
      }
      default: {
        addEntity(new Enemy3(640, 15, 1, tex));
        addEntity(new Enemy3(720, 45, 1, tex));
        addEntity(new Enemy3(680, 85, 1, tex));
        addEntity(new Enemy3(640, 155, 1, tex));
        addEntity(new Enemy3(720, 185, 1, tex));
        addEntity(new Enemy3(680, 225, 1, tex));
        addEntity(new Enemy3(640, 295, 1, tex));
        addEntity(new Enemy3(720, 325, 1, tex));
        addEntity(new Enemy3(680, 365, 1, tex));
        addEntity(new Enemy3(640, 435, 1, tex));
      }
    }

  }

  public void restartlevel() {
    System.out.println("Restarting Level");
    for (Entity entity : entities) {
      if (entity instanceof GameObject) {
        GameObject o = (GameObject) entity;
        o.destroySelf(null);
      }
    }
    running = false;
    Game.setState(STATE.Score);
    time = 0;
    levelCounter = 0;

  }

  public void updateTime() {
    time++;
    if ((time + 1) % 5 == 0) {
      addEntity(new AmmunitionDrop(640, Math.random() * (Game.HEIGHT - 30), 1, tex));
    }
  }

  public void tick() {
    if (Enemy.enemyCounter == 0) {
      loadLevel(1 + levelCounter++);
    }
    for (int i = 0; i < entities.size(); i++) {
      if (entities.get(i) != null) {
        if (!maxEntityBounds.contains(new Point((int) entities.get(i).getX(), (int) entities.get(i).getY()))) {
          if (entities.get(i) instanceof Enemy) {
            Enemy.decrementEnemyCounter();
          }
          removeEntity(entities.get(i));

        } else {
          entities.get(i).tick();
        }
      }

    }

    // System.out.println("Bullet is empty " + b.isEmpty() + " " + "Enemy is empty "
    // + e.isEmpty());
  }

  public Player getPlayer() {
    return p;
  }

  public void render(Graphics g) {
    for (int i = 0; i < entities.size(); i++) {
      if (entities.get(i) != null) {
        entities.get(i).render(g);
      }
    }
  }

  public void addEntity(Entity block) {
    entities.add(block);

  }

  public void removeEntity(Entity block) {
    entities.remove(block);

  }

  public int getLevelCounter() {
    return this.levelCounter;
  }

}
