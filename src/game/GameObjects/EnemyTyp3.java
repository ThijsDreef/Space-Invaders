package game.GameObjects;

import engine.Engine;
import engine.Image;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

import java.util.Random;

public class EnemyTyp3 extends GameObject
{
  Random random = new Random();
  int timer = 20, animationSpeed = 20;
  int stepcount = 0;
  boolean animation = false;
  Image spaceVader, spaceVader0, spaceVader1;
  boolean left = true;
  float speed = 8.5f;
  public EnemyTyp3(int x, int y, int w, int h, String tag)
  {
    setState(random.nextInt(3));
    this.tag = tag;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    spaceVader0 = new Image("resources/triangle0.png");
    spaceVader1 = new Image("resources/triangle1.png");
    addComponent(new Collider("enemy", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (y > 190)
      en.getGame().peek().getManager().findObject("player").setState(2);
    if (left == true)
    {
      stepcount += 1;
      x -= speed * dt;
    }
    if (left == false)
    {
      stepcount += 1;
      x += speed * dt;
    }
    if (stepcount >= 70)
    {
      left = !left;
      stepcount = 0;
    }
    if (isDead())
      en.getGame().peek().getScoreManager().addscore(30);
    updateComponents(en, dt);
    animate();
  }

  @Override
  public void render(Engine en, Renderer r) {
    r.drawImage(spaceVader, (int)x, (int)y);
  }

  @Override
  public void componentEvent(String name, GameObject object)
  {
    if (name.equalsIgnoreCase("bullet"))
      kill();
  }

  @Override
  public void dispose() {

  }
  public void animate()
  {
    if (animation)
      spaceVader = spaceVader0;
    if (!animation)
      spaceVader = spaceVader1;
    if (timer <= 0) {
      animation = !animation;
      timer = animationSpeed;
    }
    timer--;
  }
}
