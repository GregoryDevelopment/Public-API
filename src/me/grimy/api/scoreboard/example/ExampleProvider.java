package me.grimy.api.scoreboard.example;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.grimy.api.scoreboard.BoardManager;
import me.grimy.api.scoreboard.BoardSettings;
import me.grimy.api.scoreboard.ScoreDirection;
import me.grimy.api.scoreboard.provider.BoardProvider;

public class ExampleProvider extends JavaPlugin {

    private BoardManager manager;

    @Override
    public void onEnable() {
        manager = new BoardManager(this, BoardSettings.builder().boardProvider(new ExampleProviderImplementation()).scoreDirection(ScoreDirection.UP).build());
    }

    @Override
    public void onDisable() {
        manager.onDisable();
    }

    private class ExampleProviderImplementation implements BoardProvider {

        @Override
        public String getTitle(Player player) {
            return "Willy";
        }

        @Override
        public List<String> getLines(Player player) {
            List<String> lines = new ArrayList<>();
            lines.add("Willy");
            return lines;
        }
    }
}