package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalHurtByTarget;
import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntitySkeleton extends EntitySkeleton {
    public CustomEntitySkeleton(World world) {
        super(world);
    }

    @Override
    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
        this.goalSelector.a(3, new PathfinderGoalFleeSun(this, 1.0D));
        this.goalSelector.a(3, new PathfinderGoalAvoidTarget(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(3, new CustomPathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
    }
}
