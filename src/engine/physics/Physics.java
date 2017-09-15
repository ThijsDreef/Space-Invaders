package engine.physics;

import java.util.ArrayList;

public class Physics
{
  private ArrayList<Collider> objects = new ArrayList<Collider>();

  public void update()
  {
    for (int i = 0; i < objects.size(); i++)
    {
      for (int j = i + 1; j < objects.size(); j++)
      {
        Collider col0 = objects.get(i);
        Collider col1 = objects.get(j);

        if (Math.abs(col0.getX() - col1.getX()) < col0.gethW() + col1.gethW())
        {
          if (Math.abs(col0.getY() - col1.getY()) < col0.gethH() + col1.gethH())
          {
            col0.collision(col1.getObject(), col1.getTag());
            col1.collision(col0.getObject(), col0.getTag());
          }
        }
      }
    }
    objects.clear();
  }
  public void addCollider(Collider e)
  {
    objects.add(e);
  }
}
