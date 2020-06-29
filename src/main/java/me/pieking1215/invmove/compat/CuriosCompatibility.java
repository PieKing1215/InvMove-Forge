package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import top.theillusivec4.curios.client.gui.CuriosScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CuriosCompatibility extends ModCompatibility {

    AtomicBoolean CuriosScreen_movement = new AtomicBoolean(true);

    AtomicBoolean CuriosScreen_background_disable  = new AtomicBoolean(true);

    public CuriosCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Curios Inventory", "CuriosScreen_movement", CuriosScreen_movement,true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Curios Inventory", "CuriosScreen_background_disable", CuriosScreen_background_disable,true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof CuriosScreen) return Optional.of(CuriosScreen_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CuriosScreen) return Optional.of(CuriosScreen_background_disable.get());

        return Optional.empty();
    }
}
