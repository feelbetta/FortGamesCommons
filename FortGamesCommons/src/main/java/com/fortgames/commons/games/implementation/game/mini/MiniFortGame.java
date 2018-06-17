package com.fortgames.commons.games.implementation.game.mini;

import com.fortgames.commons.games.implementation.game.FortGame;
import com.fortgames.commons.games.implementation.game.mini.npcs.GameNpc;
import com.fortgames.commons.games.implementation.game.mini.signs.GameSign;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.stream.Collectors;

public interface MiniFortGame extends FortGame {

    default Set<GameSign> getSigns() {
        return this.getJoinableObjects()
                .stream()
                .filter(GameSign.class::isInstance)
                .map(GameSign.class::cast)
                .collect(Collectors.toSet());
    }

    void cleanup();

    @Override
    default void close() throws Exception {

    }

    @Override
    default boolean isClosed() {
        return false;
    }

    @Nullable
    @Override
    default Exception closeSilently() {
        return null;
    }

    @Override
    default void closeAndReportException() {

    }

    default Set<GameNpc> getNpcs() {
        return this.getJoinableObjects()
                .stream()
                .filter(GameNpc.class::isInstance)
                .map(GameNpc.class::cast)
                .collect(Collectors.toSet());
    }

    Set<JoinableObject> getJoinableObjects();
}
