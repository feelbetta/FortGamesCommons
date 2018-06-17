package com.fortgames.commons.games.implementation.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@AllArgsConstructor @Data
public class ArenaBlock {
    private Material material;
    private MaterialData materialData;
}
