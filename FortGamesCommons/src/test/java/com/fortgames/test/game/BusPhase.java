package com.fortgames.test.game;

import com.fortgames.commons.games.implementation.game.FortGame;
import com.fortgames.commons.games.implementation.game.phase.GamePhase;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

public class BusPhase implements GamePhase {

    @Getter @Setter
    private boolean running;
    private Duration durationPassed;

    @Override
    public void initialize(FortGame fortGame) {
        fortGame.getPlayers().stream().map(FortPlayer::getBukkitPlayer).filter(Optional::isPresent).map(Optional::get).forEach(player -> {

            player.sendMessage("You are being loaded on the bus!");

        });
        this.durationPassed = Duration.ofMillis(System.currentTimeMillis());
    }

    @Nullable
    @Override
    public GamePhase getNextPhase() {
        return null;
    }


    @Override
    public Duration getDurationPassed() {
        return this.durationPassed;
    }
}
