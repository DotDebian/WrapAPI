package space.debian.WrapAPI.objects.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static space.debian.WrapAPI.tools.Messages.errorPrefix;
import static space.debian.WrapAPI.tools.Messages.noPermissionError;

public abstract class WCommand implements CommandExecutor {

	private final String name;
	private boolean consoleOnly = false;
	private List<WSubCommand> subCommands = new ArrayList<>();
	private String permission = null;

	/**
	 * Create a new instance of a WCommand
	 * @param name Command name, must be in plugin.yml's commands.
	 */
	public WCommand(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * Bukkit's API onCommand override, only override this if you know what you're doing !
	 * @param sender Command sender
	 * @param command Command executed, a.k.a this
	 * @param string Command name as executed, can be an aliase
	 * @param args Command arguments
	 * @return False if command is off, true otherwise
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

		if (args.length > 0) {

			Optional<WSubCommand> subCommand = subCommands.stream().filter((singleSubCommand) -> singleSubCommand.getName().equalsIgnoreCase(args[0])).findFirst();

			if (subCommand.isPresent())
				return subCommand.get().subCommandProcess(sender, command, string, args);
		}

		if ((consoleOnly && sender instanceof Player) || (permission != null && !sender.hasPermission(permission))) {

			sender.sendMessage(errorPrefix + noPermissionError);
			return true;
		}

		return process(sender, command, string, args);
	}

	/**
	 * WrapAPI's command process method, override this method and build your command.
	 * @param sender Command sender
	 * @param command Command executed, a.k.a this
	 * @param string Command name as executed, can be an aliase
	 * @param args Command arguments
	 * @return False if command is off, true otherwise
	 */
	public boolean process(CommandSender sender, Command command, String string, String[] args) {

		return true;
	}

	public List<WSubCommand> getSubCommands() {
		return subCommands;
	}

	public WCommand addSubCommand(WSubCommand subCommand) {
		subCommands.add(subCommand);
		return this;
	}

	public WCommand setConsoleOnly(boolean consoleOnly) {
		this.consoleOnly = consoleOnly;
		return this;
	}

	public WCommand setPermission(String permission) {
		this.permission = permission;
		return this;
	}
}
