package com.mooviies.speaceful.entities;

import net.minecraft.server.v1_12_R1.*;

import javax.annotation.Nullable;

public class CustomEntityStray extends CustomEntitySkeleton {
    public CustomEntityStray(World var1) {
        super(var1);
    }

    public static void a(DataConverterManager var0) {
        EntityInsentient.a(var0, net.minecraft.server.v1_12_R1.EntitySkeletonStray.class);
    }

    public boolean P() {
        return super.P() && this.world.h(new BlockPosition(this));
    }

    @Nullable
    protected MinecraftKey J() {
        return LootTables.aq;
    }

    protected SoundEffect F() {
        return SoundEffects.hR;
    }

    protected SoundEffect d(DamageSource var1) {
        return SoundEffects.hT;
    }

    protected SoundEffect cf() {
        return SoundEffects.hS;
    }

    public SoundEffect p(){
        return SoundEffects.hU;
    }

    protected EntityArrow a(float var1) {
        EntityArrow var2 = super.a(var1);
        if (var2 instanceof EntityTippedArrow) {
            ((EntityTippedArrow)var2).a(new MobEffect(MobEffects.SLOWER_MOVEMENT, 600));
        }

        return var2;
    }
}