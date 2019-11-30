package com.github.draylar.betterbees.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import com.mojang.datafixers.Dynamic;

import com.github.draylar.betterbees.registry.BeeBlocks;
import com.github.draylar.betterbees.registry.BeeFluids;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class HoneyPoolFeature extends LakeFeature {
	
	private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
	
	public HoneyPoolFeature(Function<Dynamic<?>, ? extends BushFeatureConfig> configFactory) {
		super(configFactory);
	}
	
	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, BushFeatureConfig bushFeatureConfig) {
		ArrayList<BlockPos> placedPositions = new ArrayList<>();
		
		while (blockPos.getY() > 5 && world.isAir(blockPos)) {
			blockPos = blockPos.down();
		}
		
		if (blockPos.getY() <= 4) {
			return false;
		} else {
			blockPos = blockPos.down(4);
			ChunkPos chunkPos = new ChunkPos(blockPos);
			
			if (!world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_REFERENCES).getStructureReferences(Feature.VILLAGE.getName()).isEmpty()) {
				return false;
			} else {
				boolean[] bls = new boolean[2048];
				int i = random.nextInt(4) + 4;
				
				int dfz;
				for (dfz = 0; dfz < i; ++dfz) {
					double d = random.nextDouble() * 6.0D + 3.0D;
					double e = random.nextDouble() * 4.0D + 2.0D;
					double f = random.nextDouble() * 6.0D + 3.0D;
					double g = random.nextDouble() * (16.0D - d - 2.0D) + 1.0D + d / 2.0D;
					double h = random.nextDouble() * (8.0D - e - 4.0D) + 2.0D + e / 2.0D;
					double k = random.nextDouble() * (16.0D - f - 2.0D) + 1.0D + f / 2.0D;
					
					for (int x = 1; x < 15; ++x) {
						for (int z = 1; z < 15; ++z) {
							for (int y = 1; y < 7; ++y) {
								double o = ((double) x - g) / (d / 2.0D);
								double p = ((double) y - h) / (e / 2.0D);
								double q = ((double) z - k) / (f / 2.0D);
								
								double r = o * o + p * p + q * q;
								if (r < 1.0D) {
									bls[(x * 16 + z) * 8 + y] = true;
								}
							}
						}
					}
				}
				
				int y;
				int z;
				boolean bl2;
				for (dfz = 0; dfz < 16; ++dfz) {
					for (z = 0; z < 16; ++z) {
						for (y = 0; y < 8; ++y) {
							bl2 = !bls[(dfz * 16 + z) * 8 + y] && (dfz < 15 && bls[((dfz + 1) * 16 + z) * 8 + y] || dfz > 0 && bls[((dfz - 1) * 16 + z) * 8 + y] || z < 15 && bls[(dfz * 16 + z + 1) * 8 + y] || z > 0 && bls[(dfz * 16 + (z - 1)) * 8 + y] || y < 7 && bls[(dfz * 16 + z) * 8 + y + 1] || y > 0 && bls[(dfz * 16 + z) * 8 + (y - 1)]);
							if (bl2) {
								Material material = world.getBlockState(blockPos.add(dfz, y, z)).getMaterial();
								if (y >= 4 && material.isLiquid()) {
									return false;
								}
								
								if (y < 4 && !material.isSolid() && world.getBlockState(blockPos.add(dfz, y, z)) != bushFeatureConfig.state) {
									return false;
								}
							}
						}
					}
				}
				
				for (dfz = 0; dfz < 16; ++dfz) {
					for (z = 0; z < 16; ++z) {
						for (y = 0; y < 8; ++y) {
							if (bls[(dfz * 16 + z) * 8 + y]) {
								BlockPos placePos = blockPos.add(dfz, y, z);
								world.setBlockState(placePos, y >= 4 ? CAVE_AIR : bushFeatureConfig.state, 2);
								placedPositions.add(new BlockPos(placePos.getX(), placePos.getY(), placePos.getZ()));
							}
						}
					}
				}
				
				
				for (BlockPos pos : placedPositions) {
					
					// placed positions include cave air and the liquid; filter out the cave air
					if (!world.getBlockState(pos).isAir()) {
						
						// surround with redstone on sides
						for (Direction direction : Direction.values()) {
							BlockPos offsetPos = pos.offset(direction);
							BlockState offsetState = world.getBlockState(offsetPos);
							
							// not liquid honey, not up (cave air)
							if (!offsetState.getFluidState().getFluid().equals(BeeFluids.HONEY) && direction != Direction.UP) {
								world.setBlockState(offsetPos, BeeBlocks.BEESWAX_BLOCK.getDefaultState(), 3);
							}
						}
					}
				}
				
				return true;
			}
		}
	}
}
