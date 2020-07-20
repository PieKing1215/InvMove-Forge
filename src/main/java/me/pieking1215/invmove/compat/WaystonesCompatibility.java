package me.pieking1215.invmove.compat;

import net.blay09.mods.waystones.client.gui.GuiEditWaystone;
import net.blay09.mods.waystones.client.gui.GuiWaystoneList;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class WaystonesCompatibility extends ModCompatibility {
    AtomicBoolean Selection_movement = new AtomicBoolean(true);
    AtomicBoolean Settings_movement = new AtomicBoolean(true);

    AtomicBoolean Selection_background_disable = new AtomicBoolean(true);
    AtomicBoolean Settings_background_disable = new AtomicBoolean(true);

    public WaystonesCompatibility() {
        movementOptions = Arrays.asList(
                new BoolOption("Waystone Selection", "Selection_movement", Selection_movement, true),
                new BoolOption("Waystone Settings", "Settings_movement", Settings_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Waystone Selection", "Selection_background_disable", Selection_background_disable, true),
                new BoolOption("Waystone Settings", "Settings_background_disable", Settings_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if (screen instanceof GuiWaystoneList) return Optional.of(Selection_movement.get());
        if (screen instanceof GuiEditWaystone) return Optional.of(Settings_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if (screen instanceof GuiWaystoneList) return Optional.of(Selection_background_disable.get());
        if (screen instanceof GuiEditWaystone) return Optional.of(Settings_background_disable.get());

        return Optional.empty();
    }
}
