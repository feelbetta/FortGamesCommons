package com.fortgames.commons.games.implementation.game.phase;

import com.fortgames.commons.games.implementation.game.FortGame;
import lombok.NonNull;

import java.time.Duration;

public interface TimedGamePhase extends GamePhase {

    @NonNull
    Duration getDuration();

    void tick(FortGame fortGame);
}
