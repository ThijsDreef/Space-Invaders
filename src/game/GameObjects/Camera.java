package game.GameObjects;

import engine.Engine;
import engine.Renderer;
import game.managers.GameObject;

import java.awt.event.KeyEvent;

/**
 * Created by Thijs Dreef on 18/01/2017.
 */
public class Camera extends GameObject
{
  @Override
  public void update(Engine en, float dt)
  {
    if (en.getInput().isKeyPressed(KeyEvent.VK_A))
      en.getCamera().subtractTransx(1);
    if (en.getInput().isKeyPressed(KeyEvent.VK_D))
      en.getCamera().addTransx(1);
    if (en.getInput().isKeyPressed(KeyEvent.VK_W))
      en.getCamera().subtractTransY(1);
    if (en.getInput().isKeyPressed(KeyEvent.VK_S))
      en.getCamera().addTransy(1);
  }

  @Override
  public void render(Engine en, Renderer r) {

  }

  @Override
  public void componentEvent(String name, GameObject object) {

  }

  @Override
  public void dispose() {

  }
}
