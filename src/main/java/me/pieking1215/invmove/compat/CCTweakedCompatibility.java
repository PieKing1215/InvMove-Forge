package me.pieking1215.invmove.compat;

import dan200.computercraft.client.gui.GuiComputer;
import dan200.computercraft.client.gui.GuiDiskDrive;
import dan200.computercraft.client.gui.GuiPrinter;
import dan200.computercraft.client.gui.GuiTurtle;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CCTweakedCompatibility extends ModCompatibility {

    // want to be able to type
    AtomicBoolean GuiComputer_movement  = new AtomicBoolean(false);
    AtomicBoolean GuiTurtle_movement    = new AtomicBoolean(false);

    AtomicBoolean GuiPrinter_movement   = new AtomicBoolean(true);
    AtomicBoolean GuiDiskDrive_movement = new AtomicBoolean(true);

    AtomicBoolean GuiComputer_background_disable  = new AtomicBoolean(false);
    AtomicBoolean GuiTurtle_background_disable    = new AtomicBoolean(false);
    AtomicBoolean GuiPrinter_background_disable   = new AtomicBoolean(true);
    AtomicBoolean GuiDiskDrive_background_disable = new AtomicBoolean(true);

    public CCTweakedCompatibility(){
        movementOptions = Arrays.asList(
            new BoolOption("Computer", "GuiComputer_movement", GuiComputer_movement, false),
            new BoolOption("Turtle", "GuiTurtle_movement", GuiTurtle_movement, false),
            new BoolOption("Printer", "GuiPrinter_movement", GuiPrinter_movement, true),
            new BoolOption("Disk Drive", "GuiDiskDrive_movement", GuiDiskDrive_movement,true)
        );
        backgroundOptions = Arrays.asList(
            new BoolOption("Computer", "GuiComputer_background_disable", GuiComputer_background_disable, false),
            new BoolOption("Turtle", "GuiTurtle_background_disable", GuiTurtle_background_disable, false),
            new BoolOption("Printer", "GuiPrinter_background_disable", GuiPrinter_background_disable, true),
            new BoolOption("Disk Drive", "GuiDiskDrive_background_disable", GuiDiskDrive_background_disable,true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GuiComputer)  return Optional.of(GuiComputer_movement.get());
        if(screen instanceof GuiTurtle)    return Optional.of(GuiTurtle_movement.get());
        if(screen instanceof GuiPrinter)   return Optional.of(GuiPrinter_movement.get());
        if(screen instanceof GuiDiskDrive) return Optional.of(GuiDiskDrive_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GuiComputer)  return Optional.of(GuiComputer_background_disable.get());
        if(screen instanceof GuiTurtle)    return Optional.of(GuiTurtle_background_disable.get());
        if(screen instanceof GuiPrinter)   return Optional.of(GuiPrinter_background_disable.get());
        if(screen instanceof GuiDiskDrive) return Optional.of(GuiDiskDrive_background_disable.get());

        return Optional.empty();
    }


}
