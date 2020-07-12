package me.pieking1215.invmove;

import com.mojang.blaze3d.systems.RenderSystem;
import me.pieking1215.invmove.compat.Compatibility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.GrindstoneScreen;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.LoomScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.WorkingScreen;
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
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.gui.screen.inventory.SmokerScreen;
import net.minecraft.client.gui.screen.inventory.StonecutterScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
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

    private boolean allowMovementInScreen(Screen screen) {
        if(screen == null) return false;

        if(!Config.GENERAL.enabled.get()) return false;
        if(!Config.GENERAL.moveInInventories.get()) return false;

        if(screen.isPauseScreen() && Minecraft.getInstance().isSingleplayer() && !Minecraft.getInstance().getIntegratedServer().getPublic()) return false;
        if(screen instanceof WorkingScreen) return false;
        if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof WorldSelectionScreen) return false;
        if(screen instanceof ModListScreen) return false;
        if(screen instanceof IngameMenuScreen) return false;
        if(screen instanceof OptionsScreen) return false;
        if(screen instanceof SettingsScreen) return false;

        if(screen instanceof EditSignScreen) return false;

        if(screen instanceof InventoryScreen        && !Config.UI_MOVEMENT.inventory.get()) return false;
        if(screen instanceof CreativeScreen         && !Config.UI_MOVEMENT.creative.get()) return false;
        if(screen instanceof CraftingScreen         && !Config.UI_MOVEMENT.crafting.get()) return false;
        if(screen instanceof ChestScreen            && !Config.UI_MOVEMENT.chest.get()) return false;
        if(screen instanceof ShulkerBoxScreen       && !Config.UI_MOVEMENT.shulker.get()) return false;
        if(screen instanceof DispenserScreen        && !Config.UI_MOVEMENT.dispenser.get()) return false;
        if(screen instanceof HopperScreen           && !Config.UI_MOVEMENT.hopper.get()) return false;
        if(screen instanceof EnchantmentScreen      && !Config.UI_MOVEMENT.enchantment.get()) return false;
        if(screen instanceof AnvilScreen            && !Config.UI_MOVEMENT.anvil.get()) return false;
        if(screen instanceof BeaconScreen           && !Config.UI_MOVEMENT.beacon.get()) return false;
        if(screen instanceof BrewingStandScreen     && !Config.UI_MOVEMENT.brewing.get()) return false;
        if(screen instanceof FurnaceScreen          && !Config.UI_MOVEMENT.furnace.get()) return false;
        if(screen instanceof BlastFurnaceScreen     && !Config.UI_MOVEMENT.blastFurnace.get()) return false;
        if(screen instanceof SmokerScreen           && !Config.UI_MOVEMENT.smoker.get()) return false;
        if(screen instanceof LoomScreen             && !Config.UI_MOVEMENT.loom.get()) return false;
        if(screen instanceof CartographyTableScreen && !Config.UI_MOVEMENT.cartography.get()) return false;
        if(screen instanceof GrindstoneScreen       && !Config.UI_MOVEMENT.grindstone.get()) return false;
        if(screen instanceof StonecutterScreen      && !Config.UI_MOVEMENT.stonecutter.get()) return false;
        if(screen instanceof MerchantScreen         && !Config.UI_MOVEMENT.villager.get()) return false;

        if(Config.GENERAL.textFieldDisablesMovement.get()) {
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

        Optional<Boolean> compatMove = Compatibility.shouldAllowMovement(screen);
        if(compatMove.isPresent()) return compatMove.get();

        Class<? extends Screen> scr = screen.getClass();
        if(Config.UI_MOVEMENT.seenScreens.containsKey(scr.getName())){
            return Config.UI_MOVEMENT.seenScreens.get(scr.getName());
        }else{
            Config.UI_MOVEMENT.seenScreens.put(scr.getName(), true);
        }

        return true;
    }

    public Field[] getDeclaredFieldsSuper(Class aClass) {
        List<Field> fs = new ArrayList<>();

        do{
            fs.addAll(Arrays.asList(aClass.getDeclaredFields()));
        }while((aClass = aClass.getSuperclass()) != null);

        return fs.toArray(new Field[0]);
    }

    /**
     * Clone of MovementInputFromOptions.tick but uses raw keybind data
     */
    public void manualTickMovement(MovementInput input, boolean slow, boolean noDampening) {
        input.forwardKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindForward);
        input.backKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindBack);
        input.leftKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindLeft);
        input.rightKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindRight);
        input.moveForward = input.forwardKeyDown == input.backKeyDown ? 0.0F : (float)(input.forwardKeyDown ? 1 : -1);
        input.moveStrafe = input.leftKeyDown == input.rightKeyDown ? 0.0F : (float)(input.leftKeyDown ? 1 : -1);
        input.jump = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindJump) && Config.GENERAL.jumpInInventories.get();
        input.sneaking = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSneak) && Config.GENERAL.sneakInInventories.get();
        if (!noDampening && (input.sneaking || slow)) {
            input.moveStrafe = (float)((double)input.moveStrafe * 0.3D);
            input.moveForward = (float)((double)input.moveForward * 0.3D);
        }
    }

    /**
     * Returns KeyBinding.pressed, which is normally a private field
     */
    public boolean rawIsKeyDown(KeyBinding key){
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGUIBackgroundDraw(GuiScreenEvent.BackgroundDrawnEvent event){
        Screen screen = event.getGui();

        if(shouldDisableScreenBackground(screen)) {
            // "reenable" rendering
            RenderSystem.translatef(-10000, -10000, 0);
        }
    }

    private boolean shouldDisableScreenBackground(Screen screen) {
        if(!Config.GENERAL.enabled.get()) return false;

        if(!Config.hasFinalizedConfig) Config.doneLoading();

        if(!Config.GENERAL.uiBackground.get()) return false;

        if(screen == null) return false;
        if(screen.isPauseScreen() && Minecraft.getInstance().isSingleplayer() && !Minecraft.getInstance().getIntegratedServer().getPublic()) return false;
        if(screen instanceof WorkingScreen) return false;
        if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof WorldSelectionScreen) return false;
        if(screen instanceof ModListScreen) return false;
        if(screen instanceof IngameMenuScreen) return false;
        if(screen instanceof OptionsScreen) return false;
        if(screen instanceof SettingsScreen) return false;
        if(screen instanceof CreateWorldScreen) return false;
        if(screen instanceof ConfirmScreen) return false;

        if(screen instanceof EditSignScreen) return false;

        if(screen instanceof MainMenuScreen) return false;
        if(screen instanceof ChatScreen) return false;

        if(screen instanceof InventoryScreen)           return !Config.UI_BACKGROUND.inventory.get();
        if(screen instanceof CreativeScreen)            return !Config.UI_BACKGROUND.creative.get();
        if(screen instanceof CraftingScreen)            return !Config.UI_BACKGROUND.crafting.get();
        if(screen instanceof ChestScreen)               return !Config.UI_BACKGROUND.chest.get();
        if(screen instanceof ShulkerBoxScreen)          return !Config.UI_BACKGROUND.shulker.get();
        if(screen instanceof DispenserScreen)           return !Config.UI_BACKGROUND.dispenser.get();
        if(screen instanceof HopperScreen)              return !Config.UI_BACKGROUND.hopper.get();
        if(screen instanceof EnchantmentScreen)         return !Config.UI_BACKGROUND.enchantment.get();
        if(screen instanceof AnvilScreen)               return !Config.UI_BACKGROUND.anvil.get();
        if(screen instanceof BeaconScreen)              return !Config.UI_BACKGROUND.beacon.get();
        if(screen instanceof BrewingStandScreen)        return !Config.UI_BACKGROUND.brewing.get();
        if(screen instanceof FurnaceScreen)             return !Config.UI_BACKGROUND.furnace.get();
        if(screen instanceof BlastFurnaceScreen)        return !Config.UI_BACKGROUND.blastFurnace.get();
        if(screen instanceof SmokerScreen)              return !Config.UI_BACKGROUND.smoker.get();
        if(screen instanceof LoomScreen)                return !Config.UI_BACKGROUND.loom.get();
        if(screen instanceof CartographyTableScreen)    return !Config.UI_BACKGROUND.cartography.get();
        if(screen instanceof GrindstoneScreen)          return !Config.UI_BACKGROUND.grindstone.get();
        if(screen instanceof StonecutterScreen)         return !Config.UI_BACKGROUND.stonecutter.get();
        if(screen instanceof MerchantScreen)            return !Config.UI_BACKGROUND.villager.get();


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
