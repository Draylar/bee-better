package com.github.draylar.beebetter.ai;

import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public abstract class BeeMoveToTargetGoal extends NotAngryGoal {
	
	private int field_21510;
	private int range;
	
	public BeeMoveToTargetGoal(BeeEntity bee, int range, int i) {
		super(bee);
		this.field_21510 = range;
		this.range = i;
		this.setControls(EnumSet.of(Goal.Control.MOVE));
	}
	
	protected abstract BlockPos getTargetPos();
	
	boolean method_23741() {
		BlockPos blockPos = this.getTargetPos();
		
		if (blockPos == null) {
			return false;
		} else {
			double d = blockPos.getSquaredDistance(new BlockPos(this.getBee()));
			boolean bl = d > (double) (this.field_21510 * this.field_21510);
			boolean bl2 = d < (double) (this.range * this.range);
			return bl && bl2;
		}
	}
	
	protected abstract void method_23885();
	
	@Override
	public void tick() {
		BlockPos blockPos = this.getTargetPos();
		EntityNavigation entityNavigation = this.getBee().getNavigation();
		if (blockPos != null && (entityNavigation.getCurrentPath() == null || entityNavigation.getCurrentPath().reachesTarget())) {
			if (entityNavigation.isIdle()) {
				Vec3d vec3d = new Vec3d(blockPos);
				Vec3d vec3d2 = TargetFinder.findTargetTowards(this.getBee(), 8, 6, vec3d, 0.3141592741012573D);
				if (vec3d2 == null) {
					vec3d2 = TargetFinder.findTargetTowards(this.getBee(), 3, 3, vec3d);
				}
				
				if (vec3d2 == null) {
					this.stop();
					this.method_23885();
					return;
				}
				
				entityNavigation.startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, 1.0D);
			}
			
		} else {
			this.stop();
			this.method_23885();
		}
	}
	
	@Override
	public void stop() {
		this.getBee().getNavigation().stop();
	}
}
