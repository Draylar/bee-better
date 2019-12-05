package com.github.draylar.beebetter.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.Difficulty;

public class BeeStingerItem extends SwordItem {
	
	public BeeStingerItem(Settings settings) {
		super(new ToolMaterial() {
			@Override
			public int getDurability() {
				return 256;
			}
			
			@Override
			public float getMiningSpeed() {
				return 3;
			}
			
			@Override
			public float getAttackDamage() {
				return 3;
			}
			
			@Override
			public int getMiningLevel() {
				return 1;
			}
			
			@Override
			public int getEnchantability() {
				return 15;
			}
			
			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.EMPTY;
			}
		}, 0, -2.0f, settings);
	}
	
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.getRandom().nextInt(stack.getMaxDamage() - stack.getDamage()) == 0) {
			int i = 0;
			if (target.world.getDifficulty() == Difficulty.NORMAL) {
				i = 10;
			} else if (target.world.getDifficulty() == Difficulty.HARD) {
				i = 18;
			}
			
			if (i > 0) {
				target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, i * 20, 0));
			}
		}
		
		return super.postHit(stack, target, attacker);
	}
}
