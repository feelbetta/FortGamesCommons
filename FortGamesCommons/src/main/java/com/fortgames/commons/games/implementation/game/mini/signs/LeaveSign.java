package com.fortgames.commons.games.implementation.game.mini.signs;

import com.fortgames.commons.games.implementation.game.mini.JoinableObjectAction;
import com.fortgames.commons.games.implementation.game.mini.MiniFortGame;
import com.fortgames.commons.games.implementation.game.players.FortPlayer;
import com.sllibrary.bukkit.serialize.BlockPosition;
import com.sllibrary.bukkit.text.Text;
import com.sllibrary.bukkit.utils.Log;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.stream.IntStream;

public class LeaveSign implements GameSign {

    private final MiniFortGame game;
    @Getter
    private BlockPosition position;

    public LeaveSign(@Nonnull MiniFortGame game, @Nonnull BlockPosition position) {
        this.game = Objects.requireNonNull(game, "game");
        this.position = Objects.requireNonNull(position, "position");
    }

    @Override
    public void onClick(@Nonnull FortPlayer fortPlayer, @Nonnull JoinableObjectAction joinableObjectAction) {
        if (Objects.requireNonNull(joinableObjectAction, "signAction") != JoinableObjectAction.RIGHT) {
            return;
        }
        this.game.onLeave(Objects.requireNonNull(fortPlayer, "fortPlayer"));
    }

    @Override
    public void setPosition(@Nonnull BlockPosition position) {
        this.position = Objects.requireNonNull(position, "position");
    }

    @Override
    public void update() {
        Block block = this.position.toBlock();

        if (block.getType() != Material.WALL_SIGN) {
            Log.warn("Unable to locate wall sign at location " + this.position.toString());
            return;
        }
        Sign sign = ((Sign) block.getState());
        IntStream
                .range(0, JoinSign.SIGN_FORMAT.length)
                .forEach(line -> sign.setLine(line, Text.colorize(JoinSign.SIGN_FORMAT[line].replace("{map}", this.game.getArena().getName()).replace("{players}", Integer.toString(this.game.getPlayers().size())))));
    }
}
