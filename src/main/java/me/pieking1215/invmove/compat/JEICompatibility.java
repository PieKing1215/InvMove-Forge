package me.pieking1215.invmove.compat;

import me.pieking1215.invmove.Config;
import mezz.jei.Internal;
import mezz.jei.gui.recipes.RecipesGui;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class JEICompatibility extends ModCompatibility {

    AtomicBoolean RecipesGui_movement = new AtomicBoolean(true);
    AtomicBoolean SPECIAL_OverlayFocus_movement = new AtomicBoolean(false);

    AtomicBoolean RecipesGui_background_disable  = new AtomicBoolean(true);

    public JEICompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Recipe GUI", "RecipesGui_movement", RecipesGui_movement,true),
                new BoolOption("When Search Field Focused", "SPECIAL_OverlayFocus_movement", SPECIAL_OverlayFocus_movement,false)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Recipe GUI", "RecipesGui_background_disable", RecipesGui_background_disable,true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(!SPECIAL_OverlayFocus_movement.get() && Config.UI_MOVEMENT.textFieldDisablesMovement && Internal.getRuntime().getIngredientListOverlay().hasKeyboardFocus()) return Optional.of(false);

        if(screen instanceof RecipesGui) return Optional.of(RecipesGui_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof RecipesGui) return Optional.of(RecipesGui_background_disable.get());

        return Optional.empty();
    }
}
