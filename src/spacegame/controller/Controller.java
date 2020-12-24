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
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;
import spacegame.gameobjects.enemies.Enemy1;
import spacegame.gameobjects.enemies.Enemy2;

public class Controller {

  public static LinkedList<Entity> entities = new LinkedList<Entity>();
  public static int time;
  public static Rectangle maxEntityBounds = new Rectangle(-200, -200, Game.WIDTH + 400, Game.HEIGHT + 400);
  private Entity entity;

  private Game game;

  private Textures tex;
  public int levelCounter = 0;
  public boolean running;
  private Player p;

  public Controller(Game game, Textures tex, Player p) {
    this.game = game;
    this.tex = tex;
    // loadLevel(1);
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
      default: {
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
      }
    }

  }

  public void restartlevel() {
    System.out.println("Restarting Level");
    running = false;
    Game.state = STATE.Score;
    time = 0;
    levelCounter = 0;
    entities.clear();
  }

  public void updateTime() {
    time++;
    if ((time + 1) % 5 == 0) {
      addEntity(new AmmunitionDrop(640, Math.random() * (Game.HEIGHT - 30), 1, tex));
    }
  }

  public void tick() {

    boolean allEnemiesGone = true;
    for (Entity entity : entities) {
      if (entity instanceof Enemy) {
        allEnemiesGone = false;
      }
    }
    if (allEnemiesGone) {
      loadLevel(1 + levelCounter++);
    }

    for (int i = 0; i < entities.size(); i++) {
      if (entity != null) {
        if (!maxEntityBounds.contains(new Point((int) entity.getX(), (int) entity.getY()))) {
          removeEntity(entity);
        } else {
          entity = entities.get(i);
          entity.tick();
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
      entity = entities.get(i);
      entity.render(g);
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
