package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.*;
import net.minecraft.server.v1_12_R1.*;

public class CustomEntityBlaze extends EntityBlaze {
    public CustomEntityBlaze(World world) {
        super(world);
    }

    @Override
    protected void r() {
        this.goalSelector.a(4, new PathfinderGoalBlazeFireball(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new CustomPathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new CustomPathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }

    static class PathfinderGoalBlazeFireball extends PathfinderGoal {
        private final EntityBlaze a;
        private int b;
        private int c;

        public PathfinderGoalBlazeFireball(EntityBlaze var1) {
            this.a = var1;
            this.a(3);
        }

        public boolean a() {
            EntityLiving var1 = this.a.getGoalTarget();
            return var1 != null && var1.isAlive();
        }

        public void c() {
            this.b = 0;
        }

        public void d() {
            this.a.a(false);
        }

        public void e() {
            --this.c;
            EntityLiving var1 = this.a.getGoalTarget();
            double var2 = this.a.h(var1);
            if (var2 < 4.0D) {
                if (this.c <= 0) {
                    this.c = 20;
                    this.a.B(var1);
                }

                this.a.getControllerMove().a(var1.locX, var1.locY, var1.locZ, 1.0D);
            } else if (var2 < this.f() * this.f()) {
                double var4 = var1.locX - this.a.locX;
                double var6 = var1.getBoundingBox().b + (double)(var1.length / 2.0F) - (this.a.locY + (double)(this.a.length / 2.0F));
                double var8 = var1.locZ - this.a.locZ;
                if (this.c <= 0) {
                    ++this.b;
                    if (this.b == 1) {
                        this.c = 60;
                        this.a.a(true);
                    } else if (this.b <= 4) {
                        this.c = 6;
                    } else {
                        this.c = 100;
                        this.b = 0;
                        this.a.a(false);
                    }

                    if (this.b > 1) {
                        float var10 = MathHelper.c(MathHelper.sqrt(var2)) * 0.5F;
                        this.a.world.a((EntityHuman)null, 1018, new BlockPosition((int)this.a.locX, (int)this.a.locY, (int)this.a.locZ), 0);

                        for(int var11 = 0; var11 < 1; ++var11) {
                            EntitySmallFireball var12 = new EntitySmallFireball(this.a.world, this.a, var4 + this.a.getRandom().nextGaussian() * (double)var10, var6, var8 + this.a.getRandom().nextGaussian() * (double)var10);
                            var12.locY = this.a.locY + (double)(this.a.length / 2.0F) + 0.5D;
                            this.a.world.addEntity(var12);
                        }
                    }
                }

                this.a.getControllerLook().a(var1, 10.0F, 10.0F);
            } else {
                this.a.getNavigation().p();
                this.a.getControllerMove().a(var1.locX, var1.locY, var1.locZ, 1.0D);
            }

            super.e();
        }

        private double f() {
            AttributeInstance var1 = this.a.getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
            return var1 == null ? 16.0D : var1.getValue();
        }
    }
}
