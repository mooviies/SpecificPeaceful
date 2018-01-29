package com.mooviies.speaceful.pathfindergoals;

import com.mooviies.speaceful.SelectivePeaceful;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PathfinderGoalHurtByTarget;

public class CustomPathfinderGoalHurtByTarget extends PathfinderGoalHurtByTarget {
    public CustomPathfinderGoalHurtByTarget(EntityCreature entitycreature, boolean flag, Class<?>... aclass) {
        super(entitycreature, flag, aclass);
    }

    @Override
    public boolean a() {
        EntityLiving entityliving = this.e.getLastDamager();

        if(entityliving == null)
            return super.a();

        if(SelectivePeaceful.getInstance().playerIsOnPeaceful(entityliving.getUniqueID()))
            return false;

        return super.a();
    }
}
