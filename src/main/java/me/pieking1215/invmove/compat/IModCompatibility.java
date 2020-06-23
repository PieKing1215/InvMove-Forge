package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public interface IModCompatibility {

    Optional<Boolean> shouldAllowMovement(Screen screen);
    Optional<Boolean> shouldDisableBackground(Screen screen);

}
