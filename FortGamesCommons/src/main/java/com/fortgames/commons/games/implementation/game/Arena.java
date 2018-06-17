package com.fortgames.commons.games.implementation.game;

import com.fortgames.commons.Tickable;
import com.google.common.collect.ImmutableMap;
import com.sllibrary.bukkit.Schedulers;
import com.sllibrary.bukkit.bucket.Bucket;
import com.sllibrary.bukkit.bucket.BucketPartition;
import com.sllibrary.bukkit.bucket.factory.BucketFactory;
import com.sllibrary.bukkit.bucket.partitioning.PartitioningStrategies;
import com.sllibrary.bukkit.serialize.BlockPosition;
import com.sllibrary.bukkit.serialize.BlockRegion;
import com.sllibrary.bukkit.terminable.Terminable;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Arena implements Terminable {
    @Getter
    private String name;
    @Getter
    private BlockRegion blockRegion;
    private Set<ArenaBlock> unbreakableBlocks;
    private final Map<BlockPosition, ArenaBlock> brokenBlocks = new ConcurrentHashMap<>();

    public Arena(@Nonnull String name, @Nonnull Set<ArenaBlock> unbreakableBlocks) {
        this.name = Objects.requireNonNull(name, "name");
        this.unbreakableBlocks = Objects.requireNonNull(unbreakableBlocks, "unbreakableBlocks");
    }

    public void appendBlock(Block block) {
        this.brokenBlocks.put(BlockPosition.of(block), new ArenaBlock(block.getType(), block.getState().getData()));
    }

    public boolean isBreakable(Block block) {
        return this.unbreakableBlocks
                .stream()
                .noneMatch(arenaBlock -> arenaBlock.getMaterial() == block.getType() && arenaBlock.getMaterialData().equals(block.getState().getData()));
    }

    @Override
    public void close() {
        Bucket<Map.Entry<BlockPosition, ArenaBlock>> bucket = BucketFactory.newConcurrentBucket(20, PartitioningStrategies.lowestSize());
        Map<BlockPosition, ArenaBlock> brokenBlocksImmutable = ImmutableMap.copyOf(this.brokenBlocks);
        this.brokenBlocks.clear();
        bucket.addAll(brokenBlocksImmutable.entrySet());

        Schedulers.sync().runRepeating(() -> {
            BucketPartition<Map.Entry<BlockPosition, ArenaBlock>> part = bucket.asCycle().next();

            part.forEach(locationMaterialEntry -> {
                BlockPosition blockPosition = locationMaterialEntry.getKey();
                ArenaBlock arenaBlock = locationMaterialEntry.getValue();

                Block block = blockPosition.toBlock();
                block.setType(arenaBlock.getMaterial());
                block.getState().setData(arenaBlock.getMaterialData());
                block.getState().update(true, true);
            });
        }, 1L, 1L);
    }
}
