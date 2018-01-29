package com.mooviies.speaceful.pathfindergoals;

import com.google.common.base.Predicate;
import com.mooviies.speaceful.SelectivePeaceful;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoalNearestAttackableTarget;

import javax.annotation.Nullable;

public class CustomPathfinderGoalNearestAttackableTarget extends PathfinderGoalNearestAttackableTarget {
    public CustomPathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, boolean flag) {
        super(entitycreature, oclass, flag);
    }

    public CustomPathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, boolean flag, boolean flag1) {
        super(entitycreature, oclass, flag, flag1);
    }

    public CustomPathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1, @Nullable Predicate predicate) {
        super(entitycreature, oclass, i, flag, flag1, predicate);
    }

    @Override
    public boolean a() {
        if(super.a())
        {
            if(this.d != null && SelectivePeaceful.getInstance().playerIsOnPeaceful(this.d.getUniqueID()))
                return false;

            return true;
        }
        return false;
    }
}
