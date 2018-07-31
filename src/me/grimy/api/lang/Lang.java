package me.grimy.api.lang;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.grimy.api.API;

/**
 * OPEN SOURCE LANG FILE
 * 
 * @author Owner
 *
 */

public class Lang {

	private static File f = new File(API.getInstance().getDataFolder(), "lang.yml");
	private static FileConfiguration data = YamlConfiguration.loadConfiguration(f);

	public static String getString(String path) {
		if (!data.contains(path)) {
			return "Message not found!";
		} else {
			return ChatColor.translateAlternateColorCodes('&', data.getString(path));
		}
	}

	public static List<String> getList(String path) {
		if (!data.contains(path)) {
			return Arrays.asList("Message not found!");
		} else {
			return data.getStringList(path);
		}
	}

	public static void sendList(Player p, String path) {
		if (!data.contains(path)) {
			p.sendMessage("Message not found!");
		} else {
			List<String> list = data.getStringList(path);
			for (String s : list) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
			}
		}
	}
}