package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class GoProneCompatibility extends ModCompatibility {

    public AtomicBoolean SPECIAL_prone_in_guis = new AtomicBoolean(false);

    public GoProneCompatibility() {
        movementOptions = Arrays.asList(
            new BoolOption("Allow Prone", "SPECIAL_prone_in_guis", SPECIAL_prone_in_guis,true)
        );
    }

    @Override
    Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    Optional<Boolean> shouldDisableBackground(Screen screen) {
        return Optional.empty();
    }
}
