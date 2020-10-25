package space.debian.WrapAPI.tools.logging;

import org.bukkit.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WLogger extends Logger {

	private static WLogger instance;
	private String pluginName;

	/**
	 * Creates a new PluginLogger that extracts the name from a plugin.
	 *
	 * @param context A reference to the plugin
	 */
	public WLogger(Plugin context) {
		super(context.getClass().getCanonicalName(), null);

		instance = this;
		setParent(context.getServer().getLogger());
		setLevel(Level.ALL);
	}

	@Override
	public void log(Level level, String message) {
		super.log(level, "\033[38;5;244m#~ " + message);
	}

	@Override
	public void severe(String var1) {
		this.log(Level.SEVERE, ConsoleColor.getSevereColorMap().get("p") + ConsoleColor.getSevereSubstitutor().replace(var1) + ConsoleColor.RESET);
	}

	@Override
	public void warning(String var1) {
		this.log(Level.WARNING, ConsoleColor.getWarningColorMap().get("p") + ConsoleColor.getWarningSubstitutor().replace(var1) + ConsoleColor.RESET);
	}

	@Override
	public void info(String var1) {
		this.log(Level.INFO, ConsoleColor.getInfoColorMap().get("p") + ConsoleColor.getInfoSubstitutor().replace(var1) + ConsoleColor.RESET);
	}

	public void spacer() {
		super.log(Level.INFO, "\033[38;5;244m#~ ------------------------------------------------ ~#\033[0m");
	}

	public static WLogger get() {
		return instance;
	}

}