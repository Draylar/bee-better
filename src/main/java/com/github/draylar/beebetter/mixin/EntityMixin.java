package com.github.draylar.beebetter.mixin;

import com.github.draylar.beebetter.registry.BeeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public World world;
    @Shadow @Final protected Random random;
    @Shadow private EntityDimensions dimensions;
    @Shadow public abstract double getY();
    @Shadow public abstract double getX();
    @Shadow public abstract double getZ();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), method = "onSwimmingStart", cancellable = true)
    private void onEnterLiquid(CallbackInfo info) {
        Entity entity = (Entity) (Object) this;
        Vec3d velocity = entity.getVelocity();

        if (isInFluidTag(entity, BeeTags.HONEY)) {
            entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_STEP, 1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);

            float yPos = (float) MathHelper.floor(this.getY());

            for (int i = 0; (float) i < 1.0F + this.dimensions.width * 20.0F; ++i) {
                float particleX = (this.random.nextFloat() * 2.0F - 1.0F) * this.dimensions.width;
                float particleZ = (this.random.nextFloat() * 2.0F - 1.0F) * this.dimensions.width;
                this.world.addParticle(ParticleTypes.LANDING_HONEY, this.getX() + (double) particleX, yPos + 1.0F, this.getZ() + (double) particleZ, velocity.x, velocity.y - (double) (this.random.nextFloat() * 0.2F), velocity.z);
            }

            info.cancel();
        }
    }

    @Unique
    private boolean isInFluidTag(Entity entity, TagKey<Fluid> tag) {
        Box box = entity.getBoundingBox().contract(0.001D);
        int startX = MathHelper.floor(box.minX);
        int endX = MathHelper.ceil(box.maxX);
        int startY = MathHelper.floor(box.minY);
        int endY = MathHelper.ceil(box.maxY);
        int startZ = MathHelper.floor(box.minZ);
        int endZ = MathHelper.ceil(box.maxZ);

        if (!entity.world.isRegionLoaded(startX, startY, startZ, endX, endY, endZ)) {
            return false;
        } else {
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int x = startX; x < endX; ++x) {
                for (int y = startY; y < endY; ++y) {
                    for (int z = startZ; z < endZ; ++z) {
                        mutable.set(x, y, z);
                        FluidState fluidState = entity.world.getFluidState(mutable);

                        if (fluidState.isIn(tag)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }
}
