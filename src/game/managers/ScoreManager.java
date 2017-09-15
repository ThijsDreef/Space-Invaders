package game.managers;

import engine.Engine;
import engine.SaveAndLoad.Properties;
import engine.Renderer;

public class ScoreManager
{
  Properties prop;
  public ScoreManager()
  {
    prop = new Properties();
  }
  int score = 0;
  public void addscore(int scoreToAdd)
  {
    score += scoreToAdd;
  }
  public void render(Engine en, Renderer r)
  {
    r.drawString("score " + score, 0xffffffff, 0, 0);
    r.drawString("high score: " + prop.loadVariable("high-score"), 0xffffffff, en.getWidth() / 2 - 40, 0);
  }
  public void saveScore()
  {
    prop.saveVariable("high-score", "" + score);
  }
  public int getScore()
  {
    return score;
  }
}
