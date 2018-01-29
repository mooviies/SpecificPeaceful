package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.*;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntityCreeper extends EntityCreeper {
    public CustomEntityCreeper(World world) {
        super(world);
    }

    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalSwell(this));
        this.goalSelector.a(3, new PathfinderGoalAvoidTarget(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(2, new CustomPathfinderGoalHurtByTarget(this, false, new Class[0]));
    }
}
