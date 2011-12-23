package ca.mcpnet.CavernCarver;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class CavernCarver extends JavaPlugin {

	static Logger logger = Logger.getLogger("Minecraft");
	static public void log(String msg) {
		logger.info("[CavernCarver] "+msg);
	}

	public void onDisable() {
		log("CavernCarver 0.0.1 Plugin Enabled!");		
	}

	public void onEnable() {
		log("CavernCarver 0.0.1 Plugin Enabled!");
	}

}
