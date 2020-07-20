package me.pieking1215.invmove;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import me.pieking1215.invmove.compat.Compatibility;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

@net.minecraftforge.common.config.Config(modid = "invmove", category = "")
@Mod.EventBusSubscriber
public class Config {
    @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.general")
    public static final General GENERAL = new General();

    @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.movement")
    public static final UIMovement UI_MOVEMENT = new UIMovement();

    @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.background")
    public static final UIBackground UI_BACKGROUND = new UIBackground();

    @net.minecraftforge.common.config.Config.Ignore
    public static boolean hasFinalizedConfig = false;
    @net.minecraftforge.common.config.Config.Ignore
    public static File unknownScreensConfig;
    @net.minecraftforge.common.config.Config.Ignore
    public static HashMap<String, File> modCompatConfigs;

    @net.minecraftforge.common.config.Config.Ignore
    public static Function<Boolean, String> movement_yesNoText = b -> b ? TextFormatting.GREEN + "Allow Movement" : TextFormatting.RED + "Disallow Movement";

    @net.minecraftforge.common.config.Config.Ignore
    public static Function<Boolean, String> background_yesNoText = b -> b ? TextFormatting.GREEN + "Hide Background" : TextFormatting.RED + "Show Background";

    public static void doneLoading() {
        hasFinalizedConfig = true;

        try {


            File dotMinecraft = Minecraft.getMinecraft().mcDataDir;
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
        File dotMinecraft = Minecraft.getMinecraft().mcDataDir;
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
        @net.minecraftforge.common.config.Config.LangKey("config.invmove.enable")
        public boolean enabled = true;

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.debugDisplay")
        public boolean debugDisplay = false;

    }

    public static class UIBackground {

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.background.enable")
        public boolean uiBackground = true;

        @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.types")
        public BackgroundVanillaTypes VANILLA = new BackgroundVanillaTypes();

        class BackgroundVanillaTypes {
            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.inventory")
            public boolean inventory = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.creative")
            public boolean creative = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.crafting")
            public boolean crafting = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.chest")
            public boolean chest = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.shulker")
            public boolean shulker = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.dispenser")
            public boolean dispenser = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.hopper")
            public boolean hopper = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.enchantment")
            public boolean enchantment = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.anvil")
            public boolean anvil = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.beacon")
            public boolean beacon = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.brewing")
            public boolean brewing = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.furnace")
            public boolean furnace = false;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.villager")
            public boolean villager = false;
        }

        @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.types.unrecognized")
        public HashMap<String, Boolean> seenScreens = new HashMap<>();

    }

    public static class UIMovement {

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.movement.enable")
        public boolean moveInInventories = true;

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.movement.sneak")
        public boolean sneakInInventories = false;

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.movement.jump")
        public boolean jumpInInventories = true;

        @net.minecraftforge.common.config.Config.LangKey("config.invmove.movement.textFieldDisables")
        public boolean textFieldDisablesMovement = true;

        @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.types")
        public MovementVanillaTypes VANILLA = new MovementVanillaTypes();

        class MovementVanillaTypes {
            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.inventory")
            public boolean inventory = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.creative")
            public boolean creative = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.crafting")
            public boolean crafting = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.chest")
            public boolean chest = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.shulker")
            public boolean shulker = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.dispenser")
            public boolean dispenser = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.hopper")
            public boolean hopper = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.enchantment")
            public boolean enchantment = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.anvil")
            public boolean anvil = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.beacon")
            public boolean beacon = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.brewing")
            public boolean brewing = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.furnace")
            public boolean furnace = true;

            @net.minecraftforge.common.config.Config.LangKey("config.invmove.type.villager")
            public boolean villager = true;
        }

        @net.minecraftforge.common.config.Config.LangKey("key.invmove.category.types.unrecognized")
        public HashMap<String, Boolean> seenScreens = new HashMap<>();

    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals("invmove")) {
            ConfigManager.sync("invmove", net.minecraftforge.common.config.Config.Type.INSTANCE);
        }
    }

//    public static void registerClothConfig() {
//        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> {
//            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("config.invmove.title");
//            builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/spruce_planks.png"));
//            builder.transparentBackground();
//
//            ConfigEntryBuilder eb = builder.getEntryBuilder();
//            ConfigCategory general = builder.getOrCreateCategory("key.invmove.category.general");
//            general.addEntry(eb.startBooleanToggle("config.invmove.enable", getBoolSafe(GENERAL.enabled, true)).setDefaultValue(true).setSaveConsumer(GENERAL.enabled::set).setTooltip(I18n.format("tooltip.config.invmove.enable").split("\n")).build());
//            general.addEntry(eb.startBooleanToggle("config.invmove.debugDisplay", getBoolSafe(GENERAL.debugDisplay, false)).setDefaultValue(false).setSaveConsumer(GENERAL.debugDisplay::set).setTooltip(I18n.format("tooltip.config.invmove.debugDisplay").split("\n")).build());
//
//            // movement
//
//            ConfigCategory movement = builder.getOrCreateCategory("key.invmove.category.movement");
//            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.enable", getBoolSafe(GENERAL.moveInInventories, true)).setDefaultValue(true).setSaveConsumer(GENERAL.moveInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.enable").split("\n")).build());
//            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.sneak", getBoolSafe(GENERAL.sneakInInventories, false)).setDefaultValue(false).setSaveConsumer(GENERAL.sneakInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.sneak").split("\n")).build());
//            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.jump", getBoolSafe(GENERAL.jumpInInventories, true)).setDefaultValue(true).setSaveConsumer(GENERAL.jumpInInventories::set).setTooltip(I18n.format("tooltip.config.invmove.movement.jump").split("\n")).build());
//            movement.addEntry(eb.startBooleanToggle("config.invmove.movement.textFieldDisables", getBoolSafe(GENERAL.textFieldDisablesMovement, true)).setDefaultValue(true).setSaveConsumer(GENERAL.textFieldDisablesMovement::set).setTooltip(I18n.format("tooltip.config.invmove.movement.textFieldDisables").split("\n")).build());
//
//            SubCategoryBuilder movementTypes = eb.startSubCategory("key.invmove.category.types");
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.inventory",    getBoolSafe(UI_MOVEMENT.inventory, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.inventory::set    ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.creative",     getBoolSafe(UI_MOVEMENT.creative, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.creative::set     ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.crafting",     getBoolSafe(UI_MOVEMENT.crafting, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.crafting::set     ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.chest",        getBoolSafe(UI_MOVEMENT.chest, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.chest::set        ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.shulker",      getBoolSafe(UI_MOVEMENT.shulker, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.shulker::set      ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser",    getBoolSafe(UI_MOVEMENT.dispenser, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.dispenser::set    ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.hopper",       getBoolSafe(UI_MOVEMENT.hopper, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.hopper::set       ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment",  getBoolSafe(UI_MOVEMENT.enchantment, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.enchantment::set  ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.anvil",        getBoolSafe(UI_MOVEMENT.anvil, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.anvil::set        ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.beacon",       getBoolSafe(UI_MOVEMENT.beacon, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.beacon::set       ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.brewing",      getBoolSafe(UI_MOVEMENT.brewing, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.brewing::set      ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.furnace",      getBoolSafe(UI_MOVEMENT.furnace, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.furnace::set      ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", getBoolSafe(UI_MOVEMENT.blastFurnace, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.blastFurnace::set ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.smoker",       getBoolSafe(UI_MOVEMENT.smoker, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.smoker::set       ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.loom",         getBoolSafe(UI_MOVEMENT.loom, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.loom::set         ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.cartography",  getBoolSafe(UI_MOVEMENT.cartography, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.cartography::set  ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone",   getBoolSafe(UI_MOVEMENT.grindstone, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.grindstone::set   ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter",  getBoolSafe(UI_MOVEMENT.stonecutter, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.stonecutter::set  ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.villager",     getBoolSafe(UI_MOVEMENT.villager, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.villager::set     ).setYesNoTextSupplier(movement_yesNoText).build());
//            movementTypes.add(eb.startBooleanToggle("config.invmove.type.book",         getBoolSafe(UI_MOVEMENT.book, true)).setDefaultValue(true).setSaveConsumer(UI_MOVEMENT.book::set     ).setYesNoTextSupplier(movement_yesNoText).build());
//            movement.addEntry(movementTypes.build());
//
//            for(String modid : Compatibility.getCompatibilities().keySet()){
//                SubCategoryBuilder compatCat = eb.startSubCategory(ModList.get().getModContainerById(modid).get().getModInfo().getDisplayName());
//                compatCat.setTooltip(TextFormatting.GRAY + "ModID: " + modid);
//                if(Compatibility.getCompatibilities().get(modid).setupClothMovement(compatCat, eb)) {
//                    movement.addEntry(compatCat.build());
//                }
//            }
//
//            SubCategoryBuilder movementTypesSeen = eb.startSubCategory("key.invmove.category.types.unrecognized");
//            movementTypesSeen.setTooltip(I18n.format("tooltip.config.invmove.unrecognized_desc").split("\n"));
//            for(String scr : UI_MOVEMENT.seenScreens.keySet()){
//                movementTypesSeen.add(eb.startBooleanToggle(scr, UI_MOVEMENT.seenScreens.get(scr)).setDefaultValue(true).setSaveConsumer(b -> {
//                    UI_MOVEMENT.seenScreens.put(scr, b);
//                }).setYesNoTextSupplier(movement_yesNoText).build());
//            }
//            movement.addEntry(movementTypesSeen.build());
//
//            // background
//
//            ConfigCategory background = builder.getOrCreateCategory("key.invmove.category.background");
//            background.addEntry(eb.startBooleanToggle("config.invmove.background.enable", getBoolSafe(GENERAL.uiBackground, true)).setDefaultValue(true).setSaveConsumer(GENERAL.uiBackground::set).setTooltip(I18n.format("tooltip.config.invmove.background.enable").split("\n")).build());
//
//            SubCategoryBuilder backgroundTypes = eb.startSubCategory("key.invmove.category.types");
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.inventory",    !getBoolSafe(UI_BACKGROUND.inventory, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.inventory.set(!b)    ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.creative",     !getBoolSafe(UI_BACKGROUND.creative, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.creative.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.crafting",     !getBoolSafe(UI_BACKGROUND.crafting, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.crafting.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.chest",        !getBoolSafe(UI_BACKGROUND.chest, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.chest.set(!b)        ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.shulker",      !getBoolSafe(UI_BACKGROUND.shulker, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.shulker.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.dispenser",    !getBoolSafe(UI_BACKGROUND.dispenser, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.dispenser.set(!b)    ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.hopper",       !getBoolSafe(UI_BACKGROUND.hopper, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.hopper.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.enchantment",  !getBoolSafe(UI_BACKGROUND.enchantment, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.enchantment.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.anvil",        !getBoolSafe(UI_BACKGROUND.anvil, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.anvil.set(!b)        ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.beacon",       !getBoolSafe(UI_BACKGROUND.beacon, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.beacon.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.brewing",      !getBoolSafe(UI_BACKGROUND.brewing, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.brewing.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.furnace",      !getBoolSafe(UI_BACKGROUND.furnace, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.furnace.set(!b)      ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.blastFurnace", !getBoolSafe(UI_BACKGROUND.blastFurnace, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.blastFurnace.set(!b) ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.smoker",       !getBoolSafe(UI_BACKGROUND.smoker, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.smoker.set(!b)       ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.loom",         !getBoolSafe(UI_BACKGROUND.loom, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.loom.set(!b)         ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.cartography",  !getBoolSafe(UI_BACKGROUND.cartography, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.cartography.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.grindstone",   !getBoolSafe(UI_BACKGROUND.grindstone, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.grindstone.set(!b)   ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.stonecutter",  !getBoolSafe(UI_BACKGROUND.stonecutter, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.stonecutter.set(!b)  ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.villager",     !getBoolSafe(UI_BACKGROUND.villager, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.villager.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
//            backgroundTypes.add(eb.startBooleanToggle("config.invmove.type.book",         !getBoolSafe(UI_BACKGROUND.book, false)).setDefaultValue(true).setSaveConsumer(b -> UI_BACKGROUND.book.set(!b)     ).setYesNoTextSupplier(background_yesNoText).build());
//            background.addEntry(backgroundTypes.build());
//
//            for(String modid : Compatibility.getCompatibilities().keySet()){
//                SubCategoryBuilder compatCat = eb.startSubCategory(ModList.get().getModContainerById(modid).get().getModInfo().getDisplayName());
//                compatCat.setTooltip(TextFormatting.GRAY + "ModID: " + modid);
//                if(Compatibility.getCompatibilities().get(modid).setupClothBackground(compatCat, eb)) {
//                    background.addEntry(compatCat.build());
//                }
//            }
//
//            SubCategoryBuilder backgroundTypesSeen = eb.startSubCategory("key.invmove.category.types.unrecognized");
//            backgroundTypesSeen.setTooltip(I18n.format("tooltip.config.invmove.unrecognized_desc").split("\n"));
//            for(String scr : UI_BACKGROUND.seenScreens.keySet()){
//                backgroundTypesSeen.add(eb.startBooleanToggle(scr, !UI_BACKGROUND.seenScreens.get(scr)).setDefaultValue(true).setSaveConsumer(b -> {
//                    UI_BACKGROUND.seenScreens.put(scr, !b);
//                }).setYesNoTextSupplier(background_yesNoText).build());
//            }
//            background.addEntry(backgroundTypesSeen.build());
//
//
//            return builder.setSavingRunnable(() -> {
//                spec.save();
//
//                try {
//                    if (unknownScreensConfig != null) {
//                        unknownScreensConfig.getParentFile().mkdirs();
//                        if (!unknownScreensConfig.exists()) unknownScreensConfig.createNewFile();
//                        JsonWriter jw = new JsonWriter(new FileWriter(unknownScreensConfig));
//                        jw.setIndent("  ");
//                        jw.beginObject();
//                        for(String scr : UI_BACKGROUND.seenScreens.keySet()) {
//                            jw.name(scr + "_background").value(UI_BACKGROUND.seenScreens.get(scr));
//                        }
//                        for(String scr : UI_MOVEMENT.seenScreens.keySet()) {
//                            jw.name(scr + "_movement").value(UI_MOVEMENT.seenScreens.get(scr));
//                        }
//                        jw.endObject();
//                        jw.close();
//                    }
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//
//                for(String modid : modCompatConfigs.keySet()){
//                    ModCompatibility compat;
//                    File f;
//                    if((f = modCompatConfigs.get(modid)) != null && (compat = Compatibility.getCompatibilities().get(modid)) != null){
//                        try {
//                            f.getParentFile().mkdirs();
//                            if (!f.exists()) f.createNewFile();
//                            JsonWriter jw = new JsonWriter(new FileWriter(f));
//                            jw.setIndent("  ");
//                            jw.beginObject();
//                            compat.saveConfig(jw);
//                            jw.endObject();
//                            jw.close();
//                        }catch(IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }).build();
//        });
//    }
}