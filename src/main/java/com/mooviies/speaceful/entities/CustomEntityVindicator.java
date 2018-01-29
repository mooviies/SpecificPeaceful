package com.mooviies.speaceful.entities;

import com.google.common.base.Predicate;
import com.mooviies.speaceful.pathfindergoals.*;
import net.minecraft.server.v1_12_R1.*;

import javax.annotation.Nullable;

public class CustomEntityVindicator extends EntityVindicator {

    private boolean b;
    private static final Predicate<Entity> c = var1 -> var1 instanceof EntityLiving && ((EntityLiving)var1).cS();

    public CustomEntityVindicator(World world) {
        super(world);
    }

    public void a(NBTTagCompound var1) {
        super.a(var1);
        if (var1.hasKeyOfType("Johnny", 99)) {
            this.b = var1.getBoolean("Johnny");
        }

    }

    public void setCustomName(String var1) {
        super.setCustomName(var1);
        if (!this.b && "Johnny".equals(var1)) {
            this.b = true;
        }

    }

    protected void r() {
        super.r();
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, true, new Class[]{EntityVindicator.class}));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(3, new CustomPathfinderGoalNearestAttackableTarget(this, EntityVillager.class, true));
        this.targetSelector.a(3, new CustomPathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
        this.targetSelector.a(4, new CustomEntityVindicator.a(this));
    }

    static class a extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
        public a(CustomEntityVindicator var1) {
            super(var1, EntityLiving.class, 0, true, true, CustomEntityVindicator.c);
        }

        public boolean a() {
            return ((CustomEntityVindicator)this.e).b && super.a();
        }
    }
}
