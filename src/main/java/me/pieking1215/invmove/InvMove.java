package me.pieking1215.invmove;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.pieking1215.invmove.compat.Compatibility;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.advancements.AdvancementsScreen;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.AlertScreen;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.CommandBlockScreen;
import net.minecraft.client.gui.screen.ConfirmBackupScreen;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.CreateBuffetWorldScreen;
import net.minecraft.client.gui.screen.CreateFlatWorldScreen;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.screen.CustomizeSkinScreen;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.DemoScreen;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.EditMinecartCommandBlockScreen;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.gui.screen.EditStructureScreen;
import net.minecraft.client.gui.screen.EditWorldScreen;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.ErrorScreen;
import net.minecraft.client.gui.screen.FlatPresetsScreen;
import net.minecraft.client.gui.screen.GrindstoneScreen;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.JigsawScreen;
import net.minecraft.client.gui.screen.LoomScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.MemoryErrorScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.OptimizeWorldScreen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ServerListScreen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.ShareToLanScreen;
import net.minecraft.client.gui.screen.SleepInMultiplayerScreen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.client.gui.screen.WorkingScreen;
import net.minecraft.client.gui.screen.WorldLoadProgressScreen;
import net.minecraft.client.gui.screen.WorldSelectionScreen;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.gui.screen.inventory.BeaconScreen;
import net.minecraft.client.gui.screen.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.BrewingStandScreen;
import net.minecraft.client.gui.screen.inventory.CartographyTableScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.DispenserScreen;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.client.gui.screen.inventory.HorseInventoryScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.gui.screen.inventory.SmokerScreen;
import net.minecraft.client.gui.screen.inventory.StonecutterScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.BrewingStandTileEntity;
import net.minecraft.util.MovementInput;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mod("invmove")
public class InvMove {
    private static final Logger LOGGER = LogManager.getLogger();

