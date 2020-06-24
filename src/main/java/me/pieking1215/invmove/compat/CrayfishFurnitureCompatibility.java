package me.pieking1215.invmove.compat;

import com.mrcrayfish.furniture.client.gui.screen.DoorMatScreen;
import com.mrcrayfish.furniture.client.gui.screen.MailBoxSettingsScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.CrateScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.MailBoxScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.PostBoxScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class CrayfishFurnitureCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CrateScreen)           return Optional.of(true);
        if(screen instanceof MailBoxScreen)         return Optional.of(true);
        if(screen instanceof PostBoxScreen)         return Optional.of(true);
        if(screen instanceof DoorMatScreen)         return Optional.of(true);
        if(screen instanceof MailBoxSettingsScreen) return Optional.of(true);

        return Optional.empty();
    }
}
