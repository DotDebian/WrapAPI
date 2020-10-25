package space.debian.WrapAPI;

import org.bukkit.plugin.java.JavaPlugin;
import space.debian.WrapAPI.managers.CommandManager;
import space.debian.WrapAPI.managers.ListenerManager;
import space.debian.WrapAPI.tools.logging.WLogger;

public abstract class WPlugin extends JavaPlugin {

	private static WPlugin instance;

	public void preEnable() {
		instance = this;

		new WLogger(this);

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

		WLogger.get().spacer();

		CommandManager.get().initCommands();
	}

	public static WPlugin get() {

		return instance;
	}

}
