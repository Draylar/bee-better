package com.github.draylar.beebetter.mixin.hive;

import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlock.class)
public abstract class BeehiveBlockMixin extends Block {

    @Shadow @Final public static IntProperty HONEY_LEVEL;
    @Shadow @Final public static DirectionProperty FACING;

    public BeehiveBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;with(Lnet/minecraft/state/property/Property;Ljava/lang/Comparable;)Ljava/lang/Object;", ordinal = 0))
    private Object cancelDefaultState(BlockState instance, Property property, Comparable comparable) {
        if((Object) this instanceof ModdedBeehiveBlock moddedBeehiveBlock) {
            return instance.with(moddedBeehiveBlock.getHoneyProperty(), 0);
        }

        return instance.with(HONEY_LEVEL, 0);
    }
}
