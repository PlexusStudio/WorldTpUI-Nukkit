package Zero.WorldTpUI;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase{
	
  private static Main instance;

  @Override
  public void onEnable(){
    instance = this;
    getServer().getPluginManager().registerEvents(new Zero.WorldTpUI.events.FormEvent(this), this);
    getServer().getCommandMap().register("wtpui", new Zero.WorldTpUI.commands.wtpui(this, "wtpui"));
    info(TextFormat.GREEN +"Is now Enabled!");
  }

  @Override
  public void onDisable(){
    info(TextFormat.RED +"Goodbye!");
  }
  
  public static Main getInstance(){
    return instance;
  }

  public void info(String msg){
    getLogger().info(msg);
  }
}
