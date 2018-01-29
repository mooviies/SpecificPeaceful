package com.mooviies.speaceful.entities;

import net.minecraft.server.v1_12_R1.*;

import javax.annotation.Nullable;

public class CustomEntityCaveSpider extends CustomEntitySpider {
    public CustomEntityCaveSpider(World world) {
        super(world);
        this.setSize(0.7F, 0.5F);
    }

    public static void a(DataConverterManager var0) {
        EntityInsentient.a(var0, EntityCaveSpider.class);
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(12.0D);
    }

    public boolean B(Entity var1) {
        if (super.B(var1)) {
            if (var1 instanceof EntityLiving) {
                byte var2 = 0;
                if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                    var2 = 7;
                } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                    var2 = 15;
                }

                if (var2 > 0) {
                    ((EntityLiving)var1).addEffect(new MobEffect(MobEffects.POISON, var2 * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public GroupDataEntity prepare(DifficultyDamageScaler var1, @Nullable GroupDataEntity var2) {
        return var2;
    }

    public float getHeadHeight() {
        return 0.45F;
    }

    @Nullable
    protected MinecraftKey J() {
        return LootTables.t;
    }
}
