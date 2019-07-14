package WorldTeleportGui;

import WorldTeleportGui.command.WtpuiCommand;
import WorldTeleportGui.config.PluginConfig;
import WorldTeleportGui.event.GuiEvent;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.Map;

public class Main extends PluginBase {

    private PluginConfig pluginConfig;

    @Override
    public void onEnable() {//TODO Add Multi Lang Support.
        this.pluginConfig = new PluginConfig(this);

        this.getServer().getPluginManager().registerEvents(new GuiEvent( this ), this);
        this.getServer().getCommandMap().register("wtpui", new WtpuiCommand("wtpui", this));

        if (!this.pluginConfig.isAdminsOnly())
            this.info(TextFormat.RED + "You are running this without op protection");
        this.info("v" + this.pluginConfig.getVersion() + TextFormat.GREEN + " Has Loaded");
    }

    @Override
    public void onDisable() {
        this.info(TextFormat.RED + "Goodbye!");
    }

    public void showForm(final Player player){
        final FormWindowSimple window = new FormWindowSimple("World Teleport GUI", "Teleport to any world");
        final Map<Integer, Level> levels = this.getServer().getLevels();

        if(this.pluginConfig.isLoadWorldWithGui())
            window.addButton(new ElementButton("Load All Worlds."));

        for (Level level : levels.values())
            window.addButton(new ElementButton(level.getFolderName()));
        player.showFormWindow(window);
    }

    public void info(final String msg) {
        this.getServer().getLogger().info(TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + getDescription().getName() + TextFormat.DARK_GRAY + "] " + TextFormat.RESET + msg);
    }

    public PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }
}