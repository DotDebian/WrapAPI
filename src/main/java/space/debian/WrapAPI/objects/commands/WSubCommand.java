package space.debian.WrapAPI.objects.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static space.debian.WrapAPI.tools.Messages.errorPrefix;
import static space.debian.WrapAPI.tools.Messages.noPermissionError;

public class WSubCommand {

	private final WCommand parent;
	private final String name;
	private boolean consoleOnly = false;
	private String permission = null;

	/**
	 * Create a new instance of a WSubCommand
	 * @param parent WCommand parent of this subcommand
	 * @param name Name of the subcommand, used when checking parent's args
	 */
	public WSubCommand(WCommand parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	/**
	 * WrapAPI's subcommand processor method, only override this if you know what you're doing !
	 * @param sender Command sender
	 * @param command Command executed, a.k.a parent
	 * @param string Command name as executed, can be an aliase
	 * @param args Command arguments
	 * @return False if command is off, true otherwise
	 */
	public boolean subCommandProcess(CommandSender sender, Command command, String string, String[] args) {

		if ((consoleOnly && sender instanceof Player) || (permission != null && !sender.hasPermission(permission))) {

			sender.sendMessage(errorPrefix + noPermissionError);
			return true;
		}

		return execute(sender, command, string, args);
	}

	/**
	 * WrapAPI's subcommand process method, override this method and build your command.
	 * @param sender Command sender
	 * @param command Command executed, a.k.a parent
	 * @param string Command name as executed, can be an aliase
	 * @param args Command arguments
	 * @return False if command is off, true otherwise
	 */
	public boolean execute(CommandSender sender, Command command, String string, String[] args) {

		return true;
	}

	public WCommand getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public WSubCommand setConsoleOnly(boolean consoleOnly) {
		this.consoleOnly = consoleOnly;
		return this;
	}

	public WSubCommand setPermission(String permission) {
		this.permission = permission;
		return this;
	}
}
