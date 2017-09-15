package game.GameObjects;


import engine.Engine;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class EnemyBullet extends GameObject
{
  int bulletSpeed = 135;
  public EnemyBullet(int x, int y)
  {
    this.x = x;
    this.y = y;
    w = 3;
    h = 8;
    tag = "enemybullet";
    addComponent(new Collider("enemybullet", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    y += Math.round(bulletSpeed * dt);
    updateComponents(en, dt);
    if (y > 240)
      kill();
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, 3, 8, 0xff00ff00);
  }

  @Override
  public void componentEvent(String name, GameObject object)
  {
    if (name.equalsIgnoreCase("player"))
      kill();
    if (name.equalsIgnoreCase("bullet"))
      kill();
  }

  @Override
  public void dispose() {

  }
}
