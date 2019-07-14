package WorldTeleportGui.command;

import WorldTeleportGui.Main;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.TextFormat;

public class WtpuiCommand extends PluginCommand<Main> {

    public WtpuiCommand(final String name, final Main owner) {
        super(name, owner);
    }

    @Override
    public boolean execute(final CommandSender sender, final String alias, final String[] args) {
        if (sender instanceof Player) {
            if (this.getPlugin().getPluginConfig().isAdminsOnly()) {
                if (sender.isOp())
                    this.getPlugin().showForm((Player) sender);
                else
                    sender.sendMessage(TextFormat.RED + "You have to be op to use this command!");
            } else {
                this.getPlugin().showForm((Player) sender);
            }
        } else {
            sender.sendMessage(TextFormat.RED + "You can only use this in-game!");
        }
        return true;
    }
}