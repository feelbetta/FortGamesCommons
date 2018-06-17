package com.fortgames.commons.games.implementation.plugin;

import com.fortgames.commons.games.implementation.game.FortGame;
import com.fortgames.commons.games.implementation.game.mini.MiniFortGame;
import com.sllibrary.bukkit.Events;
import com.sllibrary.bukkit.plugin.ExtendedJavaPlugin;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class FortGamePlugin extends ExtendedJavaPlugin {

    protected abstract FortGame getFortGame();

    @Override
    protected void enable() {
        if (this.getFortGame() instanceof MiniFortGame) {
            return;
        }
        this.getFortGame().bindWith(this);

        Events.subscribe(BlockBreakEvent.class)
                .filter(blockBreakEvent -> this.getFortGame().getPlayers().contains(blockBreakEvent.getPlayer()))
                .filter(blockBreakEvent -> this.getFortGame().getArena().isBreakable(blockBreakEvent.getBlock()))
                .handler(blockBreakEvent -> this.getFortGame().getArena().appendBlock(blockBreakEvent.getBlock()));
    }
}
