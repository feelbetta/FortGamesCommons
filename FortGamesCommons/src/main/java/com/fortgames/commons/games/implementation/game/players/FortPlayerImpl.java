package com.fortgames.commons.games.implementation.game.players;

import com.sllibrary.bukkit.profiles.Profile;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class FortPlayerImpl {

    private Profile profile;
    private GameStatistics gameStatistics;

    public FortPlayerImpl(String name, UUID uuid) {
        this.profile = Profile.create(uuid, name);
    }

    public FortPlayerImpl(Player player) {
        this(player.getName(), player.getUniqueId());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Player) {
            return (((Player) o).getUniqueId().equals(this.profile.getUniqueId()));
        }
        if (o instanceof FortPlayerImpl) {
            return ((FortPlayerImpl) o).profile.getUniqueId().equals(this.profile.getUniqueId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile, gameStatistics);
    }
}
