package me.pieking1215.invmove.compat;

import com.buuz135.industrial.gui.conveyor.GuiConveyor;
import com.hrznstudio.titanium.client.screen.container.BasicAddonScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class IndustrialForegoingCompatibility extends ModCompatibility {
    AtomicBoolean Machine_movement = new AtomicBoolean(true);
    AtomicBoolean Conveyor_movement = new AtomicBoolean(true);

    AtomicBoolean Machine_background_disable = new AtomicBoolean(true);
    AtomicBoolean Conveyor_background_disable = new AtomicBoolean(true);

    public IndustrialForegoingCompatibility() {
        movementOptions = Arrays.asList(
                new BoolOption("Machines", "Machine_movement", Machine_movement, true),
                new BoolOption("Conveyors", "Conveyor_movement", Conveyor_movement, true, "Unfortunately, IF's GUI implementation makes it hard to control individual ones.\nThis compatibility may get updated in the future.")
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Machines", "Machine_background_disable", Machine_background_disable, true),
                new BoolOption("Conveyors", "Conveyor_background_disable", Conveyor_background_disable, true, "Unfortunately, IF's GUI implementation makes it hard to control individual ones.\nThis compatibility may get updated in the future.")
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if (screen instanceof BasicAddonScreen) return Optional.of(Machine_movement.get());
        if (screen instanceof GuiConveyor) return Optional.of(Conveyor_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if (screen instanceof BasicAddonScreen) return Optional.of(Machine_background_disable.get());
        if (screen instanceof GuiConveyor) return Optional.of(Conveyor_background_disable.get());

        return Optional.empty();
    }
}
