package game.managers;

import engine.Engine;
import engine.Renderer;

public abstract class Component
{
  protected String tag = "null";
  public abstract void update(Engine en, GameObject object, float dt);
  public abstract void render(Engine en, Renderer r);

  public String getTag()
  {
    return tag;
  }
  public void setTag(String tag)
  {
    this.tag = tag;
  }
}
