package WorldTeleportGui.config;

import WorldTeleportGui.Main;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

public class PluginConfig {

    private final Main plugin;
    private final File file;
    private final Config config;

    public PluginConfig(final Main plugin) {
        this.plugin = plugin;
        this.plugin.info(TextFormat.YELLOW + "Loading Config...");
        this.file = new File(this.plugin.getDataFolder(), "/settings.yml");
        this.config = new Config(this.file);
        this.config.reload();
        this.plugin.info(TextFormat.YELLOW + "Config Loaded.");

        this.checkForLatestVersion();
        this.setDefaults();
    }

    private void checkForLatestVersion() {
        if(this.config.exists("version")) {
            if(!this.getVersion().equals(this.plugin.getDescription().getVersion())) {
                this.plugin.info( TextFormat.YELLOW + "It seems your on an older version, Updating Config..." );
                this.updateVersion();
                this.config.save( this.file );
                this.plugin.info( TextFormat.GREEN + "Updated Config!" );
            }
        } else {
            this.setDefaults();
        }
    }

    private void setDefaults() {
        this.updateVersion();

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

    private void updateVersion() {
        this.config.set("version", this.plugin.getDescription().getVersion());
    }

    public String getVersion() {
        return this.config.getString("version");
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