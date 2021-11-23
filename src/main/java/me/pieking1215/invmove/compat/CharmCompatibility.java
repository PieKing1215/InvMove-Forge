package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import svenhjol.charm.base.gui.CharmContainerScreen;
import svenhjol.charm.gui.KilnScreen;
import svenhjol.charm.gui.WoodcutterScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CharmCompatibility extends ModCompatibility {

    AtomicBoolean CharmContainerScreen_movement  = new AtomicBoolean(true);
    AtomicBoolean Kiln_movement  = new AtomicBoolean(true);
    AtomicBoolean Woodcutter_movement  = new AtomicBoolean(true);
    AtomicBoolean CharmContainerScreen_background_disable  = new AtomicBoolean(true);
    AtomicBoolean Kiln_background_disable  = new AtomicBoolean(true);
    AtomicBoolean Woodcutter_background_disable  = new AtomicBoolean(true);

    public CharmCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Bookshelf/Crate", "CharmContainerScreen_movement", CharmContainerScreen_movement, true),
                new BoolOption("Kiln", "Kiln_movement", Kiln_movement,true),
                new BoolOption("Woodcutter", "Woodcutter_movement", Woodcutter_movement,true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Bookshelf/Crate", "CharmContainerScreen_background_disable", CharmContainerScreen_background_disable, true),
                new BoolOption("Kiln", "Kiln_background_disable", Kiln_background_disable,true),
                new BoolOption("Woodcutter", "Woodcutter_background_disable", Woodcutter_background_disable,true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        // bookshelf and crate
        if(screen instanceof CharmContainerScreen) return Optional.of(CharmContainerScreen_movement.get());
        if(screen instanceof KilnScreen) return Optional.of(Kiln_movement.get());
        if(screen instanceof WoodcutterScreen) return Optional.of(Woodcutter_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CharmContainerScreen) return Optional.of(CharmContainerScreen_background_disable.get());
        if(screen instanceof KilnScreen) return Optional.of(Kiln_background_disable.get());
        if(screen instanceof WoodcutterScreen) return Optional.of(Woodcutter_background_disable.get());

        return Optional.empty();
    }

}
