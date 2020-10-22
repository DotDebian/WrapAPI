package space.debian.WrapAPI;

import org.bukkit.plugin.java.JavaPlugin;
import space.debian.WrapAPI.managers.CommandManager;
import space.debian.WrapAPI.managers.ListenerManager;
import space.debian.WrapAPI.tools.logging.WrapLogger;

public abstract class WrapAPI extends JavaPlugin {

	private static WrapAPI instance;

	public void preEnable() {
		instance = this;

		new WrapLogger(this);

		new ListenerManager();
		new CommandManager();
	}

	@Override
	public void onEnable() {

		preEnable();

		postEnable();
	}

	public void postEnable() {

		ListenerManager.get().initListeners();

		WrapLogger.get().spacer();

		CommandManager.get().initCommands();
	}

	public static WrapAPI get() {

		return instance;
	}

}
