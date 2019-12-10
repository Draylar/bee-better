package com.github.draylar.beebetter.item;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import com.github.draylar.beebetter.entity.ModdedBeehiveBlockEntity;

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
				if(state.getBlock() instanceof BeehiveBlock) {
					BeehiveBlockEntity be = (BeehiveBlockEntity) world.getBlockEntity(pos);
					bees = be.getBees().size();
					honey = state.get(BeehiveBlock.HONEY_LEVEL);
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
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".no_bees_inside"));
				} else if(bees == 1) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".one_bee_inside"));
				} else if(fullOfBees) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".filled_with_bees_inside"));
				} else {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".multiple_bees_inside"));
				}
				if(honey == 0) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".no_honey_inside"));
				} else if(fullOfHoney) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".filled_with_honey_inside"));
				} else if(honey == 4) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".almost_enough_honey_inside"));
				} else if(honey >= 5) {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".enough_honey_inside"));
				} else {
					player.sendMessage(new TranslatableText("text."+BeeBetter.MODID+".some_honey_inside"));
				}
				player.getItemCooldownManager().set(this, 20);
				return ActionResult.SUCCESS;
			}
			return super.useOnBlock(context);
		}
		return ActionResult.SUCCESS;
	}
}
