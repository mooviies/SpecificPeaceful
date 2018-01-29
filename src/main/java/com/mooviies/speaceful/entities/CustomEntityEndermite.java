package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.*;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntityEndermite extends EntityEndermite {
    public CustomEntityEndermite(World world) {
        super(world);
    }

    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }
}
