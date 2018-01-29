package com.mooviies.speaceful.entities;

import net.minecraft.server.v1_12_R1.*;

import javax.annotation.Nullable;

public class CustomEntityHusk extends CustomEntityZombie {
    public CustomEntityHusk(World var1) {
        super(var1);
    }

    public static void a(DataConverterManager var0) {
        EntityInsentient.a(var0, EntityZombieHusk.class);
    }

    public boolean P() {
        return super.P() && this.world.h(new BlockPosition(this));
    }

    protected boolean p() {
        return false;
    }

    protected SoundEffect F() {
        return SoundEffects.cY;
    }

    protected SoundEffect d(DamageSource var1) {
        return SoundEffects.da;
    }

    protected SoundEffect cf() {
        return SoundEffects.cZ;
    }

    protected SoundEffect dm() {
        return SoundEffects.db;
    }

    @Nullable
    protected MinecraftKey J() {
        return LootTables.ar;
    }

    public boolean B(Entity var1) {
        boolean var2 = super.B(var1);
        if (var2 && this.getItemInMainHand().isEmpty() && var1 instanceof EntityLiving) {
            float var3 = this.world.D(new BlockPosition(this)).b();
            ((EntityLiving)var1).addEffect(new MobEffect(MobEffects.HUNGER, 140 * (int)var3));
        }

        return var2;
    }

    protected ItemStack dn() {
        return ItemStack.a;
    }
}