    public InvMove() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(this);
            Compatibility.loadCompatibility();
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.spec);
            Config.registerClothConfig();
        });
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event){
        if(allowMovementInScreen(Minecraft.getInstance().currentScreen)){

            // tick keybinds (since opening the ui unpresses all keys)
            KeyBinding.updateKeyBindState();

            // this is needed for compatibility with ItemPhysic
            Minecraft.getInstance().gameSettings.keyBindDrop.setPressed(false);

            // tick movement
            manualTickMovement(event.getMovementInput(), Minecraft.getInstance().player.func_228354_I_(), Minecraft.getInstance().player.isSpectator());

            // set sprinting using raw keybind data
            Minecraft.getInstance().player.setSprinting(rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSprint));

        }
    }

    public static boolean allowMovementInScreen(Screen screen) {
        if(screen == null) return false;

        if(!Config.getBoolSafe(Config.GENERAL.enabled, true)) return false;
        if(!Config.getBoolSafe(Config.GENERAL.moveInInventories, true)) return false;

        if(screen.isPauseScreen() && Minecraft.getInstance().isSingleplayer() && !Minecraft.getInstance().getIntegratedServer().getPublic()) return false;

        if(screen instanceof AddServerScreen) return false;
        if(screen instanceof AlertScreen) return false;
        if(screen instanceof ConfirmBackupScreen) return false;
        if(screen instanceof ConfirmScreen) return false;
        if(screen instanceof ConnectingScreen) return false;
        if(screen instanceof CreateBuffetWorldScreen) return false;
        if(screen instanceof CreateFlatWorldScreen) return false;
        if(screen instanceof CreateWorldScreen) return false;
        if(screen instanceof DeathScreen) return false;
        if(screen instanceof DemoScreen) return false;
        if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof DisconnectedScreen) return false;
        if(screen instanceof DownloadTerrainScreen) return false;
        if(screen instanceof ErrorScreen) return false;
        if(screen instanceof FlatPresetsScreen) return false;
        if(screen instanceof IngameMenuScreen) return false;
        if(screen instanceof MainMenuScreen) return false;
        if(screen instanceof MemoryErrorScreen) return false;
        if(screen instanceof ModListScreen) return false;
        if(screen instanceof MultiplayerScreen) return false;
        if(screen instanceof MultiplayerWarningScreen) return false;
        if(screen instanceof OptimizeWorldScreen) return false;
        if(screen instanceof OptionsScreen) return false;
        if(screen instanceof ServerListScreen) return false;
        if(screen instanceof SettingsScreen) return false;
        if(screen instanceof ShareToLanScreen) return false;
        if(screen instanceof StatsScreen) return false;
        if(screen instanceof WinGameScreen) return false;
        if(screen instanceof WorkingScreen) return false;
        if(screen instanceof WorldLoadProgressScreen) return false;
        if(screen instanceof WorldSelectionScreen) return false;

        if(screen instanceof AdvancementsScreen) return false; // config?
        if(screen instanceof ChatScreen) return false;
        if(screen instanceof CommandBlockScreen) return false;
        if(screen instanceof EditBookScreen) return false;
        if(screen instanceof EditMinecartCommandBlockScreen) return false;
        if(screen instanceof EditSignScreen) return false;
        if(screen.getTitle().equals(new TranslationTextComponent("sign.edit", new Object[0]))) return false;

        if(screen instanceof EditStructureScreen) return false;
        if(screen instanceof EditWorldScreen) return false;
        if(screen instanceof JigsawScreen) return false;

        if(Config.getBoolSafe(Config.GENERAL.textFieldDisablesMovement, true)) {
            // don't allow movement when focused on an active textfield

            // search all fields and superclass fields for a TextFieldWidget
            try {
                Field[] fs = getDeclaredFieldsSuper(screen.getClass());

                for (Field f : fs) {
                    f.setAccessible(true);
                    if (TextFieldWidget.class.isAssignableFrom(f.getType())) {
                        TextFieldWidget tfw = (TextFieldWidget) f.get(screen);
                        if (tfw != null && tfw.canWrite()) return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (screen instanceof IRecipeShownListener) {
                try {
                    TextFieldWidget searchBar = ObfuscationReflectionHelper.getPrivateValue(RecipeBookGui.class, ((IRecipeShownListener) screen).getRecipeGui(), "field_193962_q"); //searchField
                    if (searchBar.canWrite()) return false;
                } catch (Exception e) {
                }
            }
        }

        Optional<Boolean> returnAndIgnoreUnrecognized = Optional.empty();
        if(screen instanceof InventoryScreen)        returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.inventory, true));
        if(screen instanceof HorseInventoryScreen)   returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.horseInventory, true));
        if(screen instanceof CreativeScreen)         returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.creative, true));
        if(screen instanceof CraftingScreen)         returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.crafting, true));
        if(screen instanceof ChestScreen)            returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.chest, true));
        if(screen instanceof ShulkerBoxScreen)       returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.shulker, true));
        if(screen instanceof DispenserScreen)        returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.dispenser, true));
        if(screen instanceof HopperScreen)           returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.hopper, true));
        if(screen instanceof EnchantmentScreen)      returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.enchantment, true));
        if(screen instanceof AnvilScreen)            returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.anvil, true));
        if(screen instanceof BeaconScreen)           returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.beacon, true));
        if(screen instanceof BrewingStandScreen)     returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.brewing, true));
        if(screen instanceof FurnaceScreen)          returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.furnace, true));
        if(screen instanceof BlastFurnaceScreen)     returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.blastFurnace, true));
        if(screen instanceof SmokerScreen)           returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.smoker, true));
        if(screen instanceof LoomScreen)             returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.loom, true));
        if(screen instanceof CartographyTableScreen) returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.cartography, true));
        if(screen instanceof GrindstoneScreen)       returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.grindstone, true));
        if(screen instanceof StonecutterScreen)      returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.stonecutter, true));
        if(screen instanceof MerchantScreen)         returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.villager, true));
        if(screen instanceof ReadBookScreen)         returnAndIgnoreUnrecognized = Optional.of(Config.getBoolSafe(Config.UI_MOVEMENT.book, true));

        Optional<Boolean> compatMove = Compatibility.shouldAllowMovement(screen);
        if(compatMove.isPresent()) return compatMove.get();

        if(returnAndIgnoreUnrecognized.isPresent()) return returnAndIgnoreUnrecognized.get();

        Class<? extends Screen> scr = screen.getClass();
        if(Config.UI_MOVEMENT.seenScreens.containsKey(scr.getName())){
            return Config.UI_MOVEMENT.seenScreens.get(scr.getName());
        }else{
            Config.UI_MOVEMENT.seenScreens.put(scr.getName(), true);
        }

        return true;
    }

    public static Field[] getDeclaredFieldsSuper(Class aClass) {
        List<Field> fs = new ArrayList<>();

        do{
            fs.addAll(Arrays.asList(aClass.getDeclaredFields()));
        }while((aClass = aClass.getSuperclass()) != null);

        return fs.toArray(new Field[0]);
    }

    /**
     * Clone of MovementInputFromOptions.tick but uses raw keybind data
     */
    public static void manualTickMovement(MovementInput input, boolean slow, boolean noDampening) {
        input.forwardKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindForward);
        input.backKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindBack);
        input.leftKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindLeft);
        input.rightKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindRight);
        input.moveForward = input.forwardKeyDown == input.backKeyDown ? 0.0F : (float)(input.forwardKeyDown ? 1 : -1);
        input.moveStrafe = input.leftKeyDown == input.rightKeyDown ? 0.0F : (float)(input.leftKeyDown ? 1 : -1);
        input.jump = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindJump) && Config.getBoolSafe(Config.GENERAL.jumpInInventories, true);
        boolean allowSneak = Config.getBoolSafe(Config.GENERAL.sneakInInventories, false);
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().player.isPassenger()){
            allowSneak = Config.getBoolSafe(Config.GENERAL.dismountInInventories, false);
        }
        input.sneaking = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSneak) && allowSneak;
        if (!noDampening && (input.sneaking || slow)) {
            input.moveStrafe = (float)((double)input.moveStrafe * 0.3D);
            input.moveForward = (float)((double)input.moveForward * 0.3D);
        }
    }

    /**
     * Returns KeyBinding.pressed, which is normally a private field
     */
    public static boolean rawIsKeyDown(KeyBinding key){
        try{
            return ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, key, "field_74513_e"); // pressed
        }catch(Exception e){
            LOGGER.warn("Failed to access KeyBinding.pressed on \"" + key + "\": " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // To disable the inventory background, we "disable" rendering at the start of Screen drawing
    //   and "reenable" rendering after the background is drawn.
    // Since the first thing to render is typically the background, this should work pretty nicely
    //   without having to wrap every different type of Screen.
    // We want to be last to get DrawScreenEvent.Pre, but first to get BackgroundDrawnEvent,
    //   so we don't break other mods drawing during BackgroundDrawnEvent

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGUIDraw(GuiScreenEvent.DrawScreenEvent.Pre event){
        Screen screen = event.getGui();

        if(shouldDisableScreenBackground(screen)) {
            // "disable" rendering
            RenderSystem.translatef(10000, 10000, 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGUIDrawPost(GuiScreenEvent.DrawScreenEvent.Post event){
        if(Config.getBoolSafe(Config.GENERAL.debugDisplay, false)) {
            Screen screen = event.getGui();

            int i = 0;
            Class cl = screen.getClass();
            while (cl.getSuperclass() != null) {
                double scale = 1;
                RenderSystem.scaled(scale, scale, 1);
                Minecraft.getInstance().fontRenderer.drawStringWithShadow(new MatrixStack(), cl.getName(), 4, 4 + 10 * i, 0xffffffff);
                RenderSystem.scaled(1 / scale, 1 / scale, 1);

                i++;
                cl = cl.getSuperclass();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGUIBackgroundDraw(GuiScreenEvent.BackgroundDrawnEvent event){
        Screen screen = event.getGui();

        if(shouldDisableScreenBackground(screen)) {
            // "reenable" rendering
            RenderSystem.translatef(-10000, -10000, 0);
        }
    }

    public static boolean shouldDisableScreenBackground(Screen screen) {

        if(!Config.getBoolSafe(Config.GENERAL.enabled, true)) return false;

        if(!Config.hasFinalizedConfig) Config.doneLoading();

        if(!Config.getBoolSafe(Config.GENERAL.uiBackground, true)) return false;

        if(screen == null) return false;
        if(screen.isPauseScreen() && Minecraft.getInstance().isSingleplayer() && !Minecraft.getInstance().getIntegratedServer().getPublic()) return false;

        if(screen instanceof AddServerScreen) return false;
        if(screen instanceof AlertScreen) return false;
        if(screen instanceof ConfirmBackupScreen) return false;
        if(screen instanceof ConfirmScreen) return false;
        if(screen instanceof ConnectingScreen) return false;
        if(screen instanceof CreateBuffetWorldScreen) return false;
        if(screen instanceof CreateFlatWorldScreen) return false;
        if(screen instanceof CreateWorldScreen) return false;
        if(screen instanceof DeathScreen) return false;
        if(screen instanceof DemoScreen) return false;
        if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof DisconnectedScreen) return false;
        if(screen instanceof DownloadTerrainScreen) return false;
        if(screen instanceof ErrorScreen) return false;
        if(screen instanceof FlatPresetsScreen) return false;
        if(screen instanceof IngameMenuScreen) return false;
        if(screen instanceof MainMenuScreen) return false;
        if(screen instanceof MemoryErrorScreen) return false;
        if(screen instanceof ModListScreen) return false;
        if(screen instanceof MultiplayerScreen) return false;
        if(screen instanceof MultiplayerWarningScreen) return false;
        if(screen instanceof OptimizeWorldScreen) return false;
        if(screen instanceof OptionsScreen) return false;
        if(screen instanceof ServerListScreen) return false;
        if(screen instanceof SettingsScreen) return false;
        if(screen instanceof ShareToLanScreen) return false;
        if(screen instanceof StatsScreen) return false;
        if(screen instanceof WinGameScreen) return false;
        if(screen instanceof WorkingScreen) return false;
        if(screen instanceof WorldLoadProgressScreen) return false;
        if(screen instanceof WorldSelectionScreen) return false;

        if(screen instanceof AdvancementsScreen) return false; // config?
        if(screen instanceof ChatScreen) return false;
        if(screen instanceof CommandBlockScreen) return false;
        if(screen instanceof EditMinecartCommandBlockScreen) return false;
        if(screen instanceof EditSignScreen) return false;
        if(screen.getTitle().equals(new TranslationTextComponent("sign.edit", new Object[0]))) return false;
        if(screen instanceof EditStructureScreen) return false;
        if(screen instanceof EditWorldScreen) return false;
        if(screen instanceof JigsawScreen) return false;

        if(screen instanceof InventoryScreen)           return !Config.getBoolSafe(Config.UI_BACKGROUND.inventory, false);
        if(screen instanceof HorseInventoryScreen)      return !Config.getBoolSafe(Config.UI_BACKGROUND.horseInventory, false);
        if(screen instanceof CreativeScreen)            return !Config.getBoolSafe(Config.UI_BACKGROUND.creative, false);
        if(screen instanceof CraftingScreen)            return !Config.getBoolSafe(Config.UI_BACKGROUND.crafting, false);
        if(screen instanceof ChestScreen)               return !Config.getBoolSafe(Config.UI_BACKGROUND.chest, false);
        if(screen instanceof ShulkerBoxScreen)          return !Config.getBoolSafe(Config.UI_BACKGROUND.shulker, false);
        if(screen instanceof DispenserScreen)           return !Config.getBoolSafe(Config.UI_BACKGROUND.dispenser, false);
        if(screen instanceof HopperScreen)              return !Config.getBoolSafe(Config.UI_BACKGROUND.hopper, false);
        if(screen instanceof EnchantmentScreen)         return !Config.getBoolSafe(Config.UI_BACKGROUND.enchantment, false);
        if(screen instanceof AnvilScreen)               return !Config.getBoolSafe(Config.UI_BACKGROUND.anvil, false);
        if(screen instanceof BeaconScreen)              return !Config.getBoolSafe(Config.UI_BACKGROUND.beacon, false);
        if(screen instanceof BrewingStandScreen)        return !Config.getBoolSafe(Config.UI_BACKGROUND.brewing, false);
        if(screen instanceof FurnaceScreen)             return !Config.getBoolSafe(Config.UI_BACKGROUND.furnace, false);
        if(screen instanceof BlastFurnaceScreen)        return !Config.getBoolSafe(Config.UI_BACKGROUND.blastFurnace, false);
        if(screen instanceof SmokerScreen)              return !Config.getBoolSafe(Config.UI_BACKGROUND.smoker, false);
        if(screen instanceof LoomScreen)                return !Config.getBoolSafe(Config.UI_BACKGROUND.loom, false);
        if(screen instanceof CartographyTableScreen)    return !Config.getBoolSafe(Config.UI_BACKGROUND.cartography, false);
        if(screen instanceof GrindstoneScreen)          return !Config.getBoolSafe(Config.UI_BACKGROUND.grindstone, false);
        if(screen instanceof StonecutterScreen)         return !Config.getBoolSafe(Config.UI_BACKGROUND.stonecutter, false);
        if(screen instanceof MerchantScreen)            return !Config.getBoolSafe(Config.UI_BACKGROUND.villager, false);
        if(screen instanceof ReadBookScreen)            return !Config.getBoolSafe(Config.UI_BACKGROUND.book, false);
        if(screen instanceof EditBookScreen)            return !Config.getBoolSafe(Config.UI_BACKGROUND.book, false);


        Optional<Boolean> compatBack = Compatibility.shouldDisableBackground(screen);
        if(compatBack.isPresent()) return compatBack.get();

        Class<? extends Screen> scr = screen.getClass();
        if(Config.UI_BACKGROUND.seenScreens.containsKey(scr.getName())){
            return !Config.UI_BACKGROUND.seenScreens.get(scr.getName());
        }else{
            Config.UI_BACKGROUND.seenScreens.put(scr.getName(), true);
        }

        return false;
    }

}
