package game.managers;

import engine.Engine;
import engine.Renderer;

import java.util.ArrayList;

public class ObjectManager
{
  private ArrayList<GameObject> objects = new ArrayList<GameObject>();

  public void updateObjects(Engine en, float dt)
  {
    for (int i = 0; i < objects.size(); i++)
    {
      objects.get(i).update(en, dt);
    }
    for (int i = 0; i < objects.size(); i++)
    {
      if (objects.get(i).isDead())
        objects.remove(i);
    }
  }
  public void renderObjects(Engine en, Renderer r)
  {
    for (int i = 0; i < objects.size(); i++)
    {
      objects.get(i).render(en, r);
    }
  }
  public void addObject (GameObject object)
  {
    objects.add(object);
  }
  public GameObject findObject(String tag)
  {
    for (int i = 0; i < objects.size(); i++)
    {
      if (objects.get(i).getTag().equalsIgnoreCase(tag))
        return objects.get(i);
    }
    return null;
  }
  public ArrayList<GameObject> findGameObjects(String tag)
  {
    ArrayList<GameObject> foundObjects = new ArrayList<GameObject>();
    for (int i = 0; i < objects.size();i++)
    {
      if (objects.get(i).getTag().equalsIgnoreCase(tag))
      {
        foundObjects.add(objects.get(i));
      }
    }
    return foundObjects;
  }
  public void disposeObjects()
  {
    for (int i = 0; i < objects.size(); i++)
      objects.get(i).dispose();
  }
}
