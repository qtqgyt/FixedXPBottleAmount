package net.qtqgyt.FixedXPBottleAmount.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.level.Level;
import net.qtqgyt.FixedXPBottleAmount.FixedXPBottleAmount;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ThrownExperienceBottle.class)
public class ThrownExperienceBottleMixin {
    @ModifyVariable(method = "onHit", at = @At("STORE"))
    private int handler(int i) {
        Level level = ((ThrownExperienceBottle) (Object) this).level();
        if (level instanceof ServerLevel serverLevel) {
            int fixedBottleXp = serverLevel.getGameRules().getInt(FixedXPBottleAmount.FIXED_BOTTLE_XP);
            if (fixedBottleXp < 0) {
                return i;
            }
            return fixedBottleXp;
        }
        return i;
    }
}