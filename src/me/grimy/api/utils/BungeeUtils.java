package me.grimy.api.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.grimy.api.API;

public class BungeeUtils {
	
//	public static Map<String, Integer> playerCounts = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	
    public static void sendToServer(JavaPlugin javaPlugin, Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(javaPlugin, "BungeeCord", out.toByteArray());
        
        //send message here on within the command
    }
    
//    public static void startPlayerCountTask() {
//		List<String> servers = API.getInstance().getConfig().getStringList("PlayerCountTracker.Servers");
//
//		new BukkitRunnable() {
//			public void run() {
//				for (String s : servers) {
//					ByteArrayDataOutput globalOut = ByteStreams.newDataOutput();
//					globalOut.writeUTF("PlayerCount");
//					globalOut.writeUTF(s);
//					Bukkit.getServer().sendPluginMessage(API.getInstance(), "BungeeCord", globalOut.toByteArray());
//				}
//			}
//		}.runTaskTimer(API.getInstance(), 20, 20);
//	}
//    
//    public static int getPlayerCount(String server) {
//		return (playerCounts.containsKey(server) ? playerCounts.get(server) : 0);
//	}
}
