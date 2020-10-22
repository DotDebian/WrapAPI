package space.debian.WrapAPI.managers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import space.debian.WrapAPI.WrapAPI;
import space.debian.WrapAPI.objects.ICommand;
import space.debian.WrapAPI.tools.logging.WrapLogger;

import java.util.ArrayList;

public class CommandManager {

	private static CommandManager instance;

	private ArrayList<Class<? extends ICommand>> commands = new ArrayList<>();

	public CommandManager() {

		instance = this;
	}

	public void initCommands() {

		WrapLogger.get().info("Instancing commands.");

		commands.forEach((command) -> {
			try {

				ICommand commandInstance = command.newInstance();
				Bukkit.getServer().getPluginCommand(commandInstance.getName()).setExecutor(commandInstance);

				WrapLogger.get().info(command.getSimpleName() + " command instantiated.");
			} catch (InstantiationException | IllegalAccessException e) {

				WrapLogger.get().severe("An error occured while instancing the " + command.getSimpleName() + " command.");
				WrapLogger.get().severe(e.getMessage());
			}
		});
	}



	public void addCommand(Class<? extends ICommand> command) {

		if (!commands.contains(command))
			commands.add(command);
	}

	public ArrayList<Class<? extends ICommand>> getCommands() {
		return commands;
	}

	public static CommandManager get() {

		return instance;
	}
}
