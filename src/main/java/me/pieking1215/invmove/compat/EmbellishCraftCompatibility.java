//package me.pieking1215.invmove.compat;
//
//import net.minecraft.client.gui.screen.Screen;
//import tv.mapper.embellishcraft.client.gui.screen.inventory.CrateScreen;
//import tv.mapper.embellishcraft.client.gui.screen.inventory.VerticalChestScreen;
//
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class EmbellishCraftCompatibility extends ModCompatibility {
//
//    AtomicBoolean CrateScreen_movement = new AtomicBoolean(true);
//    AtomicBoolean VerticalChestScreen_movement = new AtomicBoolean(true);
//
//    AtomicBoolean CrateScreen_background_disable  = new AtomicBoolean(true);
//    AtomicBoolean VerticalChestScreen_background_disable  = new AtomicBoolean(true);
//
//    public EmbellishCraftCompatibility(){
//        movementOptions = Arrays.asList(
//                new BoolOption("Crate", "CrateScreen_movement", CrateScreen_movement,true),
//                new BoolOption("Vertical Chest", "VerticalChestScreen_movement", VerticalChestScreen_movement,true)
//        );
//        backgroundOptions = Arrays.asList(
//                new BoolOption("Crate", "CrateScreen_background_disable", CrateScreen_background_disable,true),
//                new BoolOption("Vertical Chest", "VerticalChestScreen_background_disable", VerticalChestScreen_background_disable,true)
//        );
//    }
//
//    @Override
//    public Optional<Boolean> shouldAllowMovement(Screen screen) {
//
//        if(screen instanceof CrateScreen)         return Optional.of(CrateScreen_movement.get());
//        if(screen instanceof VerticalChestScreen) return Optional.of(VerticalChestScreen_movement.get());
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Boolean> shouldDisableBackground(Screen screen) {
//
//        if(screen instanceof CrateScreen)         return Optional.of(CrateScreen_background_disable.get());
//        if(screen instanceof VerticalChestScreen) return Optional.of(VerticalChestScreen_background_disable.get());
//
//        return Optional.empty();
//    }
//}
