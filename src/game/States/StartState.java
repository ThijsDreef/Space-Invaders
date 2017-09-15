package game.States;

import engine.Engine;
import engine.Fx.Light;
import engine.Renderer;
import game.GameObjects.Camera;
import game.GameObjects.EnemyTyp1;
import game.managers.State;

import java.awt.event.KeyEvent;


public class StartState extends State
{
  Light mid = new Light(0xff00bb00, 300);
  public StartState()
  {
    manager.addObject(new EnemyTyp1(20,20, 23, 16, "f"));
    manager.addObject(new EnemyTyp1(270,20, 23, 16, "f"));
    manager.addObject(new EnemyTyp1(20,180, 23, 16, "f"));
    manager.addObject(new EnemyTyp1(270,180, 23, 16, "f"));
    manager.addObject(new Camera());
  }
  @Override
  public void update(Engine en, float dt) {
    if (en.getInput().isKeyPressed(KeyEvent.VK_SPACE))
      en.getGame().changeToState(new PlayState());
    manager.updateObjects(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    manager.renderObjects(en, r);
    r.drawLargeString("Space Invaders", 0xffffffff, 105, 10);
    r.drawLargeString("press space to start", 0xffffffff, en.getWidth() / 2 - 72, en.getHeight() / 2 - 10);
    r.drawStaticLight(mid, en.getWidth() / 2 - 4, en.getHeight() / 2);

  }

  @Override
  public void dispose() {

  }
}
