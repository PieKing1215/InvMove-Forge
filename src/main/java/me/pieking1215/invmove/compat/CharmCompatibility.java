//package me.pieking1215.invmove.compat;
//
//import net.minecraft.client.gui.screen.Screen;
//import svenhjol.charm.decoration.inventory.BookshelfChestScreen;
//import svenhjol.charm.decoration.inventory.CrateScreen;
//
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class CharmCompatibility extends ModCompatibility {
//
//    AtomicBoolean BookshelfChestScreen_movement  = new AtomicBoolean(true);
//    AtomicBoolean CrateScreen_movement  = new AtomicBoolean(true);
//    AtomicBoolean BookshelfChestScreen_background_disable  = new AtomicBoolean(true);
//    AtomicBoolean CrateScreen_background_disable  = new AtomicBoolean(true);
//
//    public CharmCompatibility(){
//        movementOptions = Arrays.asList(
//                new BoolOption("Bookshelf Chest", "BookshelfChestScreen_movement", BookshelfChestScreen_movement, true),
//                new BoolOption("Crate", "CrateScreen_movement", CrateScreen_movement,true)
//        );
//        backgroundOptions = Arrays.asList(
//                new BoolOption("Bookshelf Chest", "BookshelfChestScreen_background_disable", BookshelfChestScreen_background_disable, true),
//                new BoolOption("Crate", "CrateScreen_background_disable", CrateScreen_background_disable,true)
//        );
//    }
//
//    @Override
//    public Optional<Boolean> shouldAllowMovement(Screen screen) {
//
//        if(screen instanceof BookshelfChestScreen) return Optional.of(BookshelfChestScreen_movement.get());
//        if(screen instanceof CrateScreen)          return Optional.of(CrateScreen_movement.get());
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Boolean> shouldDisableBackground(Screen screen) {
//
//        if(screen instanceof BookshelfChestScreen) return Optional.of(BookshelfChestScreen_background_disable.get());
//        if(screen instanceof CrateScreen)          return Optional.of(CrateScreen_background_disable.get());
//
//        return Optional.empty();
//    }
//
//}
