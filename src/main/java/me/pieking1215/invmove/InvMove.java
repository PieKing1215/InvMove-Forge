package me.pieking1215.invmove;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.GrindstoneScreen;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.gui.screen.LoomScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.gui.screen.inventory.BeaconScreen;
import net.minecraft.client.gui.screen.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.BrewingStandScreen;
import net.minecraft.client.gui.screen.inventory.CartographyTableScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.DispenserScreen;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.gui.screen.inventory.SmokerScreen;
import net.minecraft.client.gui.screen.inventory.StonecutterScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod("invmove")
public class InvMove {
    private static final Logger LOGGER = LogManager.getLogger();

    public InvMove() {

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.spec);

        // the config button is just not implemented right now
//        ModList.get().getModContainerById("invmove").get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, new Supplier<BiFunction<Minecraft, Screen, Screen>>() {
//            @Override
//            public BiFunction<Minecraft, Screen, Screen> get() {
//                return (minecraft, guiModList) -> null;
//            }
//        });
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event){
        if(allowMovementInScreen(Minecraft.getInstance().currentScreen)){

            // tick keybinds (since opening the ui unpresses all keys)
            KeyBinding.updateKeyBindState();

            // tick movement
            manualTickMovement(event.getMovementInput(), Minecraft.getInstance().player.shouldRenderSneaking() || Minecraft.getInstance().player.func_213300_bk(), Minecraft.getInstance().player.isSpectator());

            // set sprinting using raw keybind data
            Minecraft.getInstance().player.setSprinting(rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSprint));

        }
    }

    private boolean allowMovementInScreen(Screen screen) {


        if(screen == null) return false;

        if(!Config.GENERAL.enabled.get()) return false;
        if(!Config.GENERAL.moveInInventories.get()) return false;

        if(screen.isPauseScreen()) return false;

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

        // don't allow movement when focused on an active textfield
        // would be better if there was a way to do it for any Screen

        if(screen instanceof AnvilScreen){
            try{
                Field f_nameField = AnvilScreen.class.getDeclaredField("nameField");
                f_nameField.setAccessible(true);
                TextFieldWidget nameField = (TextFieldWidget) f_nameField.get((AnvilScreen)screen);

                if(nameField.func_212955_f()) return false;

            }catch(Exception e){}
        }

        if(screen instanceof CreativeScreen){
            try{
                Field f_searchField = CreativeScreen.class.getDeclaredField("searchField");
                f_searchField.setAccessible(true);
                TextFieldWidget searchField = (TextFieldWidget) f_searchField.get((CreativeScreen)screen);

                if(searchField.func_212955_f()) return false;

            }catch(Exception e){}
        }

        if(screen instanceof IRecipeShownListener){
            try{
                RecipeBookGui recipeBookGui = ((IRecipeShownListener)screen).func_194310_f();
                Field f_searchBar = RecipeBookGui.class.getDeclaredField("searchBar");
                f_searchBar.setAccessible(true);
                TextFieldWidget searchBar = (TextFieldWidget) f_searchBar.get(recipeBookGui);

                if(searchBar.func_212955_f()) return false;

            }catch(Exception e){}
        }

        if(screen instanceof ChatScreen) return false;

        return true;
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
        input.jump = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindJump);
        input.sneak = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSneak);
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
            Field f = KeyBinding.class.getDeclaredField("pressed");
            f.setAccessible(true);
            return f.getBoolean(key);
        }catch(Exception e){
            LOGGER.warn("Failed to access KeyBinding.pressed on \"" + key + "\": " + e.getMessage());
        }

        return false;
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event){
        Screen screen = event.getGui();
        if(shouldDisableScreenBackground(screen)){
            if(screen instanceof InventoryScreen){
                InventoryScreen wrap = (InventoryScreen) screen;
                InventoryScreen wrapper = new InventoryScreen(Minecraft.getInstance().player){
                    @Override
                    public void renderBackground() {}
                };
                event.setGui(wrapper);
            }else if(screen instanceof CreativeScreen) {
                CreativeScreen wrap = (CreativeScreen) screen;

                CreativeScreen wrapper = new CreativeScreen(Minecraft.getInstance().player){
                    @Override
                    public void renderBackground() {}
                };
                event.setGui(wrapper);
            }else if(screen instanceof ChestScreen){
                ChestScreen wrap = (ChestScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    ChestScreen wrapper = new ChestScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof ShulkerBoxScreen){
                ShulkerBoxScreen wrap = (ShulkerBoxScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    ShulkerBoxScreen wrapper = new ShulkerBoxScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof CraftingScreen){
                CraftingScreen wrap = (CraftingScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    CraftingScreen wrapper = new CraftingScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof DispenserScreen){
                DispenserScreen wrap = (DispenserScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    DispenserScreen wrapper = new DispenserScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof HopperScreen){
                HopperScreen wrap = (HopperScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    HopperScreen wrapper = new HopperScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof EnchantmentScreen){
                EnchantmentScreen wrap = (EnchantmentScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    EnchantmentScreen wrapper = new EnchantmentScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof AnvilScreen){
                AnvilScreen wrap = (AnvilScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    AnvilScreen wrapper = new AnvilScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof BeaconScreen){
                BeaconScreen wrap = (BeaconScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    BeaconScreen wrapper = new BeaconScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof BrewingStandScreen){
                BrewingStandScreen wrap = (BrewingStandScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    BrewingStandScreen wrapper = new BrewingStandScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof FurnaceScreen){
                FurnaceScreen wrap = (FurnaceScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    FurnaceScreen wrapper = new FurnaceScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof BlastFurnaceScreen){
                BlastFurnaceScreen wrap = (BlastFurnaceScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    BlastFurnaceScreen wrapper = new BlastFurnaceScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof SmokerScreen){
                SmokerScreen wrap = (SmokerScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    SmokerScreen wrapper = new SmokerScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof LoomScreen){
                LoomScreen wrap = (LoomScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    LoomScreen wrapper = new LoomScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof CartographyTableScreen){
                CartographyTableScreen wrap = (CartographyTableScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    CartographyTableScreen wrapper = new CartographyTableScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof GrindstoneScreen){
                GrindstoneScreen wrap = (GrindstoneScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    GrindstoneScreen wrapper = new GrindstoneScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }else if(screen instanceof StonecutterScreen){
                StonecutterScreen wrap = (StonecutterScreen) screen;

                try{
                    Field f = ContainerScreen.class.getDeclaredField("playerInventory");
                    f.setAccessible(true);

                    StonecutterScreen wrapper = new StonecutterScreen(wrap.getContainer(), (PlayerInventory)f.get(wrap), wrap.getTitle()){
                        @Override
                        public void renderBackground() {}
                    };
                    event.setGui(wrapper);
                }catch(Exception e){}
            }
        }
    }

    private boolean shouldDisableScreenBackground(Screen screen) {

        if(!Config.GENERAL.enabled.get()) return false;
        if(!Config.GENERAL.uiBackground.get()) return false;

        if(screen == null) return false;
        if(screen.isPauseScreen()) return false;

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

        return false;
    }

}
