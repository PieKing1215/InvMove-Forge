package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import tv.mapper.embellishcraft.client.gui.screen.inventory.CrateScreen;
import tv.mapper.embellishcraft.client.gui.screen.inventory.VerticalChestScreen;

import java.util.Optional;

public class EmbellishCraftCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CrateScreen) return Optional.of(true);
        if(screen instanceof VerticalChestScreen) return Optional.of(true);

        return Optional.empty();
    }
}
