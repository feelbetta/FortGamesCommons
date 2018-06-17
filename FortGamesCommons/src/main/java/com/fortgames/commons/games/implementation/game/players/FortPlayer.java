package com.fortgames.commons.games.implementation.game.players;

import com.sllibrary.bukkit.profiles.Profile;
import com.sllibrary.bukkit.utils.Players;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public interface FortPlayer {

    static FortPlayerImpl wrap(Player player) {
        return new FortPlayerImpl(player);
    }

    static FortPlayerImpl create(@Nullable String name, @Nonnull UUID uuid) {
        return new FortPlayerImpl(name, uuid);
    }

    Profile getProfile();

    GameStatistics getGameStatistics();

    default Optional<Player> getBukkitPlayer() {
        return Players.get(this.getProfile().getUniqueId());
    }
}
