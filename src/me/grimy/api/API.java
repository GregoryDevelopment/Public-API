package me.grimy.api;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;

import me.grimy.api.abstracts.AbstractManager;
import me.grimy.api.command.CommandManager;

public class API extends JavaPlugin implements Listener {

	private Set<AbstractManager> managers;
	private CommandManager commnadManager;
	
	@Getter
	public static API instance;

	public void onEnable() {
		instance = this;
		
		this.managers = new HashSet<>();
		
		loadManagaer(new CommandManager(this));
	}
	
	public void onDisable() {
		instance = null;
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public <T> T getManager(Class<?> clazz) {
		Stream<AbstractManager> stream = ImmutableSet.copyOf(managers).stream();
		return (T) stream.filter(manager -> manager.getClass().equals(clazz)).findFirst().orElse(null);
	}
	
	/**
	 * LOAD MANAGERS
	 * @param manager
	 */
	
	public void loadManagaer(AbstractManager manager) {
		managers.add(manager);
		getLogger().log(Level.INFO, "Loaded Manager: " + manager.toString());
	}
}
