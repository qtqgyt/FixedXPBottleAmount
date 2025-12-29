package net.qtqgyt.FixedXPBottleAmount;

import net.fabricmc.api.ModInitializer;
//? if 1.21.11 {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
*///? }
//? if >= 1.21.6 && <= 1.21.10 {
import net.minecraft.world.level.GameRules;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
//? }

public class FixedXPBottleAmount implements ModInitializer {
    @Override
    public void onInitialize() {}
    //? if 1.21.11 {
    /*public static final GameRule<Integer> FIXED_BOTTLE_XP = GameRuleBuilder.forInteger(-1).buildAndRegister(Identifier.fromNamespaceAndPath("fixed-xp-bottle-amount", "fixed_bottle_xp"));
    *///? } elif >= 1.21.6 && <= 1.21.10 {
    public static final GameRules.Key<GameRules.IntegerValue> FIXED_BOTTLE_XP = GameRuleRegistry.register("fixedXPBottleAmount", GameRules.Category.MISC, GameRuleFactory.createIntRule(-1));
    //? }
}