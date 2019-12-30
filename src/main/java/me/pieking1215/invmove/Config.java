package me.pieking1215.invmove;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.client.ConfigGuiHandler;
import net.minecraftforge.fml.client.IModGuiFactory;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final UIBackground UI_BACKGROUND = new UIBackground(BUILDER);
    public static final UIMovement UI_MOVEMENT = new UIMovement(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class General {
        public final ForgeConfigSpec.ConfigValue<Boolean> enabled;
        public final ForgeConfigSpec.ConfigValue<Boolean> moveInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> uiBackground;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            enabled = builder
                    .comment("Enables/Disables the whole Mod [false/true|default:true]")
                    .translation("enable.invmove.config")
                    .define("enableMod", true);
            moveInInventories = builder
                    .comment("Enables/Disables the ability to move in inventories [false/true|default:true]")
                    .translation("moveInInventories.invmove.config")
                    .define("moveInInventories", true);
            uiBackground = builder
                    .comment("Enables/Disables the ability to disable individual ui backgrounds [false/true|default:true]")
                    .translation("uiBackground.invmove.config")
                    .define("uiBackground", true);
            builder.pop();
        }
    }

    public static class UIBackground {
        public final ForgeConfigSpec.ConfigValue<Boolean> inventory;
        public final ForgeConfigSpec.ConfigValue<Boolean> creative;
        public final ForgeConfigSpec.ConfigValue<Boolean> crafting;
        public final ForgeConfigSpec.ConfigValue<Boolean> chest;
        public final ForgeConfigSpec.ConfigValue<Boolean> shulker;
        public final ForgeConfigSpec.ConfigValue<Boolean> dispenser;
        public final ForgeConfigSpec.ConfigValue<Boolean> hopper;
        public final ForgeConfigSpec.ConfigValue<Boolean> enchantment;
        public final ForgeConfigSpec.ConfigValue<Boolean> anvil;
        public final ForgeConfigSpec.ConfigValue<Boolean> beacon;
        public final ForgeConfigSpec.ConfigValue<Boolean> brewing;
        public final ForgeConfigSpec.ConfigValue<Boolean> furnace;
        public final ForgeConfigSpec.ConfigValue<Boolean> blastFurnace;
        public final ForgeConfigSpec.ConfigValue<Boolean> smoker;
        public final ForgeConfigSpec.ConfigValue<Boolean> loom;
        public final ForgeConfigSpec.ConfigValue<Boolean> cartography;
        public final ForgeConfigSpec.ConfigValue<Boolean> grindstone;
        public final ForgeConfigSpec.ConfigValue<Boolean> stonecutter;

        public UIBackground(ForgeConfigSpec.Builder builder) {
            builder.push("UIBackground");
            inventory = builder
                    .comment("Enables/Disables the inventory menu UI background [false/true|default:false]")
                    .translation("inventory.uibackground.invmove.config")
                    .define("inventory", false);
            creative = builder
                    .comment("Enables/Disables the creative menu UI background [false/true|default:false]")
                    .translation("creative.uibackground.invmove.config")
                    .define("creative", false);
            crafting = builder
                    .comment("Enables/Disables the crafting menu UI background [false/true|default:false]")
                    .translation("crafting.uibackground.invmove.config")
                    .define("crafting", false);
            chest = builder
                    .comment("Enables/Disables the chest menu UI background [false/true|default:false]")
                    .translation("chest.uibackground.invmove.config")
                    .define("chest", false);
            shulker = builder
                    .comment("Enables/Disables the shulker menu UI background [false/true|default:false]")
                    .translation("shulker.uibackground.invmove.config")
                    .define("shulker", false);
            dispenser = builder
                    .comment("Enables/Disables the dispenser menu UI background [false/true|default:false]")
                    .translation("dispenser.uibackground.invmove.config")
                    .define("dispenser", false);
            hopper = builder
                    .comment("Enables/Disables the hopper menu UI background [false/true|default:false]")
                    .translation("hopper.uibackground.invmove.config")
                    .define("hopper", false);
            enchantment = builder
                    .comment("Enables/Disables the enchantment menu UI background [false/true|default:false]")
                    .translation("enchantment.uibackground.invmove.config")
                    .define("enchantment", false);
            anvil = builder
                    .comment("Enables/Disables the anvil menu UI background [false/true|default:false]")
                    .translation("anvil.uibackground.invmove.config")
                    .define("anvil", false);
            beacon = builder
                    .comment("Enables/Disables the beacon menu UI background [false/true|default:false]")
                    .translation("beacon.uibackground.invmove.config")
                    .define("beacon", false);
            brewing = builder
                    .comment("Enables/Disables the brewing menu UI background [false/true|default:false]")
                    .translation("brewing.uibackground.invmove.config")
                    .define("brewing", false);
            furnace = builder
                    .comment("Enables/Disables the furnace menu UI background [false/true|default:false]")
                    .translation("furnace.uibackground.invmove.config")
                    .define("furnace", false);
            blastFurnace = builder
                    .comment("Enables/Disables the blast furnace menu UI background [false/true|default:false]")
                    .translation("blastFurnace.uibackground.invmove.config")
                    .define("blastFurnace", false);
            smoker = builder
                    .comment("Enables/Disables the smoker menu UI background [false/true|default:false]")
                    .translation("smoker.uibackground.invmove.config")
                    .define("smoker", false);
            loom = builder
                    .comment("Enables/Disables the loom menu UI background [false/true|default:false]")
                    .translation("loom.uibackground.invmove.config")
                    .define("loom", false);
            cartography = builder
                    .comment("Enables/Disables the cartography table menu UI background [false/true|default:false]")
                    .translation("cartography.uibackground.invmove.config")
                    .define("cartography", false);
            grindstone = builder
                    .comment("Enables/Disables the grindstone menu UI background [false/true|default:false]")
                    .translation("grindstone.uibackground.invmove.config")
                    .define("grindstone", false);
            stonecutter = builder
                    .comment("Enables/Disables the stonecutter menu UI background [false/true|default:false]")
                    .translation("stonecutter.uibackground.invmove.config")
                    .define("stonecutter", false);
            builder.pop();
        }
    }

    public static class UIMovement {
        public final ForgeConfigSpec.ConfigValue<Boolean> inventory;
        public final ForgeConfigSpec.ConfigValue<Boolean> creative;
        public final ForgeConfigSpec.ConfigValue<Boolean> crafting;
        public final ForgeConfigSpec.ConfigValue<Boolean> chest;
        public final ForgeConfigSpec.ConfigValue<Boolean> shulker;
        public final ForgeConfigSpec.ConfigValue<Boolean> dispenser;
        public final ForgeConfigSpec.ConfigValue<Boolean> hopper;
        public final ForgeConfigSpec.ConfigValue<Boolean> enchantment;
        public final ForgeConfigSpec.ConfigValue<Boolean> anvil;
        public final ForgeConfigSpec.ConfigValue<Boolean> beacon;
        public final ForgeConfigSpec.ConfigValue<Boolean> brewing;
        public final ForgeConfigSpec.ConfigValue<Boolean> furnace;
        public final ForgeConfigSpec.ConfigValue<Boolean> blastFurnace;
        public final ForgeConfigSpec.ConfigValue<Boolean> smoker;
        public final ForgeConfigSpec.ConfigValue<Boolean> loom;
        public final ForgeConfigSpec.ConfigValue<Boolean> cartography;
        public final ForgeConfigSpec.ConfigValue<Boolean> grindstone;
        public final ForgeConfigSpec.ConfigValue<Boolean> stonecutter;

        public UIMovement(ForgeConfigSpec.Builder builder) {
            builder.push("UIMove");
            inventory = builder
                    .comment("Enables/Disables moving in the inventory menu [false/true|default:false]")
                    .translation("inventory.uimovement.invmove.config")
                    .define("inventory", true);
            creative = builder
                    .comment("Enables/Disables moving in the creative menu [false/true|default:false]")
                    .translation("creative.uimovement.invmove.config")
                    .define("creative", true);
            crafting = builder
                    .comment("Enables/Disables moving in the crafting menu [false/true|default:false]")
                    .translation("crafting.uimovement.invmove.config")
                    .define("crafting", true);
            chest = builder
                    .comment("Enables/Disables moving in the chest menu [false/true|default:false]")
                    .translation("chest.uimovement.invmove.config")
                    .define("chest", true);
            shulker = builder
                    .comment("Enables/Disables moving in the shulker menu [false/true|default:false]")
                    .translation("shulker.uimovement.invmove.config")
                    .define("shulker", true);
            dispenser = builder
                    .comment("Enables/Disables moving in the dispenser menu [false/true|default:false]")
                    .translation("dispenser.uimovement.invmove.config")
                    .define("dispenser", true);
            hopper = builder
                    .comment("Enables/Disables the hopper menu UI background [false/true|default:false]")
                    .translation("hopper.uimovement.invmove.config")
                    .define("hopper", true);
            enchantment = builder
                    .comment("Enables/Disables moving in the enchantment menu [false/true|default:false]")
                    .translation("enchantment.uimovement.invmove.config")
                    .define("enchantment", true);
            anvil = builder
                    .comment("Enables/Disables moving in the anvil menu [false/true|default:false]")
                    .translation("anvil.uimovement.invmove.config")
                    .define("anvil", true);
            beacon = builder
                    .comment("Enables/Disables moving in the beacon menu [false/true|default:false]")
                    .translation("beacon.uimovement.invmove.config")
                    .define("beacon", true);
            brewing = builder
                    .comment("Enables/Disables moving in the brewing menu [false/true|default:false]")
                    .translation("brewing.uimovement.invmove.config")
                    .define("brewing", true);
            furnace = builder
                    .comment("Enables/Disables moving in the furnace menu [false/true|default:false]")
                    .translation("furnace.uimovement.invmove.config")
                    .define("furnace", true);
            blastFurnace = builder
                    .comment("Enables/Disables moving in the blast furnace menu [false/true|default:false]")
                    .translation("blastFurnace.uimovement.invmove.config")
                    .define("blastFurnace", true);
            smoker = builder
                    .comment("Enables/Disables moving in the smoker menu [false/true|default:false]")
                    .translation("smoker.uimovement.invmove.config")
                    .define("smoker", true);
            loom = builder
                    .comment("Enables/Disables moving in the loom menu [false/true|default:false]")
                    .translation("loom.uimovement.invmove.config")
                    .define("loom", true);
            cartography = builder
                    .comment("Enables/Disables moving in the cartography table menu [false/true|default:false]")
                    .translation("cartography.uimovement.invmove.config")
                    .define("cartography", true);
            grindstone = builder
                    .comment("Enables/Disables moving in the grindstone menu [false/true|default:false]")
                    .translation("grindstone.uimovement.invmove.config")
                    .define("grindstone", true);
            stonecutter = builder
                    .comment("Enables/Disables moving in the stonecutter menu [false/true|default:false]")
                    .translation("stonecutter.uimovement.invmove.config")
                    .define("stonecutter", true);
            builder.pop();
        }
    }
}