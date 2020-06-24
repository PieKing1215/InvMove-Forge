package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import ovh.corail.woodcutter.client.gui.WoodcutterScreen;

import java.util.Optional;

public class CorailWoodcutterCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof WoodcutterScreen) return Optional.of(true);

        return Optional.empty();
    }
}
