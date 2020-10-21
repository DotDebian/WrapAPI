package space.debian.WrapAPI.tools.logging;

import org.apache.commons.lang.text.StrSubstitutor;

import java.util.HashMap;

public class ConsoleColor {

	private static HashMap<String, String> severeColorMap = new HashMap<>();
	private static HashMap<String, String> warningColorMap = new HashMap<>();
	private static HashMap<String, String> infoColorMap = new HashMap<>();
	private static StrSubstitutor severeSubstitutor;
	private static StrSubstitutor warningSubstitutor;
	private static StrSubstitutor infoSubstitutor;

	public static final String RESET = "\033[0m";

	static {

		infoColorMap.put("p", "\033[38;5;012m");
		infoColorMap.put("s", "\033[38;5;033m");
		warningColorMap.put("p", "\033[38;5;220m");
		warningColorMap.put("s", "\033[38;5;208m");
		severeColorMap.put("p", "\033[38;5;196m");
		severeColorMap.put("s", "\033[38;5;124m");

		severeSubstitutor = new StrSubstitutor(severeColorMap, "%(", ")");
		warningSubstitutor = new StrSubstitutor(warningColorMap, "%(", ")");
		infoSubstitutor = new StrSubstitutor(infoColorMap, "%(", ")");
	}

	public static StrSubstitutor getSevereSubstitutor() {
		return severeSubstitutor;
	}

	public static StrSubstitutor getWarningSubstitutor() {
		return warningSubstitutor;
	}

	public static StrSubstitutor getInfoSubstitutor() {
		return infoSubstitutor;
	}

	public static HashMap<String, String> getSevereColorMap() {
		return severeColorMap;
	}

	public static HashMap<String, String> getWarningColorMap() {
		return warningColorMap;
	}

	public static HashMap<String, String> getInfoColorMap() {
		return infoColorMap;
	}
}
