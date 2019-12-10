package com.github.draylar.beebetter.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import com.github.draylar.beebetter.block.ApiaryBlock;
import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import com.github.draylar.beebetter.util.BeeState;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public abstract class ModdedBeehiveBlockEntity extends BlockEntity implements Tickable {
	
	protected final List<Bee> bees = Lists.newArrayList();
	protected BlockPos flowerPos = null;
	
	public ModdedBeehiveBlockEntity(BlockEntityType<?> type) {
		super(type);
	}
	
	@Override
	public void markDirty() {
		if (this.isHiveNearFire()) {
			this.angerBees(null, this.world.getBlockState(this.getPos()), BeeState.EMERGENCY);
		}
		
		super.markDirty();
	}
	
	public boolean isHiveNearFire() {
		if (this.world == null) {
			return false;
		} else {
			Iterator var1 = BlockPos.iterate(this.pos.add(-1, -1, -1), this.pos.add(1, 1, 1)).iterator();
			
			BlockPos blockPos;
			do {
				if (!var1.hasNext()) {
					return false;
				}
				
				blockPos = (BlockPos) var1.next();
			} while (!(this.world.getBlockState(blockPos).getBlock() instanceof FireBlock));
			
			return true;
		}
	}
	
	public abstract int getMaxBees();
	
	public boolean hasNoBees() {
		return this.bees.isEmpty();
	}
	
	public boolean isFullOfBees() {
		return this.bees.size() == getMaxBees();
	}
	
	public void angerBees(PlayerEntity playerEntity, BlockState blockState, BeeState beeState) {
		List<Entity> list = this.tryReleaseBee(blockState, beeState);
		if (playerEntity != null) {
			Iterator var5 = list.iterator();
			
			while (var5.hasNext()) {
				Entity entity = (Entity) var5.next();
				if (entity instanceof BeeEntity) {
					BeeEntity beeEntity = (BeeEntity) entity;
					if (playerEntity.getPos().squaredDistanceTo(entity.getPos()) <= 16.0D) {
						if (!this.isSmoked()) {
							beeEntity.setBeeAttacker(playerEntity);
						} else {
							beeEntity.setCannotEnterHiveTicks(400);
						}
					}
				}
			}
		}
		
	}
	
	private List<Entity> tryReleaseBee(BlockState blockState, BeeState beeState) {
		List<Entity> list = Lists.newArrayList();
		this.bees.removeIf((bee) -> this.releaseBee(blockState, bee.entityData, list, beeState));
		return list;
	}
	
	public void tryEnterHive(Entity entity, boolean hasNectar) {
		this.tryEnterHive(entity, hasNectar, 0);
	}
	
	public int getBeeCount() {
		return this.bees.size();
	}
	
	public abstract int getHoneyLevel(BlockState blockState);
	
	public boolean isSmoked() {
		return CampfireBlock.isLitCampfireInRange(this.world, this.getPos(), 5);
	}
	
	public void tryEnterHive(Entity entity, boolean hasNectar, int ticksInHive) {
		if (this.bees.size() < getMaxBees()) {
			entity.removeAllPassengers();
			CompoundTag compoundTag = new CompoundTag();
			entity.saveToTag(compoundTag);
			this.bees.add(new Bee(compoundTag, ticksInHive, hasNectar ? 2400 : 600));
			if (this.world != null) {
				if (entity instanceof BeeEntity) {
					BeeEntity beeEntity = (BeeEntity) entity;
					if (beeEntity.hasFlower() && (!this.hasFlowerPos() || this.world.random.nextBoolean())) {
						this.flowerPos = beeEntity.getFlowerPos();
					}
				}
				
				BlockPos blockPos = this.getPos();
				this.world.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_BEEHIVE_ENTER, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
			
			entity.remove();
		}
	}
	
	private boolean releaseBee(BlockState blockState, CompoundTag compoundTag, List<Entity> list, BeeState beeState) {
		BlockPos pos = this.getPos();
		
		if ((this.world.isNight() || this.world.isRaining()) && beeState != BeeState.EMERGENCY) {
			return false;
		} else {
			compoundTag.remove("Passengers");
			compoundTag.remove("Leash");
			compoundTag.removeUuid("UUID");
			Direction direction = blockState.get(ApiaryBlock.FACING);
			BlockPos offsetPos = pos.offset(direction);
			
			if (!this.world.getBlockState(offsetPos).getCollisionShape(this.world, offsetPos).isEmpty()) {
				return false;
			} else {
				Entity entity = EntityType.loadEntityWithPassengers(compoundTag, this.world, (passenger) -> passenger);
				
				if (entity != null) {
					float entityWidth = entity.getWidth();
					double entitySize = 0.55D + (double) (entityWidth / 2.0F);
					double spawnX = (double) pos.getX() + 0.5D + entitySize * (double) direction.getOffsetX();
					double spawnY = (double) pos.getY() + 0.5D - (double) (entity.getHeight() / 2.0F);
					double spawnZ = (double) pos.getZ() + 0.5D + entitySize * (double) direction.getOffsetZ();
					entity.setPositionAndAngles(spawnX, spawnY, spawnZ, entity.yaw, entity.pitch);
					
					if (!entity.getType().isTaggedWith(EntityTypeTags.BEEHIVE_INHABITORS)) {
						return false;
					} else {
						if (entity instanceof BeeEntity) {
							BeeEntity beeEntity = (BeeEntity) entity;
							
							if (this.hasFlowerPos() && !beeEntity.hasFlower() && this.world.random.nextFloat() < 0.9F) {
								beeEntity.setFlowerPos(this.flowerPos);
							}
							
							if (beeState == BeeState.HONEY_DELIVERED) {
								beeEntity.onHoneyDelivered();
								
								if (blockState.getBlock().matches(BlockTags.BEEHIVES)) {
									int i = getHoneyLevel(blockState);
									if (i < 10) {
										int j = this.world.random.nextInt(100) == 0 ? 2 : 1;
										
										if (i + j > 10) {
											--j;
										}
										
										((ModdedBeehiveBlock) blockState.getBlock()).addHoney(world, blockState, getPos(), j);
									}
								}
							}
							
							if (list != null) {
								beeEntity.resetPollinationTicks();
								list.add(beeEntity);
							}
						}
						
						BlockPos apiaryPos = this.getPos();
						this.world.playSound(null, apiaryPos.getX(), apiaryPos.getY(), apiaryPos.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
						return this.world.spawnEntity(entity);
					}
				} else {
					return false;
				}
			}
		}
	}
	
	private boolean hasFlowerPos() {
		return this.flowerPos != null;
	}
	
	private void tickBees() {
		Iterator<Bee> iterator = this.bees.iterator();
		BlockState blockState = this.getCachedState();
		
		while (iterator.hasNext()) {
			Bee bee = iterator.next();
			
			if (bee.ticksInHive > bee.minOccupationTicks) {
				CompoundTag compoundTag = bee.entityData;
				BeeState beeState = compoundTag.getBoolean("HasNectar") ? BeeState.HONEY_DELIVERED : BeeState.BEE_RELEASED;
				
				if (this.releaseBee(blockState, compoundTag, null, beeState)) {
					iterator.remove();
				}
			} else {
				bee.ticksInHive++;
			}
		}
		
	}
	
	public void tick() {
		if (!this.world.isClient) {
			this.tickBees();
			BlockPos blockPos = this.getPos();
			if (this.bees.size() > 0 && this.world.getRandom().nextDouble() < 0.005D) {
				double d = (double) blockPos.getX() + 0.5D;
				double e = blockPos.getY();
				double f = (double) blockPos.getZ() + 0.5D;
				this.world.playSound(null, d, e, f, SoundEvents.BLOCK_BEEHIVE_WORK, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
	}
	
	public void fromTag(CompoundTag compoundTag) {
		super.fromTag(compoundTag);
		this.bees.clear();
		ListTag listTag = compoundTag.getList("Bees", 10);
		
		for (int i = 0; i < listTag.size(); ++i) {
			CompoundTag compoundTag2 = listTag.getCompound(i);
			Bee bee = new Bee(compoundTag2.getCompound("EntityData"), compoundTag2.getInt("TicksInHive"), compoundTag2.getInt("MinOccupationTicks"));
			this.bees.add(bee);
		}
		
		this.flowerPos = null;
		if (compoundTag.contains("FlowerPos")) {
			this.flowerPos = NbtHelper.toBlockPos(compoundTag.getCompound("FlowerPos"));
		}
		
	}
	
	public CompoundTag toTag(CompoundTag compoundTag) {
		super.toTag(compoundTag);
		compoundTag.put("Bees", this.getBees());
		if (this.hasFlowerPos()) {
			compoundTag.put("FlowerPos", NbtHelper.fromBlockPos(this.flowerPos));
		}
		
		return compoundTag;
	}
	
	public ListTag getBees() {
		ListTag listTag = new ListTag();
		Iterator var2 = this.bees.iterator();
		
		while (var2.hasNext()) {
			Bee bee = (Bee) var2.next();
			bee.entityData.removeUuid("UUID");
			CompoundTag compoundTag = new CompoundTag();
			compoundTag.put("EntityData", bee.entityData);
			compoundTag.putInt("TicksInHive", bee.ticksInHive);
			compoundTag.putInt("MinOccupationTicks", bee.minOccupationTicks);
			listTag.add(compoundTag);
		}
		
		return listTag;
	}
	
	private static class Bee {
		private final CompoundTag entityData;
		private final int minOccupationTicks;
		private int ticksInHive;
		
		private Bee(CompoundTag entityData, int ticksInHive, int minOccupationTicks) {
			entityData.removeUuid("UUID");
			this.entityData = entityData;
			this.ticksInHive = ticksInHive;
			this.minOccupationTicks = minOccupationTicks;
		}
	}
}
