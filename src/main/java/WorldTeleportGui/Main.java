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
import lombok.Getter;

import java.util.Map;

public class Main extends PluginBase {

    @Getter
    private PluginConfig pluginConfig;
    @Getter
    private LocaleManager localeManager;

    @Override
    public void onEnable() {
        this.pluginConfig = new PluginConfig(this);
        this.localeManager = new LocaleManager(this);

        this.getServer().getPluginManager().registerEvents(new GuiEvent(this), this);
        this.getServer().getCommandMap().register("wtpui", new WtpuiCommand("wtpui", this));

        if (!this.pluginConfig.isAdminsOnly())
            this.info(this.localeManager.translate("plugin-running-without-op-protection"));
        this.info(this.localeManager.translate("plugin-loaded-successfully", this.getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        this.info(this.localeManager.translate("plugin-disabled-successfully"));
    }

    public void showForm(final Player player){
        final FormWindowSimple window = new FormWindowSimple("World Teleport GUI", this.localeManager.translate("plugin-formwindow-content"));
        final Map<Integer, Level> levels = this.getServer().getLevels();

        if(this.pluginConfig.isLoadWorldWithGui())
            window.addButton(new ElementButton(this.localeManager.translate("plugin-formwindow-button-load")));

        for (Level level : levels.values())
            window.addButton(new ElementButton(level.getFolderName()));
        player.showFormWindow(window);
    }

    public void info(final String msg) {
        this.getServer().getLogger().info(TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + getDescription().getName() + TextFormat.DARK_GRAY + "] " + TextFormat.RESET + msg);
    }
}