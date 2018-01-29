package com.mooviies.speaceful.entities;

import com.mooviies.speaceful.pathfindergoals.CustomPathfinderGoalTargetNearestPlayer;
import net.minecraft.server.v1_12_R1.*;

import java.util.Random;

public class CustomEntityGhast extends EntityGhast {
    public CustomEntityGhast(World world) {
        super(world);
    }

    protected void r() {
        this.goalSelector.a(5, new PathfinderGoalGhastIdleMove(this));
        this.goalSelector.a(7, new PathfinderGoalGhastMoveTowardsTarget(this));
        this.goalSelector.a(7, new PathfinderGoalGhastAttackTarget(this));
        this.targetSelector.a(1, new CustomPathfinderGoalTargetNearestPlayer(this));
    }

    static class PathfinderGoalGhastAttackTarget extends PathfinderGoal {
        private final EntityGhast ghast;
        public int a;

        public PathfinderGoalGhastAttackTarget(EntityGhast entityghast) {
            this.ghast = entityghast;
        }

        public boolean a() {
            return this.ghast.getGoalTarget() != null;
        }

        public void c() {
            this.a = 0;
        }

        public void d() {
            this.ghast.a(false);
        }

        public void e() {
            EntityLiving entityliving = this.ghast.getGoalTarget();
            if (entityliving.h(this.ghast) < 4096.0D && this.ghast.hasLineOfSight(entityliving)) {
                World world = this.ghast.world;
                ++this.a;
                if (this.a == 10) {
                    world.a((EntityHuman)null, 1015, new BlockPosition(this.ghast), 0);
                }

                if (this.a == 20) {
                    Vec3D vec3d = this.ghast.e(1.0F);
                    double d2 = entityliving.locX - (this.ghast.locX + vec3d.x * 4.0D);
                    double d3 = entityliving.getBoundingBox().b + (double)(entityliving.length / 2.0F) - (0.5D + this.ghast.locY + (double)(this.ghast.length / 2.0F));
                    double d4 = entityliving.locZ - (this.ghast.locZ + vec3d.z * 4.0D);
                    world.a((EntityHuman)null, 1016, new BlockPosition(this.ghast), 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.ghast, d2, d3, d4);
                    entitylargefireball.bukkitYield = (float)(entitylargefireball.yield = this.ghast.getPower());
                    entitylargefireball.locX = this.ghast.locX + vec3d.x * 4.0D;
                    entitylargefireball.locY = this.ghast.locY + (double)(this.ghast.length / 2.0F) + 0.5D;
                    entitylargefireball.locZ = this.ghast.locZ + vec3d.z * 4.0D;
                    world.addEntity(entitylargefireball);
                    this.a = -40;
                }
            } else if (this.a > 0) {
                --this.a;
            }

            this.ghast.a(this.a > 10);
        }
    }

    static class PathfinderGoalGhastIdleMove extends PathfinderGoal {
        private final EntityGhast a;

        public PathfinderGoalGhastIdleMove(EntityGhast entityghast) {
            this.a = entityghast;
            this.a(1);
        }

        public boolean a() {
            ControllerMove controllermove = this.a.getControllerMove();
            if (!controllermove.b()) {
                return true;
            } else {
                double d0 = controllermove.d() - this.a.locX;
                double d1 = controllermove.e() - this.a.locY;
                double d2 = controllermove.f() - this.a.locZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean b() {
            return false;
        }

        public void c() {
            Random random = this.a.getRandom();
            double d0 = this.a.locX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.a.locY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.a.locZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.a.getControllerMove().a(d0, d1, d2, 1.0D);
        }
    }

    static class PathfinderGoalGhastMoveTowardsTarget extends PathfinderGoal {
        private final EntityGhast a;

        public PathfinderGoalGhastMoveTowardsTarget(EntityGhast entityghast) {
            this.a = entityghast;
            this.a(2);
        }

        public boolean a() {
            return true;
        }

        public void e() {
            if (this.a.getGoalTarget() == null) {
                this.a.yaw = -((float)MathHelper.c(this.a.motX, this.a.motZ)) * 57.295776F;
                this.a.aN = this.a.yaw;
            } else {
                EntityLiving entityliving = this.a.getGoalTarget();
                if (entityliving.h(this.a) < 4096.0D) {
                    double d1 = entityliving.locX - this.a.locX;
                    double d2 = entityliving.locZ - this.a.locZ;
                    this.a.yaw = -((float)MathHelper.c(d1, d2)) * 57.295776F;
                    this.a.aN = this.a.yaw;
                }
            }

        }
    }
}
