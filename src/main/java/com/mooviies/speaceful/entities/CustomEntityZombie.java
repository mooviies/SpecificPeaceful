package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalHurtByTarget;
import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_12_R1.*;


public class CustomEntityZombie extends EntityZombie {
    public CustomEntityZombie(World world) {
        super(world);
    }

    @Override
    protected void do_() {
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        if (this.world.spigotConfig.zombieAggressiveTowardsVillager) {
            this.targetSelector.a(3, new CustomPathfinderGoalNearestAttackableTarget(this, EntityVillager.class, false));
        }

        this.targetSelector.a(3, new CustomPathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
    }
}
