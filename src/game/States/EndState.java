package game.States;

import engine.Engine;
import engine.Renderer;
import engine.SaveAndLoad.Properties;
import game.managers.State;

public class EndState extends State
{
  int timer;
  Properties prop = new Properties();
  public EndState()
  {
    timer = 250;

  }
  @Override
  public void update(Engine en, float dt)
  {
    if (timer < 0)
    {
      en.getGame().changeToState(new StartState());
    }
    timer--;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawLargeString("GAME OVER", 0xffffffff, 120, 30);
    r.drawLargeString("your score was : " + prop.loadVariable("current-Score"), 0xffffffff, 90, 70);
    r.drawLargeString("the high score was: " + prop.loadVariable("high-score"), 0xffffffff, 80, 120);
    if (Integer.valueOf(prop.loadVariable("current-Score")) > Integer.valueOf(prop.loadVariable("high-score")))
    {
      r.drawLargeString("YOU BEAT THE HIGH SCORE", 0xffffffff, 75, 170);

    }
  }

  @Override
  public void dispose() {

  }
}
