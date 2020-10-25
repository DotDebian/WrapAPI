package space.debian.WrapAPI.objects.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class WCommand implements CommandExecutor {

	private String name;
	private WSubCommand command;
	private List<WSubCommand> subCommands = new ArrayList<>();

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

		return process(sender, command, string, args);
	}

	/**
	 * WrapAPI's command process method, override this method and build your command !
	 * @param sender Command sender
	 * @param command Command executed, a.k.a this
	 * @param string Command name as executed, can be an aliase
	 * @param args Command arguments
	 * @return False if command is off, true otherwise
	 */
	public boolean process(CommandSender sender, Command command, String string, String[] args) {
		return true;
	}
}
