package com.github.draylar.betterbees.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

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
}
