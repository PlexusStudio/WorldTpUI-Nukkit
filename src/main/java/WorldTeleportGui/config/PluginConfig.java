package WorldTeleportGui.config;

import WorldTeleportGui.Main;
import cn.nukkit.utils.Config;

import java.io.File;

public class PluginConfig {

    private final Main plugin;
    private final File file;
    private final Config config;

    public PluginConfig(final Main plugin) {
        this.plugin = plugin;
        this.file = new File(this.plugin.getDataFolder(), "/settings.yml");
        this.config = new Config(this.file);
        this.config.reload();

        this.setDefaults();
    }

    private void setDefaults() {
        this.config.set("version", this.plugin.getDescription().getVersion());

        if(!this.config.exists("language"))
            this.config.set("language", "eng");

        if (!this.config.exists("adminsOnly"))
            this.config.set("adminsOnly", true);

        if (!this.config.exists("loadWorldWithGui"))
            this.config.set("loadWorldWithGui", true);

        if (!this.config.exists("useItemToOpenGUI")) {
            this.config.set("useItemToOpenGUI", false);

            if (!this.config.exists("item"))
                this.config.set("item", 339);
        }
        this.config.save(this.file);
    }

    public String getLanguage() {
        return this.config.getString("language");
    }

    public boolean isAdminsOnly() {
        return this.config.getBoolean("adminsOnly");
    }

    public boolean isLoadWorldWithGui() {
        return this.config.getBoolean("loadWorldWithGui");
    }

    public boolean isUseItemToOpenGui() {
        return this.config.getBoolean("useItemToOpenGUI");
    }

    public int getItem() {
        return this.config.getInt("item");
    }
}