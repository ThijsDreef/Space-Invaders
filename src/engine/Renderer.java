package engine;

import engine.Fx.Font;
import engine.Fx.Light;
import engine.Fx.Pixel;

import java.awt.image.DataBufferInt;

public class Renderer {
  private Engine en;
  public int backgroundColor = 0xff626262;
  private int width, height;
  private int[] pixels;
  private int[] lightMap;
  private Font font;
  private Font largeFont;

  public int ambient = 0xff777777;

  public Renderer(Engine en) {
    this.en = en;
    width = en.getWidth();
    height = en.getHeight();
    pixels = ((DataBufferInt) en.getWindow().getImage().getRaster().getDataBuffer()).getData();
    lightMap = new int[pixels.length];
    font = new Font("resources/standard.png");
    largeFont = new Font("resources/large.png");
  }

  public void setPixels(int x, int y, int color)
  {
    x -= en.getCamera().getTransx();
    y -= en.getCamera().getTransy();

    if (x < 0 || x >= width || y < 0 || y >= height || color == 0xffff00ff)
      return;
    pixels[x + y * width] = color;
  }

  public void setLightMap(int x, int y, int color)
  {
    x -= en.getCamera().getTransx();
    y -= en.getCamera().getTransy();
    if (x < 0 || x >= width || y < 0 || y >= height)
      return;
    lightMap[x + y * width] = Pixel.getMaxLight(color, lightMap[x + y * width]);
  }
  public void combineMaps() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++)
      {
        setPixels(x, y, Pixel.getLightBlend(pixels[x + y * width], lightMap[x + y * width], ambient));
        lightMap[x + y * width] = ambient;
      }
    }
  }

  public void drawString(String text, int color, int offx, int offy) {
    text = text.toUpperCase();
    int offset = 0;
    for (int i = 0; i < text.length(); i++) {
      int unicode = text.codePointAt(i) - 31;

      for (int x = 0; x < font.widths[unicode]; x++) {
        for (int y = 0; y < font.standard.height; y++) {
          if (font.standard.pixels[(x + font.offsets[unicode]) + y * font.standard.width] == 0xffffffff)
            setPixels(x + offx + offset, y + offy - 1, color);

        }
      }
      offset += font.widths[unicode];
    }
  }

  public void drawLargeString(String text, int color, int offx, int offy) {
    text = text.toUpperCase();
    int offset = 0;
    for (int i = 0; i < text.length(); i++) {
      int unicode = text.codePointAt(i) - 31;
      for (int x = 0; x < largeFont.widths[unicode]; x++) {
        for (int y = 0; y < largeFont.standard.height; y++) {
          if (largeFont.standard.pixels[(x + largeFont.offsets[unicode]) + y * largeFont.standard.width] == 0xffffffff)
            setPixels(x + offx + offset, y + offy - 1, color);
        }
      }
      offset += largeFont.widths[unicode];
    }
  }

  public void drawImage(Image image, int offX, int offy) {
    for (int x = 0; x < image.width; x++) {
      for (int y = 0; y < image.height; y++) {
        setPixels(x + offX, y + offy, image.pixels[x + y * image.width]);
      }
    }
  }

  public void drawCircle(int offx, int offy, int radius, int color) {
    int[] maxfill = new int[(int) (radius + 1)];
    int x = 0;
    int y = radius;
    double d = 5.0 / 4.0 - radius;
    setPixels(x + offx, y + offy, color);
    setPixels(-x + offx, -y + offy, color);
    setPixels(y + offx, x + offy, color);
    setPixels(-y + offx, -x + offy, color);
    while (y >= x) {
      if (d < 0)
        d += 2.0 * x + 3.0; // select east
      else {
        d += 2.0 * (x - y) + 5.0;
        y--;
      }
      x++;
      maxfill[y] = x;

      setPixels(x + offx, y + offy, color);
      setPixels(-x + offx, -y + offy, color);
      setPixels(-x + offx, y + offy, color);
      setPixels(x + offx, -y + offy, color);
      setPixels(y + offx, x + offy, color);
      setPixels(-y + offx, -x + offy, color);
      setPixels(y + offx, -x + offy, color);
      setPixels(-y + offx, x + offy, color);
    }
    y = radius;
    while (maxfill[y] != 0) {
      for (x = -maxfill[y]; x < maxfill[y]; x++) {
        setPixels(x + offx, y + offy, color);
        setPixels(x + offx, -y + offy, color);
        setPixels(y + offx, x + offy, color);
        setPixels(-y + offx, x + offy, color);
      }
      y--;
    }
    int vierkantStart=maxfill[y+1];
    drawRect(offx-vierkantStart,offy-vierkantStart,vierkantStart*2,vierkantStart*2, color);

  }
  public void clear()
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        setPixels(x, y, backgroundColor);
      }
    }
  }
  public void drawRect(int offx, int offy, int width, int height, int color)
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        setPixels(x + offx, y + offy, color);
      }
    }
  }
//  public void drawCircle(int offx, int offy, int radius, int color)
//  {
//    int diameter = radius * radius;
//    for (int x = 0; x < diameter; x++)
//    {
//      for (int y = 0; y < diameter; y++)
//      {
//        float distance = (float)Math.sqrt((x - radius) * (x - radius) + (y - radius) * (y - radius));
//
//        if (distance < radius)
//          setPixels(x + offx, y + offy, color);
//      }
//    }
//  }
  public void drawStaticLight(Light light, int offx, int offy)
  {
    offx -= en.getCamera().getTransx();
    offy -= en.getCamera().getTransy();
    for (int x = 0; x < (light.diameter ); x++)
    {
      for (int y = 0; y < (light.diameter); y++)
      {
        setLightMap(x + offx - light.radius, y + offy - light.radius, light.getLightValue(x,y));
      }
    }
//    System.out.println(light.lightArray.length);
  }
}
