package me.pieking1215.invmove.compat;

import cpw.mods.ironchest.client.gui.chest.GUIChest;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class IronChestsCompatibility extends ModCompatibility {

    AtomicBoolean IronChest_movement  = new AtomicBoolean(true);

    AtomicBoolean IronChest_background_disable  = new AtomicBoolean(true);

    public IronChestsCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Chests", "IronChest_movement", IronChest_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Chests", "IronChest_background_disable", IronChest_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GUIChest) return Optional.of(IronChest_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GUIChest) return Optional.of(IronChest_background_disable.get());

        return Optional.empty();
    }

}
