package com.github.draylar.betterbees.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;

public abstract class NotAngryGoal extends Goal {
	
	private BeeEntity bee;
	
	NotAngryGoal(BeeEntity bee) {
		this.bee = bee;
	}
	
	public abstract boolean canBeeStart();
	
	public abstract boolean canBeeContinue();
	
	@Override
	public boolean canStart() {
		return this.canBeeStart() && !bee.isAngry();
	}
	
	@Override
	public boolean shouldContinue() {
		return this.canBeeContinue() && !bee.isAngry();
	}
	
	BeeEntity getBee() {
		return bee;
	}
}
