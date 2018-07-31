package me.grimy.api.command.example;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.grimy.api.command.CommandHandler;
import me.grimy.api.command.CommandListener;
import me.grimy.api.command.CommandManager;

public class ExampleCommand implements CommandListener {

	@CommandHandler(name = "test", permission = "asdad", aliases = {"lol", "lol"})
	public void onExampleCommand(CommandSender commandSender, String label, String[] args) {
		if(commandSender instanceof Player) {
			
			/**
			 * BASIC COMMAND USAGE
			 */
			
			commandSender.sendMessage(ChatColor.RED + "lol");
			
		}
	}
	
	public static class Main extends JavaPlugin implements Listener {
		
		/**
		 * COMMANDS DONT NEED TO BE INSIDE PLUGIN.YML
		 */
		
		@Getter public static Main instance;
		
		private CommandManager commandManager;
	
		public void onEnable() {
			instance = this;
			
			commandManager = new CommandManager(this);
			
			commandManager.setNoPermissionMessage("no");
			commandManager.registerCommands(new ExampleCommand());
		}
	}
}
