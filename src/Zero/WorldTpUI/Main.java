package Zero.WorldTpUI;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Zero.WorldTpUI.events.FormEvent(), this);
        getServer().getCommandMap().register("wtpui", new Zero.WorldTpUI.commands.wtpui("wtpui"));
        info("v" + getDescription().getVersion() + TextFormat.GREEN + " Has Loaded");
    }

    @Override
    public void onDisable() {
        info(TextFormat.RED + "Goodbye!");
    }

    public void info(String msg) {
        getServer().getLogger().info(TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + getDescription().getName() + TextFormat.DARK_GRAY + "] " + TextFormat.RESET + msg);
    }
}
