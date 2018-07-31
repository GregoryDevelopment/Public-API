package me.grimy.api.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtils {

    public static void sendToServer(JavaPlugin javaPlugin, Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(javaPlugin, "BungeeCord", out.toByteArray());
        
        //send message here on within the command
    }
}
