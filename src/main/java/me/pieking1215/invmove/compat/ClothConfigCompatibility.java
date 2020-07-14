package me.pieking1215.invmove.compat;

import me.shedaniel.forge.clothconfig2.gui.ClothConfigScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class ClothConfigCompatibility extends ModCompatibility {
    @Override
    Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof ClothConfigScreen) return Optional.of(false);

        return Optional.empty();
    }

    @Override
    Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof ClothConfigScreen) return Optional.of(false);

        return Optional.empty();
    }
}
