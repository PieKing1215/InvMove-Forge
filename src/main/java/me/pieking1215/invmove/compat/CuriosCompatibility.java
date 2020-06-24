package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import top.theillusivec4.curios.client.gui.CuriosScreen;

import java.util.Optional;

public class CuriosCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CuriosScreen) return Optional.of(true);

        return Optional.empty();
    }
}
