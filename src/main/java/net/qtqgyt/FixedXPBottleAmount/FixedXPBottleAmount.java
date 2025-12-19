package net.qtqgyt.FixedXPBottleAmount;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;

public class FixedXPBottleAmount implements ModInitializer {
    @Override
    public void onInitialize() {}
    public static final GameRule<Integer> FIXED_BOTTLE_XP = GameRuleBuilder.forInteger(-1).buildAndRegister(Identifier.fromNamespaceAndPath("fixed-xp-bottle-amount", "fixed_bottle_xp"));
}
