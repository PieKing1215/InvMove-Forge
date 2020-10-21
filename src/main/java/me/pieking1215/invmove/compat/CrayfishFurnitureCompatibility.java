package me.pieking1215.invmove.compat;

import com.mrcrayfish.furniture.client.gui.screen.DoorMatScreen;
import com.mrcrayfish.furniture.client.gui.screen.MailBoxSettingsScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.CrateScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.MailBoxScreen;
import com.mrcrayfish.furniture.client.gui.screen.inventory.PostBoxScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CrayfishFurnitureCompatibility extends ModCompatibility {

    AtomicBoolean CrateScreen_movement = new AtomicBoolean(true);
    AtomicBoolean MailBoxScreen_movement = new AtomicBoolean(true);
    AtomicBoolean MailBoxSettingsScreen_movement = new AtomicBoolean(true);
    AtomicBoolean PostBoxScreen_movement = new AtomicBoolean(true);
    AtomicBoolean DoorMatScreen_movement = new AtomicBoolean(true);

    AtomicBoolean CrateScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean MailBoxScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean MailBoxSettingsScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean PostBoxScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean DoorMatScreen_background_disable = new AtomicBoolean(true);

    public CrayfishFurnitureCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Crate", "CrateScreen_movement", CrateScreen_movement, true),
                new BoolOption("Mail Box", "MailBoxScreen_movement", MailBoxScreen_movement, true),
                new BoolOption("Mail Box Settings", "MailBoxSettingsScreen_movement", MailBoxSettingsScreen_movement, true),
                new BoolOption("Post Box", "PostBoxScreen_movement", PostBoxScreen_movement, true),
                new BoolOption("Door Mat", "DoorMatScreen_movement", DoorMatScreen_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Crate", "CrateScreen_background_disable", CrateScreen_background_disable, true),
                new BoolOption("Mail Box", "MailBoxScreen_background_disable", MailBoxScreen_background_disable, true),
                new BoolOption("Mail Box Settings", "MailBoxSettingsScreen_background_disable", MailBoxSettingsScreen_background_disable, true),
                new BoolOption("Post Box", "PostBoxScreen_background_disable", PostBoxScreen_background_disable, true),
                new BoolOption("Door Mat", "DoorMatScreen_background_disable", DoorMatScreen_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        if(screen instanceof CrateScreen)           return Optional.of(CrateScreen_movement.get());
        if(screen instanceof MailBoxScreen)         return Optional.of(MailBoxScreen_movement.get());
        if(screen instanceof MailBoxSettingsScreen) return Optional.of(MailBoxSettingsScreen_movement.get());
        if(screen instanceof PostBoxScreen)         return Optional.of(PostBoxScreen_movement.get());
        if(screen instanceof DoorMatScreen)         return Optional.of(DoorMatScreen_movement.get());
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CrateScreen)           return Optional.of(CrateScreen_background_disable.get());
        if(screen instanceof MailBoxScreen)         return Optional.of(MailBoxScreen_background_disable.get());
        if(screen instanceof MailBoxSettingsScreen) return Optional.of(MailBoxSettingsScreen_background_disable.get());
        if(screen instanceof PostBoxScreen)         return Optional.of(PostBoxScreen_background_disable.get());
        if(screen instanceof DoorMatScreen)         return Optional.of(DoorMatScreen_background_disable.get());

        return Optional.empty();
    }

}
