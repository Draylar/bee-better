package com.github.draylar.beebetter.ai;

import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestStorage;

import com.github.draylar.beebetter.entity.ModdedBeehiveBlockEntity;
import com.github.draylar.beebetter.registry.BeePOI;
import com.github.draylar.beebetter.util.IBeeAccessor;

import java.util.Optional;
import java.util.stream.Stream;

public class FindModdedHiveGoal extends NotAngryGoal {
	
	public FindModdedHiveGoal(BeeEntity bee) {
		super(bee);
	}
	
	@Override
	public boolean canBeeStart() {
		return this.getBee().age % 10 == 0 && !this.getBee().hasHive();
	}
	
	@Override
	public boolean canBeeContinue() {
		return false;
	}
	
	@Override
	public void start() {
		Stream<BlockPos> stream = this.method_23742(20);
		Optional<BlockPos> optional = stream.filter((blockPos) -> {
			BlockEntity blockEntity = this.getBee().world.getBlockEntity(blockPos);
			if ((blockEntity instanceof BeehiveBlockEntity && !((BeehiveBlockEntity) blockEntity).isFullOfBees()) || (blockEntity instanceof ModdedBeehiveBlockEntity && !((ModdedBeehiveBlockEntity) blockEntity).isFullOfBees())) {
				Path path = this.getBee().getNavigation().findPathTo(blockPos, 20);
				return path != null;
			} else {
				return false;
			}
		}).findFirst();
		optional.ifPresent((blockPos) -> {
			((IBeeAccessor) this.getBee()).setHivePos(blockPos);
		});
	}
	
	private Stream<BlockPos> method_23742(int i) {
		BlockPos blockPos = new BlockPos(this.getBee());
		if (this.getBee().world instanceof ServerWorld) {
			Stream<PointOfInterest> stream = ((ServerWorld) this.getBee().world).getPointOfInterestStorage().get((pointOfInterestType) ->
							pointOfInterestType == BeePOI.MODDED_BEEHIVES,
					blockPos,
					i,
					PointOfInterestStorage.OccupationStatus.ANY
			);
			return stream.map(PointOfInterest::getPos);
		} else {
			return Stream.empty();
		}
	}
}
