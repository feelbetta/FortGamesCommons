package com.fortgames.commons.games.implementation.game.phase;

import com.fortgames.commons.games.implementation.game.FortGame;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.time.Duration;

public interface GamePhase {

    void initialize(FortGame fortGame);

    @Nullable
    default GamePhase getNextPhase() {
        return null;
    }

    boolean isRunning();

    void setRunning(boolean running);

    @NonNull
    Duration getDurationPassed();
}
