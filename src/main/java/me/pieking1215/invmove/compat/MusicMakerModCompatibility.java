package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import xerca.xercamusic.client.GuiInstrument;
import xerca.xercamusic.client.GuiMusicSheet;

import java.util.Optional;

public class MusicMakerModCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof GuiInstrument) return Optional.of(false);
        if(screen instanceof GuiMusicSheet) return Optional.of(false);

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {
        return Optional.empty();
    }
}
