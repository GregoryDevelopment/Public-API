package me.grimy.api.scoreboard.provider;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import me.grimy.api.scoreboard.board.Board;

public interface BoardProvider {

    /**
     * Gets the title for {@link Objective#getDisplayName()}
     *
     * @param player The {@link Player} to supply
     * @return The title for the objective
     */
    String getTitle(Player player);

    /**
     * Gets the contents to be displayed on the {@link Board}
     *
     * @param player The {@link Player} to supply
     * @return The {@link List} of contents
     */
    List<String> getLines(Player player);
}