package ca.mcpnet.CavernCarver;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CavernCarver extends JavaPlugin {

	static Logger logger = Logger.getLogger("Minecraft");
	static public void log(String msg) {
		logger.info("[CavernCarver] "+msg);
	}

	public void onEnable() {
		log("CavernCarver 0.0.1 Plugin Enabled!");
	}

	public void onDisable() {
		log("CavernCarver 0.0.1 Plugin Disabled!");		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		// carve out a cavern from the players location outwards
		if (cmd.getName().equals("cc_cavern")) {
			if (args.length > 2) {
				sender.sendMessage("Too many arguments!");
				return false;
			}
			if (args.length == 2) {
				player = getServer().getPlayer(args[1]);
				if (player == null) {
					sender.sendMessage("Player not found!");
					return false;
				}
			}
			if (player == null) {
				sender.sendMessage("Must specify player when calling from server console!");
				return false;
			}
			int radius = 0;
			try {
				radius = Integer.decode(args[0]);
			} catch (NumberFormatException e) {
				sender.sendMessage("Must specify the size of the cavern");
				return false;
			}
			CavernCarverTask task = new CavernCarverTask(this, sender, player, player.getLocation(), radius);
			task.setTaskid(getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 10L, 2L));
			sender.sendMessage("Scheduled cavern carver job "+task.getTaskid()+", radius "+ radius);
			return true;
		}
		return false;
	}

}
