package com.fortgames.commons.games.implementation.game.mini;

import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import com.sllibrary.bukkit.serialize.BlockPosition;

import javax.annotation.Nonnull;

public interface JoinableObject {

    @Nonnull
    BlockPosition getPosition();

    void setPosition(@Nonnull BlockPosition blockPosition);

    void onClick(@Nonnull FortPlayer fortPlayer, @Nonnull JoinableObjectAction joinableObjectAction);

    void update();
}
