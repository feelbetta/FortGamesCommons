package com.fortgames.commons.games.implementation.game.mini.npcs;

import com.fortgames.commons.games.implementation.game.mini.JoinableObject;
import com.google.common.collect.Lists;
import com.sllibrary.bukkit.Services;
import com.sllibrary.bukkit.hologram.Hologram;
import com.sllibrary.bukkit.hologram.HologramFactory;
import com.sllibrary.bukkit.npc.Npc;
import com.sllibrary.bukkit.npc.NpcFactory;

import javax.annotation.Nonnull;

public interface GameNpc extends JoinableObject {

    default void spawn() {
        NpcFactory npcFactory = Services.load(NpcFactory.class);
        HologramFactory hologramFactory = Services.load(HologramFactory.class);
        this.setNpc(npcFactory.spawnNpc(this.getPosition().toLocation(), "Join!", ""));
        this.setHologram(hologramFactory.newHologram(this.getPosition().toPosition(), Lists.newArrayList(" ")));
        this.getHologram().spawn();

    }

    default void despawn() {
        this.getNpc().despawn();
        this.getHologram().closeSilently();
    }

    void setNpc(@Nonnull Npc npc);

    Npc getNpc();

    Hologram getHologram();

    void setHologram(@Nonnull Hologram hologram);
}
