package engine.SaveAndLoad;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Properties implements Serializable
{
  String config = "Properties/config.properties";
  public Properties()
  {

      //copy the properties file from inside the jar file or from the folder
      if (new File(config).exists())
      {
        try
        {
          new File("Properties/").mkdir();
          Files.copy(this.getClass().getResourceAsStream("config.properties"), new File("Properties/config.properties").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }

  }
  public void saveVariable(String key, String value)
  {
    java.util.Properties properties = new java.util.Properties();
    try
    {
      FileInputStream input = new FileInputStream(config);
      properties.load(input);
      FileOutputStream output = new FileOutputStream(config);
      properties.setProperty(key, value);
      properties.store(output, null);
    }
    catch (IOException io)
    {
      io.printStackTrace();
    }
  }
  public String loadVariable(String key)
  {
    try
    {
      java.util.Properties properties = new java.util.Properties();
      FileInputStream input = new FileInputStream(config);
      properties.load(input);
      return properties.getProperty(key);
    }
    catch (IOException io)
    {
      io.printStackTrace();
    }
    return null;
  }
}
