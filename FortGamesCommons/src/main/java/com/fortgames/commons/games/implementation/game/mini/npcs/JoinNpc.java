package com.fortgames.commons.games.implementation.game.mini.npcs;

import com.fortgames.commons.games.implementation.game.mini.JoinableObjectAction;
import com.fortgames.commons.games.implementation.game.mini.MiniFortGame;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import com.google.common.collect.Lists;
import com.sllibrary.bukkit.hologram.Hologram;
import com.sllibrary.bukkit.npc.Npc;
import com.sllibrary.bukkit.serialize.BlockPosition;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class JoinNpc implements GameNpc {

    public static final List<String> NPC_FORMAT = Lists.newArrayList(


            "&cThere are",
                      "&e{players} Playing"


    );

    private MiniFortGame game;
    private BlockPosition position;
    private Npc npc;
    private Hologram hologram;

    public JoinNpc(@Nonnull MiniFortGame game, @Nonnull BlockPosition position) {
        this.game = Objects.requireNonNull(game, "game");
        this.position = Objects.requireNonNull(position, "position");
    }

    @Override
    public void setNpc(@Nonnull Npc npc) {
        this.npc = Objects.requireNonNull(npc, "npc");
    }

    @Override
    public void setPosition(@Nonnull BlockPosition blockPosition) {
        this.position = Objects.requireNonNull(position, "position");
    }

    @Override
    public void setHologram(@Nonnull Hologram hologram) {
        this.hologram = Objects.requireNonNull(hologram, "hologram");
    }

    @Override
    public void onClick(@Nonnull FortPlayer fortPlayer, @Nonnull JoinableObjectAction joinableObjectAction) {
        if (joinableObjectAction != JoinableObjectAction.RIGHT) {
            return;
        }
        this.game.onJoin(fortPlayer);
    }

    @Override
    public void update() {
        this.hologram
                .updateLines(JoinNpc.NPC_FORMAT
                .stream()
                .map(s -> s.replace("{map}", this.game.getArena().getName()).replace("{players}", Integer.toString(this.game.getPlayers().size())))
                .collect(Collectors.toList()));
    }
}
