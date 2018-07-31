package me.grimy.api.scoreboard;

import lombok.Builder;
import lombok.Getter;
import me.grimy.api.scoreboard.provider.BoardProvider;

@Getter
@Builder
public class BoardSettings {

    private BoardProvider boardProvider;

    private ScoreDirection scoreDirection;
}
