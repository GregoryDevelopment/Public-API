package me.grimy.api.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerMethods {

    public static void potionResetPlayer(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public static void playerRefilSoup(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) {
                player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
            }
        }
    }

    public static void reset(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        potionResetPlayer(player);
    }
}
