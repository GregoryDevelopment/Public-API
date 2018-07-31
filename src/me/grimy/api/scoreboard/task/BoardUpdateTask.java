package me.grimy.api.scoreboard.task;

import java.util.UUID;
import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.RequiredArgsConstructor;
import me.grimy.api.scoreboard.BoardManager;

@RequiredArgsConstructor
public class BoardUpdateTask extends BukkitRunnable {

    private static final Predicate<UUID> PLAYER_IS_ONLINE = uuid -> Bukkit.getPlayer(uuid) != null;

    private final BoardManager boardManager;

    @Override
    public void run() {
        boardManager.getScoreboards().entrySet().stream().filter(entrySet -> PLAYER_IS_ONLINE.test(entrySet.getKey())).forEach(entrySet -> entrySet.getValue().update());
    }
}