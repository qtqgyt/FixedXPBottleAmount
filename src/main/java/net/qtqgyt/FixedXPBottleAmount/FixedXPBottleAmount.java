package net.qtqgyt.FixedXPBottleAmount;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class FixedXPBottleAmount implements ModInitializer {
    @Override
    public void onInitialize() {}
    public static final GameRules.Key<GameRules.IntegerValue> FIXED_BOTTLE_XP = GameRuleRegistry.register("fixedBottleXp", GameRules.Category.MISC, GameRuleFactory.createIntRule(-1));
}
