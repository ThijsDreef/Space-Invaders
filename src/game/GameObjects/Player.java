package game.GameObjects;

import engine.Engine;
import engine.Image;
import engine.Renderer;
import engine.SaveAndLoad.Properties;
import engine.physics.Collider;
import game.States.EndState;
import game.managers.GameObject;

import java.awt.event.KeyEvent;

public class Player extends GameObject
{
  Properties prop = new Properties();
  int speed =100;
  int timer = 0, lives = 3;
  Image player;
  public Player(int x, int y)
  {
    setTag("player");
    this.x = x;
    this.y = y;
    w = 23;
    h = 10;
    player = new Image("resources/player.png");
    addComponent(new Collider("player", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (getState() == 1) {
      lives++;
      setState(0);
    }
    if (getState() == 2)
    {
      endGame(en);
    }
    if (en.getInput().isKeyPressed(KeyEvent.VK_A))
      if (x > 0)
        x -= Math.round(speed * dt);

    if (en.getInput().isKeyPressed(KeyEvent.VK_D))
      if (x < 320 - w)
        x += Math.round(speed * dt);

    if (en.getInput().isKeyPressed(KeyEvent.VK_SPACE))
      if (en.getGame().peek().getManager().findObject("bullet") == null)
      {
        en.getGame().peek().getManager().addObject(new Bullet((int) (x + w / 2 - 1), (int) y - 2));
      }
    updateComponents(en, dt);
    if (lives <= 0) {
      endGame(en);
    }
    timer --;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawImage(player, (int)x,(int)y);
    r.drawString("Lives: " + lives, 0xffffffff, 287, 0);
  }

  @Override
  public void componentEvent(String name, GameObject object)
  {
    if (name.equalsIgnoreCase("enemybullet") && timer <= 0)
    {
      timer = 10;
      lives--;
    }
  }
  @Override
  public void dispose() {

  }
  private void endGame(Engine en)
  {
    prop.saveVariable("current-Score", "" + en.getGame().peek().getScoreManager().getScore());
    int highScore = Integer.parseInt(prop.loadVariable("high-score"));
    if (en.getGame().peek().getScoreManager().getScore() > highScore)
      en.getGame().peek().getScoreManager().saveScore();
    en.getGame().changeToState(new EndState());
  }
}
