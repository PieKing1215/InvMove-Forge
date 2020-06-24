package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import svenhjol.charm.decoration.inventory.BookshelfChestScreen;
import svenhjol.charm.decoration.inventory.CrateScreen;

import java.util.Optional;

public class CharmCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof BookshelfChestScreen) return Optional.of(true);
        if(screen instanceof CrateScreen) return Optional.of(true);

        return Optional.empty();
    }
}
