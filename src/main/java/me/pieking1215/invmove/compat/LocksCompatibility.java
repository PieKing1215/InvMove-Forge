package me.pieking1215.invmove.compat;

import melonslise.locks.client.gui.KeyRingScreen;
import melonslise.locks.client.gui.LockPickingScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocksCompatibility extends ModCompatibility {
    AtomicBoolean LockPick_movement  = new AtomicBoolean(false);
    AtomicBoolean KeyRing_movement  = new AtomicBoolean(true);

    AtomicBoolean LockPick_background_disable  = new AtomicBoolean(false);
    AtomicBoolean KeyRing_background_disable  = new AtomicBoolean(true);

    public LocksCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Lock Pick", "LockPick_movement", LockPick_movement, false),
                new BoolOption("Key Ring", "KeyRing_movement", KeyRing_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Lock Pick", "LockPick_background_disable", LockPick_background_disable, false),
                new BoolOption("Key Ring", "KeyRing_background_disable", KeyRing_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof LockPickingScreen) return Optional.of(LockPick_movement.get());
        if(screen instanceof KeyRingScreen) return Optional.of(KeyRing_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof LockPickingScreen) return Optional.of(LockPick_background_disable.get());
        if(screen instanceof KeyRingScreen) return Optional.of(KeyRing_background_disable.get());

        return Optional.empty();
    }
}
