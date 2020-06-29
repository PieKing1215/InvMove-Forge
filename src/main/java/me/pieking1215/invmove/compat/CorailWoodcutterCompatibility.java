package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import ovh.corail.woodcutter.client.gui.WoodcutterScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CorailWoodcutterCompatibility extends ModCompatibility {

    AtomicBoolean WoodcutterScreen_movement = new AtomicBoolean(true);

    AtomicBoolean WoodcutterScreen_background_disable  = new AtomicBoolean(true);

    public CorailWoodcutterCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Woodcutter", "WoodcutterScreen_movement", WoodcutterScreen_movement,true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Woodcutter", "WoodcutterScreen_background_disable", WoodcutterScreen_background_disable,true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof WoodcutterScreen) return Optional.of(WoodcutterScreen_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof WoodcutterScreen) return Optional.of(WoodcutterScreen_background_disable.get());

        return Optional.empty();
    }
}
