package game.GameObjects;

import engine.Engine;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class Bullet extends GameObject
{
  int bulletSpeed = 150;
  public Bullet(int startx, int starty)
  {
    setTag("bullet");
    this.x = startx;
    this.y = starty;
    w = 3;
    h = 8;
    addComponent(new Collider("bullet", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    y -= Math.round(bulletSpeed * dt);
    if (y < 0)
      kill();
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xffffffff);
  }

  @Override
  public void componentEvent(String name, GameObject object)
  {
    if (name.equalsIgnoreCase("enemy"))
      kill();
    if (name.equalsIgnoreCase("enemybullet"))
      kill();
  }

  @Override
  public void dispose()
  {

  }
}
