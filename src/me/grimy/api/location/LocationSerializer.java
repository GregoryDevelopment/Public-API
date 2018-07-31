package me.grimy.api.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import net.minecraft.util.org.apache.commons.lang3.Validate;

public class LocationSerializer {

	public static Location deserialize(String string) {
		Validate.notNull(string, "String cannot be null.");
		String[] args = string.split(";");

		World world = Bukkit.getServer().getWorld(args[0]);
		Validate.notNull(world, "World parsed from the string: " + string + " is null.");

		Double x = Double.valueOf(args[1]);
		Double y = Double.valueOf(args[2]);
		Double z = Double.valueOf(args[3]);

		Float yaw = Float.valueOf(args[4]);
		Float pitch = Float.valueOf(args[5]);

		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String serialize(Location location) {
		return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ()
				+ ";" + location.getYaw() + ";" + location.getPitch();
	}

}
