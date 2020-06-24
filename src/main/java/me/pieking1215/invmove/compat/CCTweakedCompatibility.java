package me.pieking1215.invmove.compat;

import dan200.computercraft.client.gui.GuiComputer;
import dan200.computercraft.client.gui.GuiDiskDrive;
import dan200.computercraft.client.gui.GuiPrinter;
import dan200.computercraft.client.gui.GuiTurtle;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class CCTweakedCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        // want to be able to type
        if(screen instanceof GuiComputer) return Optional.of(false);
        if(screen instanceof GuiTurtle)   return Optional.of(false);

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof GuiComputer)  return Optional.of(false);
        if(screen instanceof GuiTurtle)    return Optional.of(false);
        if(screen instanceof GuiPrinter)   return Optional.of(true);
        if(screen instanceof GuiDiskDrive) return Optional.of(true);

        return Optional.empty();
    }
}
