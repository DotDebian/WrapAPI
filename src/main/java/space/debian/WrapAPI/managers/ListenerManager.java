package space.debian.WrapAPI.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import space.debian.WrapAPI.WrapAPI;
import space.debian.WrapAPI.tools.logging.WrapLogger;

import java.util.ArrayList;

public class ListenerManager {

	private static ListenerManager instance;

	private ArrayList<Class<? extends Listener>> eventListeners = new ArrayList<>();

	public ListenerManager() {

		instance = this;
	}

	public void initListeners() {

		WrapLogger.get().info("Instancing listeners.");

		eventListeners.forEach((listener) -> {
			try {

				Bukkit.getServer().getPluginManager().registerEvents(listener.newInstance(), WrapAPI.get());

				WrapLogger.get().info("  " + listener.getSimpleName() + " listener instantiated.");
			} catch (InstantiationException | IllegalAccessException e) {

				WrapLogger.get().severe("  An error occured while instancing " + listener.getSimpleName() + " listener.");
				WrapLogger.get().severe(e.getMessage());
			}
		});
	}



	public void addListener(Class<? extends Listener> listener) {

		if (!eventListeners.contains(listener))
			eventListeners.add(listener);
	}

	public ArrayList<Class<? extends Listener>> getEventListeners() {
		return eventListeners;
	}

	public static ListenerManager get() {

		return instance;
	}
}
