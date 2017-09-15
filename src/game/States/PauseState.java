package game.States;

import engine.Engine;
import engine.Renderer;
import game.managers.State;

import java.awt.event.KeyEvent;

public class PauseState extends State
{
  State oldState;
  public PauseState(State s)
  {
    oldState = s;
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (en.getInput().isKeyReleased(KeyEvent.VK_ESCAPE))
    {
      en.getGame().changeToState(oldState);
    }

  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawLargeString("the game is paused", 0xffaabbcc, 85,95);
  }

  @Override
  public void dispose() {

  }
}
