package me.pieking1215.invmove.compat;

import codechicken.enderstorage.client.gui.GuiEnderItemStorage;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class EnderStorageCompatibility extends ModCompatibility {
    AtomicBoolean EnderStorage_movement  = new AtomicBoolean(true);

    AtomicBoolean EnderStorage_background_disable  = new AtomicBoolean(true);

    public EnderStorageCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Ender Storage", "EnderStorage_movement", EnderStorage_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Ender Storage", "EnderStorage_background_disable", EnderStorage_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof GuiEnderItemStorage) return Optional.of(EnderStorage_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof GuiEnderItemStorage) return Optional.of(EnderStorage_background_disable.get());

        return Optional.empty();
    }
}
