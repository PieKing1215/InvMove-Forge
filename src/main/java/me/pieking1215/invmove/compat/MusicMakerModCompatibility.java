package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import xerca.xercamusic.client.GuiMusicSheet;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MusicMakerModCompatibility extends ModCompatibility {

    AtomicBoolean GuiMusicSheet_movement = new AtomicBoolean(false);

    AtomicBoolean GuiMusicSheet_background_disable  = new AtomicBoolean(false);

    public MusicMakerModCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Sheet Music", "GuiMusicSheet_movement", GuiMusicSheet_movement,false)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Sheet Music", "GuiMusicSheet_background_disable", GuiMusicSheet_background_disable,false)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof GuiMusicSheet) return Optional.of(GuiMusicSheet_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof GuiMusicSheet) return Optional.of(GuiMusicSheet_background_disable.get());

        return Optional.empty();
    }
}
