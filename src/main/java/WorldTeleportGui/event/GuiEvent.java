package WorldTeleportGui.events;

import WorldTeleportGui.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.Objects;

public class GuiEvent implements Listener {

    private final Main plugin;

    public GuiEvent(final Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerFormResponded(final PlayerFormRespondedEvent event) {
        final Player player = event.getPlayer();
        final FormWindow window = event.getWindow();

        if (event.getResponse() == null) return;
        if (window instanceof FormWindowSimple) {
            final String title = ((FormWindowSimple) event.getWindow()).getTitle();
            final String button = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();

            if (!event.wasClosed()) {
                if (title.equals("World Teleport GUI")) {
                    if (button.equals("Load All Worlds.")) {
                        final File worlds = new File(this.plugin.getServer().getDataPath() + "/worlds");

                        if (worlds.exists()) {
                            for (String world : Objects.requireNonNull(worlds.list())) {
                                if (!this.plugin.getServer().isLevelLoaded(world)) {
                                    this.plugin.getServer().loadLevel(world);
                                }
                            }
                            this.plugin.showForm(player);
                        }
                    } else {
                        final Level level = this.plugin.getServer().getLevelByName(button);

                        if (this.plugin.getServer().isLevelLoaded(level.getFolderName()))
                            player.teleport(level.getSafeSpawn());
                        else
                            player.sendMessage(TextFormat.RED + "The world you are trying to teleport does not exist or isn't loaded");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (this.plugin.getPluginConfig().isUseItemToOpenGui()) {
            final long currentTime = System.currentTimeMillis();
            final Item item = new Item(this.plugin.getPluginConfig().getItem());

            item.setCustomName(TextFormat.AQUA + "World Teleport");
            item.setCustomBlockData(new CompoundTag().putString("wtp", "teleport").putLong("Last Used", currentTime));

            if(this.plugin.getPluginConfig().isAdminsOnly()){
                if(player.isOp())
                    player.getInventory().addItem(item);
            } else {
                player.getInventory().addItem(item);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Item item = event.getItem();

        if (item.hasCustomBlockData()) {
            if (item.getCustomBlockData().contains("wtp") && item.getCustomBlockData().contains("Last Used")) {
                final String customBlockData = item.getCustomBlockData().getString("wtp");
                final long currentTime = System.currentTimeMillis();
                final int cooldown = 1000;//1 second

                if (customBlockData.equals("teleport")) {
                    if ((currentTime - item.getCustomBlockData().getLong("Last Used")) >= cooldown) {
                        this.plugin.showForm(player);

                        item.setCustomBlockData(
                                new CompoundTag()
                                        .putString("wtp", "teleport")
                                        .putLong("Last Used", currentTime));
                    }
                }
            }
        }
    }
}