package com.fortgames.test.game;

import com.fortgames.commons.games.implementation.game.Arena;
import com.fortgames.commons.games.implementation.game.FortGame;
import com.fortgames.commons.games.implementation.game.mini.JoinableObject;
import com.fortgames.commons.games.implementation.game.mini.MiniFortGame;
import com.fortgames.commons.games.implementation.game.phase.GamePhase;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;

import java.util.Set;

public class FortniteGame implements MiniFortGame {

    @Override
    public void cleanup() {

    }

    @Override
    public Set<JoinableObject> getJoinableObjects() {
        return null;
    }

    @Override
    public GamePhase getInitialPhase() {
        return new BusPhase();
    }

    @Override
    public void onJoin(FortPlayer fortPlayer) {

    }

    @Override
    public void onLeave(FortPlayer fortPlayer) {

    }

    @Override
    public Set<FortPlayer> getPlayers() {
        return null;
    }

    @Override
    public Arena getArena() {
        return null;
    }
}
