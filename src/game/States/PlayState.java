package game.States;

import engine.Engine;
import engine.Fx.Light;
import engine.Renderer;
import game.GameObjects.Player;
import game.managers.EnemyManager;
import game.managers.GameObject;
import game.managers.State;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayState extends State
{
  EnemyManager enemyManager = new EnemyManager(manager);
  Light enemyLightRed, enemyLightBlue, enemyLightGreen, playerLight, bulletLight, ufoLight;
  public PlayState()
  {
    manager.addObject(new Player(5, 210));
    enemyLightRed = new Light(0xffff0000, 17);
    enemyLightBlue = new Light(0xff00ff00, 17);
    enemyLightGreen = new Light(0xff0000ff, 17);

    playerLight = new Light(0xfffffff, 65);
    bulletLight = new Light(0xffaabbcc, 35);
    ufoLight = new Light(0xffffff00, 35);
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (en.getInput().isKeyReleased(KeyEvent.VK_ESCAPE))
      en.getGame().changeToState(new PauseState(this));
    enemyManager.update(en, dt);
    manager.updateObjects(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    manager.renderObjects(en, r);
    scoreManager.render(en, r);
    drawlights(en, r);

  }

  @Override
  public void dispose() {
    manager.disposeObjects();
  }

  public void drawlights(Engine en, Renderer r)
  {
    for (int i = 0; i < 9; i++)
    {
      ArrayList<GameObject> list = manager.findGameObjects("row" + i);
      for (int j = 0; j < list.size(); j++)
      {
        int colourChooser = list.get(j).getState();
        if (colourChooser == 0)
          r.drawStaticLight(enemyLightRed, (int) list.get(j).getX() + (int) list.get(j).getW() / 2, (int) list.get(j).getY() + (int) list.get(j).getH() / 2);
        if (colourChooser == 1)
          r.drawStaticLight(enemyLightBlue, (int) list.get(j).getX() + (int) list.get(j).getW() / 2, (int) list.get(j).getY() + (int) list.get(j).getH() / 2);
        if (colourChooser == 2)
          r.drawStaticLight(enemyLightGreen, (int) list.get(j).getX() + (int) list.get(j).getW() / 2, (int) list.get(j).getY() + (int) list.get(j).getH() / 2);
      }
    }
      GameObject player = manager.findObject("player");
      r.drawStaticLight(playerLight, (int)player.getX() + (int)player.getW() / 2, (int)player.getY() + (int)player.getH() / 2);
      GameObject bullet = manager.findObject("bullet");
      if (bullet != null)
        r.drawStaticLight(bulletLight, (int)bullet.getX() + (int)bullet.getW() / 2, (int)bullet.getY() + (int)bullet.getH() / 2);
      ArrayList<GameObject> enemyBullet = manager.findGameObjects("enemybullet");
      for (int i = 0; i < enemyBullet.size(); i++)
        r.drawStaticLight(bulletLight, (int)enemyBullet.get(i).getX()+ (int)enemyBullet.get(i).getW() / 2, (int) enemyBullet.get(i).getY() + (int)enemyBullet.get(i).getH() / 2);
      GameObject ufo = manager.findObject("ufo");
      if (ufo != null)
        r.drawStaticLight(ufoLight, (int)ufo.getX() + (int)ufo.getW() / 2, (int)ufo.getY() + (int)ufo.getH() / 2);
  }
}
