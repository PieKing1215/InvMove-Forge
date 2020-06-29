package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import xerca.xercamusic.client.GuiInstrument;
import xerca.xercamusic.client.GuiMusicSheet;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MusicMakerModCompatibility extends ModCompatibility {

    AtomicBoolean GuiInstrument_movement = new AtomicBoolean(false);
    AtomicBoolean GuiMusicSheet_movement = new AtomicBoolean(false);

    AtomicBoolean GuiInstrument_background_disable  = new AtomicBoolean(false);
    AtomicBoolean GuiMusicSheet_background_disable  = new AtomicBoolean(false);

    public MusicMakerModCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Instrument", "GuiInstrument_movement", GuiInstrument_movement,false),
                new BoolOption("Sheet Music", "GuiMusicSheet_movement", GuiMusicSheet_movement,false)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Instrument", "GuiInstrument_background_disable", GuiInstrument_background_disable,false),
                new BoolOption("Sheet Music", "GuiMusicSheet_background_disable", GuiMusicSheet_background_disable,false)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof GuiInstrument) return Optional.of(GuiInstrument_movement.get());
        if(screen instanceof GuiMusicSheet) return Optional.of(GuiMusicSheet_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof GuiInstrument) return Optional.of(GuiInstrument_background_disable.get());
        if(screen instanceof GuiMusicSheet) return Optional.of(GuiMusicSheet_background_disable.get());

        return Optional.empty();
    }
}
