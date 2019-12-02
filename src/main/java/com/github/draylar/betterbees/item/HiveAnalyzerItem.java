package com.github.draylar.betterbees.item;

import net.minecraft.block.BeeHiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeeHiveBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.github.draylar.betterbees.block.ModdedBeehiveBlock;
import com.github.draylar.betterbees.entity.ModdedBeehiveBlockEntity;

public class HiveAnalyzerItem extends Item {
	public HiveAnalyzerItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		if(!world.isClient()) {
			BlockPos pos = context.getBlockPos();
			BlockState state = world.getBlockState(pos);
			if(state.matches(BlockTags.BEEHIVES)) {
				PlayerEntity player = context.getPlayer();
				int bees;
				boolean fullOfBees;
				int honey;
				boolean fullOfHoney;
				if(state.getBlock() instanceof BeeHiveBlock) {
					BeeHiveBlockEntity be = (BeeHiveBlockEntity) world.getBlockEntity(pos);
					bees = be.getBees().size();
					honey = state.get(BeeHiveBlock.HONEY_LEVEL);
					fullOfBees = be.isFullOfBees();
					fullOfHoney = honey == 5;
				} else if(state.getBlock() instanceof ModdedBeehiveBlock) {
					ModdedBeehiveBlock block = (ModdedBeehiveBlock) state.getBlock();
					ModdedBeehiveBlockEntity be = (ModdedBeehiveBlockEntity) world.getBlockEntity(pos);
					bees = be.getBeeCount();
					honey = state.get(block.getHoneyProperty());
					fullOfBees = be.isFullOfBees();
					fullOfHoney = honey == block.getMaxHoneyLevel();
				} else {
					return super.useOnBlock(context);
				}
				if(bees == 0) {
					player.sendMessage(new TranslatableText("text.betterbees.no_bees_inside"));
				} else if(bees == 1) {
					player.sendMessage(new TranslatableText("text.betterbees.one_bee_inside"));
				} else if(fullOfBees) {
					player.sendMessage(new TranslatableText("text.betterbees.filled_with_bees_inside"));
				} else {
					player.sendMessage(new TranslatableText("text.betterbees.multiple_bees_inside"));
				}
				if(honey == 0) {
					player.sendMessage(new TranslatableText("text.betterbees.no_honey_inside"));
				} else if(fullOfHoney) {
					player.sendMessage(new TranslatableText("text.betterbees.filled_with_honey_inside"));
				} else if(honey == 4) {
					player.sendMessage(new TranslatableText("text.betterbees.almost_enough_honey_inside"));
				} else if(honey >= 5) {
					player.sendMessage(new TranslatableText("text.betterbees.enough_honey_inside"));
				} else {
					player.sendMessage(new TranslatableText("text.betterbees.some_honey_inside"));
				}
				return ActionResult.SUCCESS;
			}
			return super.useOnBlock(context);
		}
		return ActionResult.SUCCESS;
	}
}
