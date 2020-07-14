package me.pieking1215.invmove;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.pieking1215.invmove.compat.Compatibility;
import me.pieking1215.invmove.compat.ModCompatibility;
import me.shedaniel.forge.clothconfig2.api.ConfigBuilder;
import me.shedaniel.forge.clothconfig2.api.ConfigCategory;
import me.shedaniel.forge.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final UIBackground UI_BACKGROUND = new UIBackground(BUILDER);
    public static final UIMovement UI_MOVEMENT = new UIMovement(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static boolean hasFinalizedConfig = false;
    public static File unknownScreensConfig;
    public static HashMap<String, File> modCompatConfigs;

    public static Function<Boolean, String> movement_yesNoText = b -> b ? TextFormatting.GREEN + "Allow Movement" : TextFormatting.RED + "Disallow Movement";
    public static Function<Boolean, String> background_yesNoText = b -> b ? TextFormatting.GREEN + "Hide Background" : TextFormatting.RED + "Show Background";

    public static void doneLoading() {
        hasFinalizedConfig = true;

        try {
            File dotMinecraft = FMLPaths.GAMEDIR.get().resolve(FMLConfig.defaultConfigPath()).toFile().getParentFile();
            File f = new File(dotMinecraft, "config/invMove/unknown_screens.json");
            f.getParentFile().mkdirs();
            if(!f.exists()) f.createNewFile();
            unknownScreensConfig = f;

            JsonReader jr = new JsonReader(new FileReader(f));
            JsonElement jp = new JsonParser().parse(jr);
            if(jp.isJsonObject()) {
                JsonObject obj = jp.getAsJsonObject();
                obj.entrySet().forEach(e -> {
                    if(e.getKey().endsWith("_background")){
                        UI_BACKGROUND.seenScreens.put(e.getKey().substring(0, e.getKey().length() - "_background".length()), e.getValue().getAsBoolean());
                    }else if(e.getKey().endsWith("_movement")){
                        UI_MOVEMENT.seenScreens.put(e.getKey().substring(0, e.getKey().length() - "_movement".length()), e.getValue().getAsBoolean());
                    }
                });
            }
            jr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        modCompatConfigs = new HashMap<>();
        File dotMinecraft = FMLPaths.GAMEDIR.get().resolve(FMLConfig.defaultConfigPath()).toFile().getParentFile();
        for(String modid : Compatibility.getCompatibilities().keySet()){
            try {
                File f = new File(dotMinecraft, "config/invMove/" + modid + ".json");
                f.getParentFile().mkdirs();
                if(!f.exists()) f.createNewFile();
                modCompatConfigs.put(modid, f);

                JsonReader jr = new JsonReader(new FileReader(f));
                JsonElement jp = new JsonParser().parse(jr);
                if(jp.isJsonObject()) {
                    JsonObject obj = jp.getAsJsonObject();
                    Compatibility.getCompatibilities().get(modid).loadConfig(obj);
                }
                jr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static class General {
        public final ForgeConfigSpec.ConfigValue<Boolean> enabled;
        public final ForgeConfigSpec.ConfigValue<Boolean> moveInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> sneakInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> jumpInInventories;
        public final ForgeConfigSpec.ConfigValue<Boolean> textFieldDisablesMovement;
        public final ForgeConfigSpec.ConfigValue<Boolean> uiBackground;
        public final ForgeConfigSpec.ConfigValue<Boolean> debugDisplay;

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
                    .comment("Enables/Disables the ability to sneak when moveInInventories is enabled [false/true|default:false]")
                    .translation("sneakInInventories.invmove.config")
                    .define("sneakInInventories", false);
            jumpInInventories = builder
                    .comment("Enables/Disables the ability to jump when moveInInventories is enabled [false/true|default:true]")
                    .translation("jumpInInventories.invmove.config")
                    .define("jumpInInventories", true);
            textFieldDisablesMovement = builder
                    .comment("Enables/Disables the ability to move in inventories while a text field is focused [false/true|default:true]")
                    .translation("textFieldDisablesMovement.invmove.config")
                    .define("textFieldDisablesMovement", true);
            debugDisplay = builder
                    .comment("Enables/Disables the debug display [false/true|default:false]")
                    .translation("debugDisplay.invmove.config")
                    .define("debugDisplay", false);
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
        public final ForgeConfigSpec.ConfigValue<Boolean> villager;
        public final ForgeConfigSpec.ConfigValue<Boolean> book;
        public final HashMap<String, Boolean> seenScreens = new HashMap<>();

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
            villager = builder
                    .comment("Enables/Disables the villager menu UI background [false/true|default:false]")
                    .translation("villager.uibackground.invmove.config")
                    .define("villager", false);
            book = builder
                    .comment("Enables/Disables the book UI background [false/true|default:false]")
                    .translation("book.uibackground.invmove.config")
                    .define("book", false);
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
        public final ForgeConfigSpec.ConfigValue<Boolean> villager;
        public final ForgeConfigSpec.ConfigValue<Boolean> book;
        public final HashMap<String, Boolean> seenScreens = new HashMap<>();

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
            villager = builder
                    .comment("Enables/Disables moving in the villager menu [false/true|default:false]")
                    .translation("villager.uimovement.invmove.config")
                    .define("villager", true);
            book = builder
                    .comment("Enables/Disables moving in the book menu [false/true|default:false]")
                    .translation("book.uimovement.invmove.config")
                    .define("book", true);
            builder.pop();
        }
    }

    public static void registerClothConfig() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("config.invmove.title");
            builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/spruce_planks.png"));
            builder.transparentBackground();

            ConfigEntryBuilder eb = builder.getEntryBuilder();
            ConfigCategory general = builder.getOrCreateCategory("key.invmove.category.general");
            general.addEntry(eb.startBooleanToggle("config.invmove.enable", getBoolSafe(GENERAL.enabled, true)).setDefaultValue(true).setSaveConsumer(GENERAL.enabled::set).setTooltip(I18n.format("tooltip.config.invmove.enable").split("\n")).build());
            general.addEntry(eb.startBooleanToggle("config.invmove.debugDisplay", getBoolSafe(GENERAL.debugDisplay, false)).setDefaultValue(false).setSaveConsumer(GENERAL.debugDisplay::set).setTooltip(I18n.format("tooltip.config.invmove.debugDisplay").split("\n")).build());

            // movement

            ConfigCategory movement = builder.getOrCreateCategory("key.invmove.category.movement");
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.enable", getBoolSafe(GENERAL.moveInInventories, true)).setDefaultValue(true).setSaveConsumer(GENERAL.moveInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.enable").split("\n")).build());
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.sneak", getBoolSafe(GENERAL.sneakInInventories, false)).setDefaultValue(false).setSaveConsumer(GENERAL.sneakInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.sneak").split("\n")).build());
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.jump", getBoolSafe(GENERAL.jumpInInventories, true)).setDefaultValue(true).setSaveConsumer(GENERAL.jumpInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.jump").split("\n")).build());
            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.textFieldDisables", getBoolSafe(GENERAL.textFieldDisablesMovement, true)).setDefaultValue(true).setSaveConsumer(GENERAL.textFieldDisablesMovement::set).setTooltip(I18n.format("tooltip.config.invmove.movement.textFieldDisables").split("\n")).build());

            SubCategoryBuilder movementTypes = eb.startSubCategory("key.invmove.category.types");
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.inventory",    getBoolSafe(UI_MOVEMENT.inventory, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.inventory::set    ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.creative",     getBoolSafe(UI_MOVEMENT.creative, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.creative::set     ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.crafting",     getBoolSafe(UI_MOVEMENT.crafting, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.crafting::set     ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.chest",        getBoolSafe(UI_MOVEMENT.chest, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.chest::set        ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.shulker",      getBoolSafe(UI_MOVEMENT.shulker, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.shulker::set      ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser",    getBoolSafe(UI_MOVEMENT.dispenser, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.dispenser::set    ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.hopper",       getBoolSafe(UI_MOVEMENT.hopper, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.hopper::set       ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment",  getBoolSafe(UI_MOVEMENT.enchantment, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.enchantment::set  ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.anvil",        getBoolSafe(UI_MOVEMENT.anvil, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.anvil::set        ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.beacon",       getBoolSafe(UI_MOVEMENT.beacon, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.beacon::set       ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.brewing",      getBoolSafe(UI_MOVEMENT.brewing, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.brewing::set      ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.furnace",      getBoolSafe(UI_MOVEMENT.furnace, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.furnace::set      ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", getBoolSafe(UI_MOVEMENT.blastFurnace, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.blastFurnace::set ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.smoker",       getBoolSafe(UI_MOVEMENT.smoker, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.smoker::set       ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.loom",         getBoolSafe(UI_MOVEMENT.loom, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.loom::set         ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.cartography",  getBoolSafe(UI_MOVEMENT.cartography, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.cartography::set  ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone",   getBoolSafe(UI_MOVEMENT.grindstone, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.grindstone::set   ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter",  getBoolSafe(UI_MOVEMENT.stonecutter, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.stonecutter::set  ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.villager",     getBoolSafe(UI_MOVEMENT.villager, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.villager::set     ).setYesNoTextSupplier(movement_yesNoText).build());
            movementTypes.add(eb.startBooleanToggle("config.invmove.type.book",         getBoolSafe(UI_MOVEMENT.book, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.book::set     ).setYesNoTextSupplier(movement_yesNoText).build());
            movement.addEntry(movementTypes.build());

            for(String modid : Compatibility.getCompatibilities().keySet()){
                SubCategoryBuilder compatCat = eb.startSubCategory(ModList.get().getModContainerById(modid).get().getModInfo().getDisplayName());
                compatCat.setTooltip(TextFormatting.GRAY + "ModID: " + modid);
                if(Compatibility.getCompatibilities().get(modid).setupClothMovement(compatCat, eb)) {
                    movement.addEntry(compatCat.build());
                }
            }

            SubCategoryBuilder movementTypesSeen = eb.startSubCategory("key.invmove.category.types.unrecognized");
            movementTypesSeen.setTooltip(I18n.format("tooltip.config.invmove.unrecognized_desc").split("\n"));
            for(String scr : UI_MOVEMENT.seenScreens.keySet()){
                movementTypesSeen.add(eb.startBooleanToggle(scr, UI_MOVEMENT.seenScreens.get(scr)).setDefaultValue(true).setSaveConsumer(b -> {
                    UI_MOVEMENT.seenScreens.put(scr, b);
                }).setYesNoTextSupplier(movement_yesNoText).build());
            }
            movement.addEntry(movementTypesSeen.build());

            // background

            ConfigCategory background = builder.getOrCreateCategory("key.invmove.category.background");
            background.addEntry(eb.startBooleanToggle("config.invmove.background.enable", getBoolSafe(GENERAL.uiBackground, true)).setDefaultValue(true).setSaveConsumer(GENERAL.uiBackground::set).setTooltip(I18n.format("tooltip.config.invmove.background.enable").split("\n")).build());

            SubCategoryBuilder backgroundTypes = eb.startSubCategory("key.invmove.category.types");
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.inventory",    !getBoolSafe(UI_BACKGROUND.inventory, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.inventory.set(!b)    ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.creative",     !getBoolSafe(UI_BACKGROUND.creative, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.creative.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.crafting",     !getBoolSafe(UI_BACKGROUND.crafting, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.crafting.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.chest",        !getBoolSafe(UI_BACKGROUND.chest, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.chest.set(!b)        ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.shulker",      !getBoolSafe(UI_BACKGROUND.shulker, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.shulker.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser",    !getBoolSafe(UI_BACKGROUND.dispenser, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.dispenser.set(!b)    ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.hopper",       !getBoolSafe(UI_BACKGROUND.hopper, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.hopper.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment",  !getBoolSafe(UI_BACKGROUND.enchantment, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.enchantment.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.anvil",        !getBoolSafe(UI_BACKGROUND.anvil, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.anvil.set(!b)        ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.beacon",       !getBoolSafe(UI_BACKGROUND.beacon, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.beacon.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.brewing",      !getBoolSafe(UI_BACKGROUND.brewing, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.brewing.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.furnace",      !getBoolSafe(UI_BACKGROUND.furnace, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.furnace.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", !getBoolSafe(UI_BACKGROUND.blastFurnace, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.blastFurnace.set(!b) ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.smoker",       !getBoolSafe(UI_BACKGROUND.smoker, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.smoker.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.loom",         !getBoolSafe(UI_BACKGROUND.loom, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.loom.set(!b)         ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.cartography",  !getBoolSafe(UI_BACKGROUND.cartography, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.cartography.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone",   !getBoolSafe(UI_BACKGROUND.grindstone, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.grindstone.set(!b)   ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter",  !getBoolSafe(UI_BACKGROUND.stonecutter, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.stonecutter.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.villager",     !getBoolSafe(UI_BACKGROUND.villager, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.villager.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.book",         !getBoolSafe(UI_BACKGROUND.book, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.book.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
            background.addEntry(backgroundTypes.build());

            for(String modid : Compatibility.getCompatibilities().keySet()){
                SubCategoryBuilder compatCat = eb.startSubCategory(ModList.get().getModContainerById(modid).get().getModInfo().getDisplayName());
                compatCat.setTooltip(TextFormatting.GRAY + "ModID: " + modid);
                if(Compatibility.getCompatibilities().get(modid).setupClothBackground(compatCat, eb)) {
                    background.addEntry(compatCat.build());
                }
            }

            SubCategoryBuilder backgroundTypesSeen = eb.startSubCategory("key.invmove.category.types.unrecognized");
            backgroundTypesSeen.setTooltip(I18n.format("tooltip.config.invmove.unrecognized_desc").split("\n"));
            for(String scr : UI_BACKGROUND.seenScreens.keySet()){
                backgroundTypesSeen.add(eb.startBooleanToggle(scr, !UI_BACKGROUND.seenScreens.get(scr)).setDefaultValue(true).setSaveConsumer(b -> {
                    UI_BACKGROUND.seenScreens.put(scr, !b);
                }).setYesNoTextSupplier(background_yesNoText).build());
            }
            background.addEntry(backgroundTypesSeen.build());


            return builder.setSavingRunnable(() -> {
                spec.save();

                try {
                    if (unknownScreensConfig != null) {
                        unknownScreensConfig.getParentFile().mkdirs();
                        if (!unknownScreensConfig.exists()) unknownScreensConfig.createNewFile();
                        JsonWriter jw = new JsonWriter(new FileWriter(unknownScreensConfig));
                        jw.setIndent("  ");
                        jw.beginObject();
                        for(String scr : UI_BACKGROUND.seenScreens.keySet()) {
                            jw.name(scr + "_background").value(UI_BACKGROUND.seenScreens.get(scr));
                        }
                        for(String scr : UI_MOVEMENT.seenScreens.keySet()) {
                            jw.name(scr + "_movement").value(UI_MOVEMENT.seenScreens.get(scr));
                        }
                        jw.endObject();
                        jw.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }

                for(String modid : modCompatConfigs.keySet()){
                    ModCompatibility compat;
                    File f;
                    if((f = modCompatConfigs.get(modid)) != null && (compat = Compatibility.getCompatibilities().get(modid)) != null){
                        try {
                            f.getParentFile().mkdirs();
                            if (!f.exists()) f.createNewFile();
                            JsonWriter jw = new JsonWriter(new FileWriter(f));
                            jw.setIndent("  ");
                            jw.beginObject();
                            compat.saveConfig(jw);
                            jw.endObject();
                            jw.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }

            }).build();
        });
    }

    public static boolean getBoolSafe(ForgeConfigSpec.ConfigValue<Boolean> bool, boolean defaultValue){
        Object o = bool.get();
        if(!(o instanceof Boolean)){
            //java thinks this if will never be true but we know it can be
            //noinspection ConstantConditions
            if(o instanceof String){
                String st = (String)o;

                // an invalid string (eg. "foo") should revert to the default
                if(defaultValue){
                    bool.set(!st.equalsIgnoreCase("false"));
                }else{
                    bool.set(st.equalsIgnoreCase("true"));
                }
            }else{
                bool.set(defaultValue);
            }
        }
        return bool.get();
    }
}