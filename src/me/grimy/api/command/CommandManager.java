package me.grimy.api.command;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.Setter;
import me.grimy.api.abstracts.AbstractManager;
import net.md_5.bungee.api.ChatColor;

public class CommandManager extends AbstractManager {

	@Getter
	private JavaPlugin plugin;
	@Getter
	@Setter
	private String noPermissionMessage;

	public CommandManager(JavaPlugin plugin, String noPermissionMessage) {
		this.plugin = plugin;
		this.noPermissionMessage = noPermissionMessage;
	}

	public CommandManager(JavaPlugin plugin) {
		this(plugin,
				ChatColor.translateAlternateColorCodes('&', "Im sorry you dont have permissions to use this command"));
	}

	public void registerCommands(CommandListener commandListener) {
		List<Method> methods = getCommandsMethods(commandListener.getClass());
		
		methods.forEach(method -> {
			final CommandHandler commandHandler = method.getAnnotation(CommandHandler.class);
			
			BukkitCommand bukkitCommand = new BukkitCommand(commandHandler.name()) {
			
				@Override
				public boolean execute(CommandSender commandSender, String s, String[] strings) {
					if(!commandHandler.usage().equalsIgnoreCase("None")) {
						String[] usage = commandHandler.usage().split(" ");
						
						if(strings.length < usage.length) {
							commandSender.sendMessage(ChatColor.RED + this.getUsage());
							return true;
						}
					}
					
					if(!commandHandler.permission().equalsIgnoreCase("None")) {
						if(!commandSender.hasPermission(commandHandler.permission())) {
							commandSender.sendMessage(noPermissionMessage);
							return true;
						}
					}
					
					try {
						method.invoke(commandListener, commandSender, s, strings);
					} catch (IllegalAccessException | InvocationTargetException e) {
						
						plugin.getLogger().log(Level.INFO, "Couldnt load: " + commandHandler.name());
						
						return true;
					}
					
					return true;
				}
			};
			
			if(!commandHandler.aliases()[0].equalsIgnoreCase("None")) {
				bukkitCommand.setAliases(Arrays.asList(commandHandler.aliases()));
			}
			
			bukkitCommand.setDescription(commandHandler.description());
			
			if(!commandHandler.permission().equalsIgnoreCase("None")) {
				bukkitCommand.setPermission(commandHandler.permission());
			}
			
			if(!commandHandler.usage().equalsIgnoreCase("None")) {
				bukkitCommand.setUsage("/" + commandHandler.name() + " " + commandHandler.usage());
			}
			
			try {
				Field commandMap = plugin.getServer().getClass().getDeclaredField("commandMap");
				
				commandMap.setAccessible(true);
				
				SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commandMap.get(plugin.getServer());
				simpleCommandMap.register(bukkitCommand.getName(), bukkitCommand);
				
				plugin.getLogger().log(Level.INFO, "Registered command: " + commandHandler.name());
				
				commandMap.setAccessible(false);
				
			} catch (NoSuchFieldException | IllegalAccessException e) {
				plugin.getLogger().log(Level.WARNING, "CUNT THE SHIT DIDNT FUCKING LOAD");
			}
		});
	}

	private List<Method> getCommandsMethods(final Class<?> type) {
		final List<Method> methods = new ArrayList<>();

		Class<?> clazz = type;

		while (clazz != Object.class) {
			final List<Method> allMethods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));

			allMethods.forEach(method -> addCommandAnnotated(methods, method));

			clazz = clazz.getSuperclass();
		}

		return methods;
	}

	private List<Method> addCommandAnnotated(List<Method> base, Method method) {
		if (method.isAnnotationPresent((Class<? extends Annotation>) CommandHandler.class)) {
			base.add(method);
		}

		return base;
	}

}
