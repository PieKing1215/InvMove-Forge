package me.pieking1215.invmove.compat;

import mezz.jei.Internal;
import mezz.jei.gui.recipes.RecipesGui;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class JEICompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(Internal.getRuntime().getIngredientListOverlay().hasKeyboardFocus()) return Optional.of(false);

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof RecipesGui) return Optional.of(true);

        return Optional.empty();
    }
}
