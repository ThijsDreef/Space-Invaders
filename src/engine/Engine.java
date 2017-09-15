package engine;

import engine.physics.Physics;

import java.awt.event.KeyEvent;

public class Engine implements Runnable
{
  private Thread thread;
  private AbstractGame game;

  private Window window;
  private Renderer renderer;
  private Input input;
  private Camera camera;
  private Physics physics;

  private int width = 320, height = 220;
  private float scale = 2.5f;
  private String title = "TrainWreck Engine v0.1";
  private boolean isRunning = false;
  private boolean lights = false;
  private boolean debug = false;
  private double frameCap = 1.0 / 60.0;
  private boolean framecapped = false;
  public Engine(AbstractGame game)
  {
    this.game = game;
  }
  public void start()
  {
    if (isRunning)
      return;
    window = new Window(this);
    renderer = new Renderer(this);
    input = new Input(this);
    physics = new Physics();
    camera = new Camera();
    thread = new Thread(this);
    thread.run();


  }
  public void stop()
  {
    if (!isRunning)
      return;

    isRunning = false;
  }
  public void run()
  {
    isRunning = true;

    double lastTime = System.nanoTime() / 1000000000.0;
    double firstTime = 0;
    double passedTime = 0;
    double unproccesedTime = 0;
    double frameTime = 0;

    int displayFrames = 0;
    int frames = 0;
    while (isRunning)
    {
      boolean render = framecapped;

      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      unproccesedTime += passedTime;
      frameTime += passedTime;
      while (unproccesedTime >= frameCap)
      {
        if (input.isKeyPressed(KeyEvent.VK_F1))
          framecapped = !framecapped;
        if (input.isKeyPressed(KeyEvent.VK_F2))
          debug = !debug;
        game.update(this, (float)frameCap);
        physics.update();
        input.update();
        unproccesedTime -= frameCap;
        render = true;

        if (frameTime >= 1)
        {
          frameTime = 0;
          displayFrames = frames;
          frames = 0;
        }
      }
      if (render)
      {
        renderer.clear();
        game.render(this, renderer);
        if (lights)
          renderer.combineMaps();
        if (debug)
          renderer.drawString("fps: " + displayFrames, 0xff000000, 0, 10);
        window.update();
        frames++;
        game.changeState();
      }
      else
      {
        try
        {
          Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  public Window getWindow() {
    return window;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Renderer getRenderer() {
    return renderer;
  }

  public Input getInput()
  {
    return input;
  }

  public Physics getPhysics() {
    return physics;
  }

  public void setPhysics(Physics physics) {
    this.physics = physics;
  }

  public AbstractGame getGame() {
    return game;
  }

  public boolean isLights() {
    return lights;
  }

  public void setLights(boolean lights) {
    this.lights = lights;
  }

  public Camera getCamera() {
    return camera;
  }
}
