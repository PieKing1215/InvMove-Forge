package me.pieking1215.invmove;

//import me.pieking1215.invmove.compat.Compatibility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiWorldEdit;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditCommandBlockMinecart;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiEditStructure;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiShulkerBox;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mod(modid = "invmove",
        name = "InvMove",
        version = "0.4.3",
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required-after:forge@[14.23.5.2816,);",
        useMetadata = true,
        canBeDeactivated=true)
public class InvMove {
    private static final Logger LOGGER = LogManager.getLogger();

    @SidedProxy(clientSide = "me.pieking1215.invmove.ProxyCommonClient", serverSide = "me.pieking1215.invmove.ProxyCommon")
    private static ProxyCommon proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.init(this);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event){
        if(allowMovementInScreen(Minecraft.getMinecraft().currentScreen)){

            // tick keybinds (since opening the ui unpresses all keys)
            KeyBinding.updateKeyBindState();

            // this is needed for compatibility with ItemPhysic
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindDrop.getKeyCode(), false);

            // tick movement
            manualTickMovement(event.getMovementInput(), Minecraft.getMinecraft().player.isSneaking() || Minecraft.getMinecraft().player.isInWater(), Minecraft.getMinecraft().player.isSpectator());

            // set sprinting using raw keybind data
            Minecraft.getMinecraft().player.setSprinting(rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSprint));

        }
    }

    private boolean allowMovementInScreen(GuiScreen screen) {
        if(screen == null) return false;

        if(!Config.GENERAL.enabled) return false;
        if(!Config.GENERAL.enabled) return false;

        if(screen.doesGuiPauseGame() && Minecraft.getMinecraft().isSingleplayer() && !Minecraft.getMinecraft().getIntegratedServer().getPublic()) return false;

        if(screen instanceof GuiScreenAddServer) return false;
        //if(screen instanceof AlertScreen) return false;
        //if(screen instanceof ConfirmBackupScreen) return false;
        if(screen instanceof GuiYesNo) return false;
        if(screen instanceof GuiConnecting) return false;
        //if(screen instanceof CreateBuffetWorldScreen) return false;
        if(screen instanceof GuiCreateFlatWorld) return false;
        if(screen instanceof GuiCreateWorld) return false;
        if(screen instanceof GuiGameOver) return false;
        if(screen instanceof GuiScreenDemo) return false;
        //if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof GuiDisconnected) return false;
        if(screen instanceof GuiDownloadTerrain) return false;
        if(screen instanceof GuiErrorScreen) return false;
        if(screen instanceof GuiFlatPresets) return false;
        if(screen instanceof GuiIngameMenu) return false;
        if(screen instanceof GuiMainMenu) return false;
        if(screen instanceof GuiMemoryErrorScreen) return false;
        if(screen instanceof GuiModList) return false;
        if(screen instanceof GuiMultiplayer) return false;
        //if(screen instanceof MultiplayerWarningScreen) return false;
        //if(screen instanceof OptimizeWorldScreen) return false;
        if(screen instanceof GuiOptions) return false;
        if(screen instanceof GuiScreenServerList) return false;
        if(screen instanceof GuiVideoSettings) return false;
        if(screen instanceof GuiShareToLan) return false;
        if(screen instanceof GuiStats) return false;
        if(screen instanceof GuiWinGame) return false;
        if(screen instanceof GuiScreenWorking) return false;
        //if(screen instanceof WorldLoadProgressScreen) return false;
        if(screen instanceof GuiWorldSelection) return false;

        if(screen instanceof GuiScreenAdvancements) return false; // config?
        if(screen instanceof GuiChat) return false;
        if(screen instanceof GuiCommandBlock) return false;
        if(screen instanceof GuiScreenBook) return false;
        if(screen instanceof GuiEditCommandBlockMinecart) return false;
        if(screen instanceof GuiEditSign) return false;
        if(screen instanceof GuiEditStructure) return false;
        if(screen instanceof GuiWorldEdit) return false;
        //if(screen instanceof JigsawScreen) return false;

        if(Config.UI_MOVEMENT.textFieldDisablesMovement) {
            // don't allow movement when focused on an active textfield

            // search all fields and superclass fields for a TextFieldWidget
            try {
                Field[] fs = getDeclaredFieldsSuper(screen.getClass());

                for (Field f : fs) {
                    f.setAccessible(true);
                    if (GuiTextField.class.isAssignableFrom(f.getType())) {
                        GuiTextField tfw = (GuiTextField) f.get(screen);
                        if (tfw != null && tfw.isFocused()) return false; //TODO isEnabled
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (screen instanceof IRecipeShownListener) {
                try {
                    GuiTextField searchBar = ObfuscationReflectionHelper.getPrivateValue(GuiRecipeBook.class, ((IRecipeShownListener) screen).func_194310_f(), "field_193962_q"); //searchBar
                    if(searchBar.isFocused()) return false; //TODO isEnabled
                } catch (Exception e) {
                }
            }
        }

        Optional<Boolean> returnAndIgnoreUnrecognized = Optional.empty();
        if(screen instanceof GuiInventory)        returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.inventory);
        if(screen instanceof GuiContainerCreative)         returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.creative);
        if(screen instanceof GuiCrafting)         returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.crafting);
        if(screen instanceof GuiChest)            returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.chest);
        if(screen instanceof GuiShulkerBox)       returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.shulker);
        if(screen instanceof GuiDispenser)        returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.dispenser);
        if(screen instanceof GuiHopper)           returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.hopper);
        if(screen instanceof GuiEnchantment)      returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.enchantment);
        if(screen instanceof GuiRepair)            returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.anvil);
        if(screen instanceof GuiBeacon)           returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.beacon);
        if(screen instanceof GuiBrewingStand)     returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.brewing);
        if(screen instanceof GuiFurnace)          returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.furnace);
        if(screen instanceof GuiMerchant)         returnAndIgnoreUnrecognized = Optional.of(Config.UI_MOVEMENT.VANILLA.villager);

        //Optional<Boolean> compatMove = Compatibility.shouldAllowMovement(screen);
        //if(compatMove.isPresent()) return compatMove.get();

        if(returnAndIgnoreUnrecognized.isPresent()) return returnAndIgnoreUnrecognized.get();

        Class<? extends GuiScreen> scr = screen.getClass();
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
        input.forwardKeyDown = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward);
        input.backKeyDown = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack);
        input.leftKeyDown = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft);
        input.rightKeyDown = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight);
        input.moveForward = input.forwardKeyDown == input.backKeyDown ? 0.0F : (float)(input.forwardKeyDown ? 1 : -1);
        input.moveStrafe = input.leftKeyDown == input.rightKeyDown ? 0.0F : (float)(input.leftKeyDown ? 1 : -1);
        input.jump = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump) && Config.UI_MOVEMENT.jumpInInventories;
        input.sneak = rawIsKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak) && Config.UI_MOVEMENT.sneakInInventories;
        if (!noDampening && (input.sneak || slow)) {
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
        GuiScreen screen = event.getGui();

        if(shouldDisableScreenBackground(screen)) {
            // "disable" rendering
            GlStateManager.translate(10000, 10000, 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGUIDrawPost(GuiScreenEvent.DrawScreenEvent.Post event){
        if(Config.GENERAL.debugDisplay) {
            GuiScreen screen = event.getGui();

            int i = 0;
            Class cl = screen.getClass();
            while (cl.getSuperclass() != null) {
                double scale = 1;
                GlStateManager.scale(scale, scale, 1);
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(cl.getName(), 4, 4 + 10 * i, 0xffffffff);
                GlStateManager.scale(1 / scale, 1 / scale, 1);

                i++;
                cl = cl.getSuperclass();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGUIBackgroundDraw(GuiScreenEvent.BackgroundDrawnEvent event){
        GuiScreen screen = event.getGui();

        if(shouldDisableScreenBackground(screen)) {
            // "reenable" rendering
            GlStateManager.translate(-10000, -10000, 0);
        }
    }

    private boolean shouldDisableScreenBackground(GuiScreen screen) {

        if(!Config.GENERAL.enabled) return false;

        //if(!Config.hasFinalizedConfig) Config.doneLoading();

        if(!Config.UI_BACKGROUND.uiBackground) return false;

        if(screen == null) return false;
        if(screen.doesGuiPauseGame() && Minecraft.getMinecraft().isSingleplayer() && !Minecraft.getMinecraft().getIntegratedServer().getPublic()) return false;

        if(screen instanceof GuiScreenAddServer) return false;
        //if(screen instanceof AlertScreen) return false;
        //if(screen instanceof ConfirmBackupScreen) return false;
        if(screen instanceof GuiYesNo) return false;
        if(screen instanceof GuiConnecting) return false;
        //if(screen instanceof CreateBuffetWorldScreen) return false;
        if(screen instanceof GuiCreateFlatWorld) return false;
        if(screen instanceof GuiCreateWorld) return false;
        if(screen instanceof GuiGameOver) return false;
        if(screen instanceof GuiScreenDemo) return false;
        //if(screen instanceof DirtMessageScreen) return false;
        if(screen instanceof GuiDisconnected) return false;
        if(screen instanceof GuiDownloadTerrain) return false;
        if(screen instanceof GuiErrorScreen) return false;
        if(screen instanceof GuiFlatPresets) return false;
        if(screen instanceof GuiIngameMenu) return false;
        if(screen instanceof GuiMainMenu) return false;
        if(screen instanceof GuiMemoryErrorScreen) return false;
        if(screen instanceof GuiModList) return false;
        if(screen instanceof GuiConfig) return false;
        if(screen instanceof GuiMultiplayer) return false;
        //if(screen instanceof MultiplayerWarningScreen) return false;
        //if(screen instanceof OptimizeWorldScreen) return false;
        if(screen instanceof GuiOptions) return false;
        if(screen instanceof GuiScreenServerList) return false;
        if(screen instanceof GuiVideoSettings) return false;
        if(screen instanceof GuiShareToLan) return false;
        if(screen instanceof GuiStats) return false;
        if(screen instanceof GuiWinGame) return false;
        if(screen instanceof GuiScreenWorking) return false;
        //if(screen instanceof WorldLoadProgressScreen) return false;
        if(screen instanceof GuiWorldSelection) return false;

        if(screen instanceof GuiScreenAdvancements) return false; // config?
        if(screen instanceof GuiChat) return false;
        if(screen instanceof GuiCommandBlock) return false;
        if(screen instanceof GuiEditCommandBlockMinecart) return false;
        if(screen instanceof GuiEditSign) return false;
        if(screen instanceof GuiEditStructure) return false;
        if(screen instanceof GuiWorldEdit) return false;
        //if(screen instanceof JigsawScreen) return false;

        if(screen instanceof GuiInventory)         return !Config.UI_BACKGROUND.VANILLA.inventory;
        if(screen instanceof GuiContainerCreative) return !Config.UI_BACKGROUND.VANILLA.creative;
        if(screen instanceof GuiCrafting)          return !Config.UI_BACKGROUND.VANILLA.crafting;
        if(screen instanceof GuiChest)             return !Config.UI_BACKGROUND.VANILLA.chest;
        if(screen instanceof GuiShulkerBox)        return !Config.UI_BACKGROUND.VANILLA.shulker;
        if(screen instanceof GuiDispenser)         return !Config.UI_BACKGROUND.VANILLA.dispenser;
        if(screen instanceof GuiHopper)            return !Config.UI_BACKGROUND.VANILLA.hopper;
        if(screen instanceof GuiEnchantment)       return !Config.UI_BACKGROUND.VANILLA.enchantment;
        if(screen instanceof GuiRepair)            return !Config.UI_BACKGROUND.VANILLA.anvil;
        if(screen instanceof GuiBeacon)            return !Config.UI_BACKGROUND.VANILLA.beacon;
        if(screen instanceof GuiBrewingStand)      return !Config.UI_BACKGROUND.VANILLA.brewing;
        if(screen instanceof GuiFurnace)           return !Config.UI_BACKGROUND.VANILLA.furnace;
        if(screen instanceof GuiMerchant)          return !Config.UI_BACKGROUND.VANILLA.villager;
        if(screen instanceof GuiScreenBook)        return false;
        //if(screen instanceof EditBookScreen)     return !Config.UI_BACKGROUND.book;


        //Optional<Boolean> compatBack = Compatibility.shouldDisableBackground(screen);
        //if(compatBack.isPresent()) return compatBack.get();

        Class<? extends GuiScreen> scr = screen.getClass();
        if(Config.UI_BACKGROUND.seenScreens.containsKey(scr.getName())){
            return !Config.UI_BACKGROUND.seenScreens.get(scr.getName());
        }else{
            Config.UI_BACKGROUND.seenScreens.put(scr.getName(), true);
            ConfigManager.sync("invmove", net.minecraftforge.common.config.Config.Type.INSTANCE);
        }

        return false;
    }

}
