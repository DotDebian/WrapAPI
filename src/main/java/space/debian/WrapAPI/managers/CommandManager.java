package space.debian.WrapAPI.managers;

import org.bukkit.Bukkit;
import space.debian.WrapAPI.objects.commands.WCommand;
import space.debian.WrapAPI.tools.logging.WLogger;

import java.util.ArrayList;

public class CommandManager {

	private static CommandManager instance;

	private ArrayList<Class<? extends WCommand>> commands = new ArrayList<>();

	public CommandManager() {

		instance = this;
	}

	public void initCommands() {

		WLogger.get().info("Instancing commands.");

		commands.forEach((command) -> {
			try {

				WCommand commandInstance = command.newInstance();
				Bukkit.getServer().getPluginCommand(commandInstance.getName()).setExecutor(commandInstance);

				WLogger.get().info("  " + command.getSimpleName() + " command instantiated.");
			} catch (InstantiationException | IllegalAccessException e) {

				WLogger.get().severe("  An error occured while instancing the " + command.getSimpleName() + " command.");
				WLogger.get().severe(e.getMessage());
			}
		});
	}



	public void addCommand(Class<? extends WCommand> command) {

		if (!commands.contains(command))
			commands.add(command);
	}

	public ArrayList<Class<? extends WCommand>> getCommands() {
		return commands;
	}

	public static CommandManager get() {

		return instance;
	}
}
