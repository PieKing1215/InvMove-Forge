package me.pieking1215.invmove;

import me.shedaniel.forge.clothconfig2.api.ConfigBuilder;
import me.shedaniel.forge.clothconfig2.api.ConfigCategory;
import me.shedaniel.forge.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.ConfigGuiHandler;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final UIBackground UI_BACKGROUND = new UIBackground(BUILDER);
    public static final UIMovement UI_MOVEMENT = new UIMovement(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class General {
        public final ForgeConfigSpec.ConfigValue<Boolean> enabled;
        public final ForgeConfigSpec.ConfigValue<Boolean> moveInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> sneakInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> jumpInInventories;
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
            sneakInInventories = builder
                    .comment("Enables/Disables the ability to sneak when moveInInventories is enabled [false/true|default:true]")
                    .translation("sneakInInventories.invmove.config")
                    .define("sneakInInventories", false);
            jumpInInventories = builder
                    .comment("Enables/Disables the ability to jump when moveInInventories is enabled [false/true|default:true]")
                    .translation("jumpInInventories.invmove.config")
                    .define("jumpInInventories", true);
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

    public static void registerClothConfig() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("config.invmove.title");
            builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/spruce_planks.png"));

            ConfigEntryBuilder eb = builder.getEntryBuilder();
            ConfigCategory general = builder.getOrCreateCategory("key.invmove.category.general");
            general.addEntry(eb.startBooleanToggle("config.invmove.enable", GENERAL.enabled.get()).setDefaultValue(true).setSaveConsumer(GENERAL.enabled::set).setTooltip(I18n.format("tooltip.config.invmove.enable")).build());

            ConfigCategory movement = builder.getOrCreateCategory("key.invmove.category.movement");
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.enable", GENERAL.moveInInventories.get()).setDefaultValue(true).setSaveConsumer(GENERAL.moveInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.enable")).build());
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.sneak", GENERAL.sneakInInventories.get()).setDefaultValue(false).setSaveConsumer(GENERAL.sneakInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.sneak")).build());
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.jump", GENERAL.jumpInInventories.get()).setDefaultValue(true).setSaveConsumer(GENERAL.jumpInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.jump")).build());

            SubCategoryBuilder movementTypes = eb.startSubCategory("Inventory Types");
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.inventory", UI_MOVEMENT.inventory.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.inventory::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.creative", UI_MOVEMENT.creative.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.creative::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.crafting", UI_MOVEMENT.crafting.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.crafting::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.chest", UI_MOVEMENT.chest.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.chest::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.shulker", UI_MOVEMENT.shulker.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.shulker::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser", UI_MOVEMENT.dispenser.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.dispenser::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.hopper", UI_MOVEMENT.hopper.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.hopper::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment", UI_MOVEMENT.enchantment.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.enchantment::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.anvil", UI_MOVEMENT.anvil.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.anvil::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.beacon", UI_MOVEMENT.beacon.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.beacon::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.brewing", UI_MOVEMENT.brewing.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.brewing::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.furnace", UI_MOVEMENT.furnace.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.furnace::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", UI_MOVEMENT.blastFurnace.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.blastFurnace::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.smoker", UI_MOVEMENT.smoker.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.smoker::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.loom", UI_MOVEMENT.loom.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.loom::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.cartography", UI_MOVEMENT.cartography.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.cartography::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone", UI_MOVEMENT.grindstone.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.grindstone::set).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter", UI_MOVEMENT.stonecutter.get()).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.stonecutter::set).build());
            movement.addEntry(movementTypes.build());

            ConfigCategory background = builder.getOrCreateCategory("key.invmove.category.background");
            background.addEntry(eb.startBooleanToggle("config.invmove.background.enable", GENERAL.uiBackground.get()).setDefaultValue(true).setSaveConsumer(GENERAL.uiBackground::set).setTooltip(I18n.format("tooltip.config.invmove.background.enable")).build());

            SubCategoryBuilder backgroundTypes = eb.startSubCategory("Inventory Types");
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.inventory", !UI_BACKGROUND.inventory.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.inventory.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.creative", !UI_BACKGROUND.creative.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.creative.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.crafting", !UI_BACKGROUND.crafting.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.crafting.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.chest", !UI_BACKGROUND.chest.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.chest.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.shulker", !UI_BACKGROUND.shulker.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.shulker.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser", !UI_BACKGROUND.dispenser.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.dispenser.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.hopper", !UI_BACKGROUND.hopper.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.hopper.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment", !UI_BACKGROUND.enchantment.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.enchantment.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.anvil", !UI_BACKGROUND.anvil.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.anvil.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.beacon", !UI_BACKGROUND.beacon.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.beacon.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.brewing", !UI_BACKGROUND.brewing.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.brewing.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.furnace", !UI_BACKGROUND.furnace.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.furnace.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", !UI_BACKGROUND.blastFurnace.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.blastFurnace.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.smoker", !UI_BACKGROUND.smoker.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.smoker.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.loom", !UI_BACKGROUND.loom.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.loom.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.cartography", !UI_BACKGROUND.cartography.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.cartography.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone", !UI_BACKGROUND.grindstone.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.grindstone.set(!b)).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter", !UI_BACKGROUND.stonecutter.get()).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.stonecutter.set(!b)).build());
            background.addEntry(backgroundTypes.build());

            return builder.setSavingRunnable(spec::save).build();
        });
    }
}