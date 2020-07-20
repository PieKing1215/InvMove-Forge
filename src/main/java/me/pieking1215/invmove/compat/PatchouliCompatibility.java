package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.GuiScreen;
import vazkii.patchouli.client.book.gui.GuiBook;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class PatchouliCompatibility extends ModCompatibility {

    AtomicBoolean GuiBook_movement  = new AtomicBoolean(true);

    AtomicBoolean GuiBook_background_disable  = new AtomicBoolean(true);

    public PatchouliCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Book Gui", "GuiBook_movement", GuiBook_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Book Gui", "GuiBook_background_disable", GuiBook_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GuiBook) return Optional.of(GuiBook_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GuiBook) return Optional.of(GuiBook_background_disable.get());

        return Optional.empty();
    }

}
