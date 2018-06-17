package com.fortgames.test.game;

import com.fortgames.commons.games.implementation.game.FortGame;
import com.fortgames.commons.games.implementation.game.phase.GamePhase;

import java.time.Duration;

public class DroppingBusPhase implements GamePhase {

    @Override
    public void initialize(FortGame fortGame) {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void setRunning(boolean running) {

    }

    @Override
    public Duration getDurationPassed() {
        return null;
    }
}
