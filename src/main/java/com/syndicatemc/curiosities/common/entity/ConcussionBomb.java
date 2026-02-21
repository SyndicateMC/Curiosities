package com.syndicatemc.curiosities.common.entity;

import com.syndicatemc.curiosities.core.registry.CEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ConcussionBomb extends PrimedTnt {
    @Nullable
    private LivingEntity owner;

    public ConcussionBomb(EntityType<? extends PrimedTnt> entityType, Level level) {
        super(entityType, level);
    }

    public ConcussionBomb(Level level, double x, double y, double z, @Nullable LivingEntity owner) {
        this(CEntityTypes.CONCUSSION_BOMB.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * Math.PI * 2F;
        this.setDeltaMovement(-Math.sin(d0) * 0.02, 0.2F, -Math.cos(d0) * 0.02);
        this.setFuse(20);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = owner;
    }

    @Override
    protected void explode() {
        this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), null, this.getX(), this.getY(0.0625F), this.getZ(), 8.0F, false, Level.ExplosionInteraction.TRIGGER);
        this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), null, this.getX(), this.getY(0.0625F), this.getZ(), 4.0F, false, Level.ExplosionInteraction.TRIGGER);
        this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), null, this.getX(), this.getY(0.0625F), this.getZ(), 1.0F, false, Level.ExplosionInteraction.TNT);
    }
}
