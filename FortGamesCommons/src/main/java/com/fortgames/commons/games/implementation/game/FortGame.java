package com.fortgames.commons.games.implementation.game;

import com.fortgames.commons.games.implementation.game.phase.GamePhase;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import com.sllibrary.bukkit.terminable.Terminable;
import org.bukkit.World;

import javax.annotation.Nonnull;
import java.util.Set;

public interface FortGame extends Terminable {

    @Nonnull
    GamePhase getInitialPhase();

    void onJoin(@Nonnull FortPlayer fortPlayer);

    void onLeave(@Nonnull FortPlayer fortPlayer);

    Set<FortPlayer> getPlayers();

    Arena getArena();
}
