package space.debian.WrapAPI;

import org.bukkit.plugin.java.JavaPlugin;
import space.debian.WrapAPI.managers.ListenerManager;
import space.debian.WrapAPI.tools.logging.WrapLogger;

public abstract class WrapAPI extends JavaPlugin {

	private static WrapAPI instance;

	@Override
	public void onEnable() {
		instance = this;

		new WrapLogger(this);
		new ListenerManager();
	}

	public static WrapAPI get() {

		return instance;
	}

}
