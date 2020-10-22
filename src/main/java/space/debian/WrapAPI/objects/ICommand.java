package space.debian.WrapAPI.objects;

import org.bukkit.command.CommandExecutor;

public abstract class ICommand implements CommandExecutor {

	private String name;

	public ICommand(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
