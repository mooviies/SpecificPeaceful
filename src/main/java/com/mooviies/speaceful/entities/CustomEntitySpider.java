package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.SelectivePeaceful;
import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalHurtByTarget;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntitySpider extends EntitySpider {
    public CustomEntitySpider(World world) {
        super(world);
    }

    @Override
    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
        this.goalSelector.a(4, new PathfinderGoalSpiderMeleeAttack(this));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalSpiderNearestAttackableTarget(this, EntityHuman.class));
        this.targetSelector.a(3, new PathfinderGoalSpiderNearestAttackableTarget(this, EntityIronGolem.class));
    }

    static class PathfinderGoalSpiderMeleeAttack extends PathfinderGoalMeleeAttack {
        public PathfinderGoalSpiderMeleeAttack(EntitySpider entityspider) {
            super(entityspider, 1.0D, true);
        }

        public boolean b() {
            float f = this.b.aw();
            if (f >= 0.5F && this.b.getRandom().nextInt(100) == 0) {
                this.b.setGoalTarget((EntityLiving)null);
                return false;
            } else {
                return super.b();
            }
        }

        protected double a(EntityLiving entityliving) {
            return (double)(4.0F + entityliving.width);
        }
    }

    static class PathfinderGoalSpiderNearestAttackableTarget<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {
        public PathfinderGoalSpiderNearestAttackableTarget(EntitySpider entityspider, Class<T> oclass) {
            super(entityspider, oclass, true);
        }

        public boolean a() {
            if(SelectivePeaceful.getInstance().playerIsOnPeaceful(this.e.getUniqueID()))
                return false;

            float f = this.e.aw();
            return f >= 0.5F ? false : super.a();
        }
    }
}
