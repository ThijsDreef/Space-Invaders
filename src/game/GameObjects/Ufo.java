package game.GameObjects;

import engine.Engine;
import engine.Image;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class Ufo extends GameObject
{
  Image ufo = new Image("resources/ufo.png");
  boolean left = false;
  int stepcount = 0, existence;
  boolean shot = false;
  public Ufo(int x, int y)
  {
    this.x = x;
    this.y = y;
    w = 32;
    h = 14;
    setTag("ufo");
    addComponent(new Collider("ufo", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (existence > 900)
      y -= Math.round(10 * dt);
    if (y < -30)
      kill();
    if (stepcount >= 140)
    {
      left = !left;
      stepcount = 0;
    }
    if (!left)
    {
      x += Math.round(90 * dt);
      stepcount++;
    }
    if (left)
    {
      x -= Math.round(90 * dt);
      stepcount++;
    }
    existence++;
    if (isDead() && shot) {
      en.getGame().peek().getScoreManager().addscore(100);
      en.getGame().peek().getManager().findObject("player").setState(1);
    }
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawImage(ufo, (int)x, (int)y);
  }

  @Override
  public void componentEvent(String name, GameObject object)
  {
    shot = true;
    kill();
  }

  @Override
  public void dispose() {

  }
}
