package com.fortgames.commons.games.implementation.game.features;

import com.fortgames.commons.games.implementation.game.FortGame;

import java.util.function.Consumer;

public interface Feature<G extends FortGame> {

    boolean enabled();

    void init(Consumer<G> game);
}
