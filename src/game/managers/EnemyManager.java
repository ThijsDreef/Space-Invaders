package game.managers;

import engine.Engine;
import game.GameObjects.*;

import java.util.ArrayList;
import java.util.Random;

public class EnemyManager
{
  ObjectManager manager;
  int timer, spawnspeed, shooterTimer, maxshootTimer = 120, count = 2, ufoTimer = 1000;
  Random random;
  public EnemyManager(ObjectManager manager)
  {
    random = new Random();
    this.manager = manager;
    timer = 0;
    shooterTimer = 60;
    spawnspeed = 450;
    for (int i = 0; i < 2; i++)
      for (int j = 0; j < 9; j++)
      {
        int spawny = 35 + 30 * i;
        int spawnx = 15 + j * 35;
        manager.addObject(new EnemyTyp1(spawnx, spawny, 23, 16, "row" + j));
      }
  }
  public void update(Engine en, float dt)
  {
    ufoSpawn();
    if (timer <= 0)
    {
      moveRows();
      spawnNewEnemies();
      timer = spawnspeed;
      spawnspeed -= 2;
      if (maxshootTimer > 30)
        maxshootTimer--;
    }
    if (shooterTimer < 0)
    {
      randomBullet();
      shooterTimer = maxshootTimer;
    }

    shooterTimer--;
    timer--;
  }
  public void moveRows()
  {
    ArrayList<GameObject> row0 = manager.findGameObjects("row0");
    ArrayList<GameObject> row1 = manager.findGameObjects("row1");
    ArrayList<GameObject> row2 = manager.findGameObjects("row2");
    ArrayList<GameObject> row3 = manager.findGameObjects("row3");
    ArrayList<GameObject> row4 = manager.findGameObjects("row4");
    ArrayList<GameObject> row5 = manager.findGameObjects("row5");
    ArrayList<GameObject> row6 = manager.findGameObjects("row6");
    ArrayList<GameObject> row7 = manager.findGameObjects("row7");
    ArrayList<GameObject> row8 = manager.findGameObjects("row8");
    for (int i = 0; i < row0.size(); i++)
    {
      row0.get(i).addY(30);
    }
    for (int i = 0; i < row1.size(); i++)
    {
      row1.get(i).addY(30);
    }
    for (int i = 0; i < row2.size(); i++) {
      row2.get(i).addY(30);
    }
    for (int i = 0; i < row3.size(); i++)
    {
      row3.get(i).addY(30);
    }
    for (int i = 0; i < row4.size(); i++)
    {
      row4.get(i).addY(30);
    }
    for (int i = 0; i < row5.size(); i++)
    {
      row5.get(i).addY(30);
    }
    for (int i = 0; i < row6.size(); i++)
    {
      row6.get(i).addY(30);
    }
    for (int i = 0; i < row7.size(); i++)
    {
      row7.get(i).addY(30);
    }
    for (int i = 0; i < row8.size(); i++)
    {
      row8.get(i).addY(30);
    }
  }
  public void randomBullet()
  {
    int[] highesty =  new int[9];
    int[] highestx = new int[9];
    ArrayList<GameObject> row0 = manager.findGameObjects("row0");
    ArrayList<GameObject> row1 = manager.findGameObjects("row1");
    ArrayList<GameObject> row2 = manager.findGameObjects("row2");
    ArrayList<GameObject> row3 = manager.findGameObjects("row3");
    ArrayList<GameObject> row4 = manager.findGameObjects("row4");
    ArrayList<GameObject> row5 = manager.findGameObjects("row5");
    ArrayList<GameObject> row6 = manager.findGameObjects("row6");
    ArrayList<GameObject> row7 = manager.findGameObjects("row7");
    ArrayList<GameObject> row8 = manager.findGameObjects("row8");
    for (int i = 0; i < row0.size();i ++)
    {
      if (highesty[0] < row0.get(i).getY())
      {
        highesty[0] = (int)row0.get(i).getY();
        highestx[0] = (int)row0.get(i).getX();
      }
    }
    for (int i = 0; i < row1.size();i ++)
    {
      if (highesty[1] < row1.get(i).getY())
      {
        highesty[1] = (int)row1.get(i).getY();
        highestx[1] = (int)row1.get(i).getX();
      }
    }
    for (int i = 0; i < row2.size();i ++)
    {
      if (highesty[2] < row2.get(i).getY())
      {
        highesty[2] = (int)row2.get(i).getY();
        highestx[2] = (int)row2.get(i).getX();
      }
    }
    for (int i = 0; i < row3.size();i ++)
    {
      if (highesty[3] < row3.get(i).getY())
      {
        highesty[3] = (int)row3.get(i).getY();
        highestx[3] = (int)row3.get(i).getX();
      }
    }
    for (int i = 0; i < row4.size();i ++)
    {
      if (highesty[4] < row4.get(i).getY())
      {
        highesty[4] = (int)row4.get(i).getY();
        highestx[4] = (int)row4.get(i).getX();
      }
    }
    for (int i = 0; i < row5.size();i ++)
    {
      if (highesty[5] < row5.get(i).getY())
      {
        highesty[5] = (int)row5.get(i).getY();
        highestx[5] = (int)row5.get(i).getX();
      }
    }
    for (int i = 0; i < row6.size();i ++)
    {
      if (highesty[6] < row6.get(i).getY())
      {
        highesty[6] = (int)row6.get(i).getY();
        highestx[6] = (int)row6.get(i).getX();
      }
    }
    for (int i = 0; i < row7.size();i ++)
    {
      if (highesty[7] < row7.get(i).getY())
      {
        highesty[7] = (int)row7.get(i).getY();
        highestx[7] = (int)row7.get(i).getX();
      }
    }
    for (int i = 0; i < row8.size();i ++)
    {
      if (highesty[8] < row8.get(i).getY())
      {
        highesty[8] = (int)row8.get(i).getY();
        highestx[8] = (int)row8.get(i).getX();
      }
    }
    int choose = random.nextInt(9);
    int tries = 0;
    while (highestx[choose] == 0 && highesty[choose] == 0)
    {
      choose = random.nextInt(9);
      tries ++;
      if (tries == 9)
      {
        choose = 9;
        break;
      }
    }
    switch (choose) {
      case 0 :
        manager.addObject(new EnemyBullet(highestx[0] + 9, highesty[0] + 6));
        break;
      case 1 :
        manager.addObject(new EnemyBullet(highestx[1] + 9, highesty[1] + 6));
        break;
      case 2 :
        manager.addObject(new EnemyBullet(highestx[2] + 9, highesty[2] + 6));
        break;
      case 3 :
        manager.addObject(new EnemyBullet(highestx[3] + 9, highesty[3] + 6));
        break;
      case 4 :
        manager.addObject(new EnemyBullet(highestx[4] + 9, highesty[4] + 6));
        break;
      case 5 :
        manager.addObject(new EnemyBullet(highestx[5] + 9, highesty[5] + 6));
        break;
      case 6 :
        manager.addObject(new EnemyBullet(highestx[6] + 9, highesty[6] + 6));
        break;
      case 7 :
        manager.addObject(new EnemyBullet(highestx[7] + 9, highesty[7] + 6));
        break;
      case 8 :
        manager.addObject(new EnemyBullet(highestx[8] + 9, highesty[8] + 6));
        break;
      case 9 :
        break;
    }
  }
  public  void spawnNewEnemies()
  {
    if (count < 2) {
      manager.addObject(new EnemyTyp1(15, 35, 23, 16, "row0"));
      manager.addObject(new EnemyTyp1(48, 35, 23, 16, "row1"));
      manager.addObject(new EnemyTyp1(85, 35, 23, 16, "row2"));
      manager.addObject(new EnemyTyp1(120, 35, 23, 16, "row3"));
      manager.addObject(new EnemyTyp1(155, 35, 23, 16, "row4"));
      manager.addObject(new EnemyTyp1(190, 35, 23, 16, "row5"));
      manager.addObject(new EnemyTyp1(225, 35, 23, 16, "row6"));
      manager.addObject(new EnemyTyp1(260, 35, 23, 16, "row7"));
      manager.addObject(new EnemyTyp1(295, 35, 23, 16, "row8"));
    }
    if (count >= 2 && count < 4)
    {
      manager.addObject(new EnemyTyp2(15, 35, 24, 16, "row0"));
      manager.addObject(new EnemyTyp2(48, 35, 24, 16, "row1"));
      manager.addObject(new EnemyTyp2(85, 35, 24, 16, "row2"));
      manager.addObject(new EnemyTyp2(120, 35, 24, 16, "row3"));
      manager.addObject(new EnemyTyp2(155, 35, 24, 16, "row4"));
      manager.addObject(new EnemyTyp2(190, 35, 24, 16, "row5"));
      manager.addObject(new EnemyTyp2(225, 35, 24, 16, "row6"));
      manager.addObject(new EnemyTyp2(260, 35, 24, 16, "row7"));
      manager.addObject(new EnemyTyp2(295, 35, 24, 16, "row8"));
    }
    if (count >= 4)
    {
      manager.addObject(new EnemyTyp3(15, 35, 16, 16, "row0"));
      manager.addObject(new EnemyTyp3(48, 35, 16, 16, "row1"));
      manager.addObject(new EnemyTyp3(85, 35, 16, 16, "row2"));
      manager.addObject(new EnemyTyp3(120, 35, 16, 16, "row3"));
      manager.addObject(new EnemyTyp3(155,35, 16, 16, "row4"));
      manager.addObject(new EnemyTyp3(190, 35, 16, 16, "row5"));
      manager.addObject(new EnemyTyp3(225, 35, 16, 16, "row6"));
      manager.addObject(new EnemyTyp3(260, 35, 16, 16, "row7"));
      manager.addObject(new EnemyTyp3(295, 35, 16, 16, "row8"));
    }
    count++;
    if (count >= 6)
      count = 0;
  }
  public void ufoSpawn()
  {
    ufoTimer--;
    if (ufoTimer < 0)
    {
      ufoTimer = random.nextInt(9000) + 5000;
      manager.addObject(new Ufo(5, 5));
    }
  }
}
