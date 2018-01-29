package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.*;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntityWitch extends EntityWitch {
    public CustomEntityWitch(World world) {
        super(world);
    }

    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 60, 10.0F));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }
}
